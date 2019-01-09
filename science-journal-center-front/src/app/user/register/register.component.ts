import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  formData: any;
  taskId: string;

  constructor(
    private http: HttpClient
  ) { }

  ngOnInit() {
    this.http.get('http://localhost:8090/api/credentials/form')
      .subscribe(
        (data) => {
          this.formData = data;
        }
      )
  }

  submit(){
    console.log(this.formData);
    this.http.post('http://localhost:8090/api/credentials/form/'+this.taskId, this.formData, { headers: { 'Content-Type': 'application/json'}})
      .subscribe(
        (data) => {
          alert("OK");
        }
      )
  }

}
