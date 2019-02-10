import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Credentials } from '../models/credentials';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient
  ) { }

  createCredentials(credentials: Credentials): Observable<Credentials> {
    return this.http.post<Credentials>("http://localhost:8090/api/credentials", credentials);
  }

  createUser(user: User): Observable<User> {
    return this.http.post<User>("http://localhost:8090/api/users", user);
  }
}
