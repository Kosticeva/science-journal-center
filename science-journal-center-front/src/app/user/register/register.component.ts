import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserService } from 'src/app/services/user.service';
import { Credentials } from 'src/app/models/credentials';
import { User } from 'src/app/models/user';
import { Router } from '@angular/router';
import { CamundaService } from 'src/app/services/camunda.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  credentials: Credentials;
  user: User;

  fields: any[];
  taskId: string;
  
  constructor(
    private userService: UserService,
    private camundaService: CamundaService,
    private router: Router
  ) { }

  ngOnInit() {
    const initiator = new Date().toDateString();
    this.camundaService.startProcess("Registration").subscribe(
      (data) => {
        this.camundaService.getTasksForProcessId(data.id).subscribe(
          (data1) => {
            this.router.navigate(["/task/"+data1[0].id]);
          }
        )
      }
    )
  }
}
