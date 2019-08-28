import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';
import { CamundaService } from 'src/app/services/camunda.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(
    private loginService: LoginService,
    private router: Router,
    private camundaService: CamundaService
  ) { }

  ngOnInit() {}

  logout() {
    this.loginService.logout();
  }
}
