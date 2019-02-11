import { Component, OnInit } from '@angular/core';
import { Application } from 'src/app/models/application';
import { Magazine } from 'src/app/models/magazine';
import { ScienceField } from 'src/app/models/science-field';
import { LoginService } from 'src/app/services/login.service';
import { NewPaperService } from 'src/app/services/new-paper.service';
import { User } from 'src/app/models/user';
import { Router } from '@angular/router';
import { PurchaseService } from 'src/app/services/purchase.service';
import { Subscription } from 'src/app/models/subscription';
import { SubscriptionPurchase } from 'src/app/models/subscription-purchase';

@Component({
  selector: 'app-new-paper',
  templateUrl: './new-paper.component.html',
  styleUrls: ['./new-paper.component.css']
})
export class NewPaperComponent implements OnInit {

  application: Application = new Application(null, null, "", "", "", null, [], null, null, null, null, null, null);
  magazines: Magazine[] = [];
  fields: ScienceField[] = [];
  coauthors: User[] = [];
  newAuthor: User = new User(null, "", "", "", "", "", 0, 0);
  newAuthorOpen: boolean = false;
  fileForm: HTMLInputElement;

  constructor(
    private loginService: LoginService,
    private newPaperService: NewPaperService,
    private router: Router,
    private purchaseService: PurchaseService
  ) { }

  ngOnInit() {
    this.newPaperService.getMagazines().subscribe(
      (data) => {
        this.magazines = data;
      }
    );

    this.newPaperService.getCoauthors().subscribe(
      (data) => {
        this.coauthors = data;
      }
    )
  }

  getFields() {
    this.fields = [];
    this.newPaperService.getFields(this.application.magazine).subscribe(
      (data) => {
        this.fields = data;
      }
    )
  }

  addNewUser() {
    this.newPaperService.createUser(this.newAuthor).subscribe(
      (data) => {
        this.coauthors.push(this.newAuthor);
        this.newAuthor = new User(null, "", "", "", "", "", 0, 0);
        this.newAuthorOpen = false;
      }
    )
  }

  submit() {
    this.newPaperService.getMagazine(this.application.magazine).subscribe(
      (data) => {
        if(data.type === "OPEN_ACCESS") {
          this.purchaseService.getActiveSubscription(data.issn).subscribe(
            (data1) => {
              if(data1 == null) {
                if(confirm("Niste prijavljeni na ovaj magazin ili vam je istekla prethodna pretplata. Da li zelite da se prijavite (ponovo)?")) {
                  const subscription = new Subscription(null, this.loginService.getUser(), data.issn, new Date());
                  this.purchaseService.createSubscription(subscription).subscribe(
                    (data2) => {
                      const purchase = new SubscriptionPurchase(null, new Date(), this.loginService.getUser(), 0, data2.id, data.membership, data.currency);
                      this.purchaseService.purchaseSubscription(purchase).subscribe(
                        (data3) => {
                          this.createApplication();
                        }
                      )
                    }
                  )
                }else{
                  alert("Ne mozete da objavite rad u ovom casopisu. Molimo vas, prvo se prijavite na njega.");
                }
              } else{
                this.createApplication();
              }
            }
          )
        } else {
          this.createApplication();
        }
      }
    )
  }

  createApplication() {
    let data = new FormData();
    data.append("title", this.application.title);
    data.append("magazine", this.application.magazine);
    data.append("abstract", this.application.paperAbstract);
    data.append("keyterms", this.application.keyTerms);
    data.append("author", this.loginService.getUser());
    data.append("field", this.application.field);
    
    for(let i = 0; i<this.application.coauthors.length; i++) {
      data.append("coauthors", this.application.coauthors[i].toString());
    }

    const fileUpload: HTMLInputElement = <HTMLInputElement> document.getElementById("file");
    data.append("file", fileUpload.files[0]);

    this.newPaperService.createAppication(data).subscribe(
      (data) => {
        this.router.navigate(['home']);
      }
    )
  }

  updateFiles() {
    this.fileForm = <HTMLInputElement>document.getElementById("file");
  }

}
