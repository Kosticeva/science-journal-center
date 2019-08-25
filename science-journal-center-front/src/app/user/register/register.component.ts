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
    this.fields = [];

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

    this.camundaService.startProcess("Registration", [{ "key": "initiator", "value": "Jelena"}]).subscribe(
      (data) => {
        //console.log(data);
        this.camundaService.getTasksForProcessId(data.id).subscribe(
          (data1) => {
              let task = data1[0];
              this.taskId = task.id;
              this.camundaService.getTaskForm(task.id).subscribe(
                (data2) => {
                  for(let d in data2) {
                    let dd = data2[d];
                    const type = data2[d]["type"];

                    if(type != 'Null') {
                      const label = this.parseLabel(d);
                      dd["label"] = label;
                      dd["key"] = d;
                      this.fields.push(dd);
                    }
                  }
                }
              )
          }
        )
      }
    )
  }

  parseLabel(key: string): string {
    return key.replace(/([A-Z])/g, ' $1').trim();
  }

  register() {
    this.camundaService.submitTaskForm(this.fields, this.taskId).subscribe(
      (data) => {
            this.router.navigate(["login"]);
          },
          (error) => {
            alert("Error with create user");
          }
    );
  }
}
