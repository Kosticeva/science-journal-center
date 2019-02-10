import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserService } from 'src/app/services/user.service';
import { Credentials } from 'src/app/models/credentials';
import { User } from 'src/app/models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  credentials: Credentials;
  user: User;

  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit() {
    this.credentials = {
      userDetails: null,
      username: "",
      password: ""
    };
    this.user = {
       city: "",
       country: "",
       fName: "",
       lName: "",
       email: "",
       id: null,
       latitude: null,
       longitude: null
    }
  }

  register() {
    this.userService.createUser(this.user).subscribe(
      (data) => {
        this.credentials.userDetails = data.id;
        this.userService.createCredentials(this.credentials).subscribe(
          (data) => {
            this.router.navigate(["login"]);
          },
          (error) => {
            alert("Error with create credentials");
          }
        )
      },
      (error) => {
        alert("Error with create user");
      }
    )
  }
}
