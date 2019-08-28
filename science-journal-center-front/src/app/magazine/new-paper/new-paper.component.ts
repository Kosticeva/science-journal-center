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
import { CamundaService } from 'src/app/services/camunda.service';

@Component({
  selector: 'app-new-paper',
  templateUrl: './new-paper.component.html',
  styleUrls: ['./new-paper.component.css']
})
export class NewPaperComponent implements OnInit {

  magazines: Magazine[] = [];
 
  constructor(
    private router: Router,
    private camundaService: CamundaService,
    private newPaperService: NewPaperService,
    private loginService: LoginService
  ) {}

  ngOnInit() {

    this.newPaperService.getMagazines().subscribe(
      (data) => {
        this.magazines = data;
      }
    )
  }

  startProcess(issn: string) {

    const variables = [
      {
        name: "magazine",
        value: issn
      },
      {
        name: "initiator",
        value: this.loginService.getUser()
      }
    ];

    this.camundaService.startProcessWithVariables("Paper_apply", variables).subscribe(
      (data) => {
        this.router.navigate(["/profile"]);
      }
    );
  }

}
