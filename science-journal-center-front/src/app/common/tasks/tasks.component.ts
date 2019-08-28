import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CamundaService } from 'src/app/services/camunda.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  taskId: string;
  taskTitle: string = "";
  taskDue: Date = new Date();
  process: string = "";

  constructor(
    private route: ActivatedRoute,
    private camundaService: CamundaService,
    private router: Router,
    private loginService: LoginService
  ) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.taskId = params.get("id");

      this.camundaService.getTask(this.taskId).subscribe(
        (data) => {
          this.taskTitle = data.name;
          this.process = data.processInstanceId;
          this.taskDue = data.due;
          this.loadForm();
        }
      );
    });
  }

  loadForm() {
    this.camundaService.getTaskForm(this.taskId).subscribe(
      data => {
        let angular6Data = this.convertJSTo2Plus(data);

        let container = document.getElementById("container");
        if(container.firstChild !== undefined && container.firstChild !== null) {
          container.removeChild(container.firstChild);
        }
        let formHtml = angular6Data.firstChild.lastChild.firstChild;

        container.appendChild(formHtml);
      }
    );
  }

  convertJSTo2Plus(jsHtml: string) : Document {
    let parser = new DOMParser();
    let dom = parser.parseFromString(jsHtml, "text/html");
    
    let nodes = dom.getElementsByClassName("help-block");
    while(nodes.length > 0){
      nodes[0].parentNode.removeChild(nodes[0]);
    }

    nodes = dom.getElementsByClassName("has-error");
    while(nodes.length > 0){
      nodes[0].parentNode.removeChild(nodes[0]);
    }

    return dom;
  }

  completeTask() {
    let formFields = [];

    const inputs = document.getElementsByClassName("form-control");
    for(let i = 0; i < inputs.length; i++) {
      const input = inputs[i];
      const readonly = input.getAttribute("readonly");

      if(readonly == null || readonly == undefined) {
        const name = input.getAttribute("name");
        let value = input.value;

        if(input.type=="checkbox") {
          value = input.checked;
        }

        formFields.push(
          {
            "key": name,
            "value": value
          }
        )
      }
    }

    if(this.loginService.getUser() != null && this.loginService.getUser() != undefined) {
      this.camundaService.submitTaskForm(formFields, this.taskId).subscribe(
        (data) => {
          this.router.navigate(["/profile"]);
        },
        (error) => {
          alert(error.error.message);
        }
      );
    } else {
      
      this.camundaService.submitTaskForm(formFields, this.taskId).subscribe(
        (data) => {
          setTimeout( (data) => {
        
            this.camundaService.getTasksForProcessId(this.process).subscribe(
              (data1) => {
                if(data1.length == 0) {
                  this.router.navigate(["/login"]);
                } else {
                  this.router.navigate(["../task/" + data1[0].id]);
                }
              }
            )
          }, 1000);
        },
        (error) => {
          alert(error.error.message);
        }
      )
    }
  }
}
