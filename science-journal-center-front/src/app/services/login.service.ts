import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { JwtHelperService } from "@auth0/angular-jwt";
import { Credentials } from "../models/credentials";
import { User } from "../models/user";

@Injectable({ providedIn: "root" })
export class LoginService {
  currentUser: String;
  tokenParameter: string = `token`;

  constructor(private http: HttpClient) {}

  login(credentials: Credentials): Observable<any> {
    return this.http.post<any>(`http://localhost:8090/api/login`, credentials);
  }

  getPrincipal(): Observable<User> {
    return this.http.get<User>(`http://localhost:8090/api/login`);
  }

  logout() {
    localStorage.removeItem(this.tokenParameter);
  }

  saveToken(token: string) {
    localStorage.setItem(this.tokenParameter, token);
    this.setUser();
  }

  setUser() {
    this.currentUser = this.getUser();
  }

  getUser(): string {
    return this.parseToken(localStorage.getItem(this.tokenParameter));
  }

  parseToken(token: string): string {
    const helper = new JwtHelperService();
    const decodedToken = helper.decodeToken(token);
    return decodedToken === null ? null : decodedToken.sub;
  }
}