import { Component, OnInit } from '@angular/core';
import { Credentials } from 'src/app/models/credentials';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentials: Credentials;

  constructor(
    private loginService: LoginService,
    private router: Router
  ) { }

  ngOnInit() {
    this.credentials = {
      username: "",
      password: "",
      userDetails: null
    }
  }

  login() {
    this.loginService.login(this.credentials).subscribe(
      (data) => {
        this.loginService.saveToken(data.value);
        this.router.navigate(['home']);
      },
      error => {
        alert("Error happened when login");
      }
    )
  }
}
