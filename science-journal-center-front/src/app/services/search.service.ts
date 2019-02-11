import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Application } from '../models/application';
import { User } from '../models/user';
import { Credentials } from '../models/credentials';

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

  getReviewers(application: Application): Observable<Credentials[]> {
    return this.http.put<Credentials[]>(`http://localhost:8090/api/search/reviewers`, application);
  }
}
