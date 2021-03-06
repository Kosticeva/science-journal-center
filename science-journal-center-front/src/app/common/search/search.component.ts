import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SearchQuery } from 'src/app/models/search-query';
import { SearchFieldQuery } from 'src/app/models/search-field-query';
import { SearchService } from 'src/app/services/search.service';
import { NewPaperService } from 'src/app/services/new-paper.service';
import { PurchaseService } from 'src/app/services/purchase.service';
import { TaskService } from 'src/app/services/task.service';
import { PaperPurchase } from 'src/app/models/paper-purchase';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  results: any[];
  searchQuery: SearchQuery;
  boolChosen: boolean = false;
  boolQuery: string = "";
  downloadable: string = null;

  fields: any[] = [];

  constructor(
    private searchService: SearchService,
    private newPaperService: NewPaperService,
    private http: HttpClient,
    private purchaseService: PurchaseService,
    private taskService: TaskService,
    private loginService: LoginService
  ) {
  }

  ngOnInit() {
    this.results = [];
    this.searchQuery = {
      queriesByFields: [],
      operatorsBetweenFields: "",
      size: 0
    };

    this.fields = [
      {
        name: "Casopis",
        value: "CASOPIS"
      },
      {
        name: "Rad",
        value: "NASLOV"
      },
      {
        name: "Ime autora",
        value: "AUTOR_IME"
      },
      {
        name: "Prezime autora",
        value: "AUTOR_PREZIME"
      },
      {
        name: "Kljucni pojmovi",
        value: "KLJUCNE_RECI"
      },
      {
        name: "Naucna oblast",
        value: "NAUCNA_OBLAST"
      },
      {
        name: "Sadrzaj",
        value: "SADRZAJ"
      }
    ]
  }

  addFilter() {
    this.searchQuery.queriesByFields.push(
      {
        term: "",
        field: "",
        must: "DA"
      }
    );
  }

  removeFilter(filter: SearchFieldQuery) {
    this.searchQuery.queriesByFields.splice(this.searchQuery.queriesByFields.indexOf(filter), 1);
  }

  checkIfFieldAlreadyTaken(fieldName: string): boolean{
    for(let field of this.searchQuery.queriesByFields) {
      if(fieldName === field.field) {
        return true;
      }
    }

    return false;
  }

  go() {
    let query;

    if(this.boolChosen) {
      query = this.boolQuery;
    }else{
      this.searchQuery.operatorsBetweenFields = "";
      for(let i=0; i<this.searchQuery.queriesByFields.length; i++){
        if(this.searchQuery.queriesByFields[i].must === "DA"){
          this.searchQuery.operatorsBetweenFields += "0";
        }else{
          this.searchQuery.operatorsBetweenFields += "1";
        }
      }

      query = this.searchQuery;
    }

    this.searchService.search(query, !this.boolChosen).subscribe(
      (data) => {
        this.results = data;
        setTimeout(function() {
          for(let i = 0; i<data.length; i++) {
            let element = document.getElementById(`item-${data[i].doi}`);
            element.innerHTML = data[i].highlight;
          }
        }, 500);
      }
    )
  }

  buy(thing: any) {
    //let purchase = new PaperPurchase(null, new Date(), this.loginService.getUser(), null, 0, thing.doi, thing.price, thing.currency);
    this.purchaseService.buyPaper(thing.doi).subscribe(
      (data) => {
        window.open(data.link, '_blank');
      },
      (error) => {
        alert("Doslo je do greske pri kupovini");
      }
    )
  }
}
