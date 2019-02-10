import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Magazine } from '../models/magazine';
import { Application } from '../models/application';
import { ScienceField } from '../models/science-field';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class NewPaperService {

  constructor(
    private http: HttpClient
  ) { }

  public getCoauthors(): Observable<User[]> {
    return this.http.get<User[]>(`http://localhost:8090/api/users`);
  }

  public createUser(user: User): Observable<User> {
    return this.http.post<User>(`http://localhost:8090/api/users`, user);
  }

  public getMagazine(issn: string): Observable<Magazine> {
    return this.http.get<Magazine>(`http://localhost:8090/api/magazines/${issn}`);
  }

  public getMagazines(): Observable<Magazine[]> {
    return this.http.get<Magazine[]>(`http://localhost:8090/api/magazines`);
  }

  public getFields(issn: string): Observable<ScienceField[]> {
    return this.http.get<ScienceField[]>(`http://localhost:8090/api/scienceFields/magazine/${issn}`);
  }

  public createAppication(formData: FormData): Observable<Application> {
    return this.http.post<Application>(`http://localhost:8090/api/applications`, formData);
  }
}
