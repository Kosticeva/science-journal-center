import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { LoginService } from '../services/login.service';

@Injectable()
export class AuthGuardService implements CanActivate {

  constructor(public auth: LoginService, public router: Router) {}

  canActivate(): boolean {
    if (!this.auth.getUser()) {
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }
}

@Injectable()
export class AuthNotGuardService implements CanActivate {
  constructor(public auth: LoginService, public router: Router) {}

  canActivate(): boolean {
    if (this.auth.getUser()) {
      this.router.navigate(['home']);
      return false;
    }
    return true;
  }
}
