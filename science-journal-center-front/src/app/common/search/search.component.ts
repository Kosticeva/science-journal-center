import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SearchQuery } from 'src/app/models/search-query';
import { SearchFieldQuery } from 'src/app/models/search-field-query';
import { SearchService } from 'src/app/services/search.service';

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

  fields: any[];

  constructor(
    private searchService: SearchService,
    private http: HttpClient
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

  generateSession(link: string) {
    this.http.get<any>(link).subscribe(
      (data) => {
        window.location.href = data.link;
      }
    )
  }
}
