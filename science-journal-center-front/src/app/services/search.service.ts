import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SearchQuery } from '../models/search-query';
import { Observable } from 'rxjs';
import { Paper } from '../models/paper';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(
    private http: HttpClient
  ) { }

  search(query: any, flag: boolean): Observable<any[]>{
    if(flag) {
      return this.http.put<any[]>("http://localhost:8090/api/search", query);
    }else{
      return this.http.put<any[]>("http://localhost:8090/api/search/query", query);
    }
  }
}
