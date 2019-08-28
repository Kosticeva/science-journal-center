import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CamundaService {

  CAMUNDA_URL: string = 'http://localhost:8090/rest/'

  constructor(private http: HttpClient) {}

  startProcess(processKey: string): Observable<any> {
    return this.http.post(this.CAMUNDA_URL + `process-definition/key/${processKey}/start`, {});
  }

  startProcessWithVariables(processKey: string, variableList: any[]): Observable<any> {
    
    let variable = {};
    for(let v of variableList) {
      variable[v.name] = { "value": v.value};
    }

    return this.http.post(this.CAMUNDA_URL + `process-definition/key/${processKey}/start`, variable);
  }

  getTasksForProcessId(processId: String): Observable<any[]> {
    return this.http.get<any[]>(this.CAMUNDA_URL + `task?processInstanceId=${processId}`);
  }

  getTasksFromUser(user: String) : Observable<any>{
    return this.http.get<any[]>(this.CAMUNDA_URL + `task?assignee=${user}`);
  }

  getTask(taskId: string): Observable<any> {
    return this.http.get<any[]>(this.CAMUNDA_URL + `task/${taskId}`);
  }

  getTaskForm(taskId: string): Observable<any> {

    let headers = new HttpHeaders({
          'Content-Type': 'application/json'
      });
    return this.http.get(this.CAMUNDA_URL + `task/${taskId}/rendered-form`, {headers: headers, responseType: 'text'});
  }

  submitTaskForm(formFields: any[], taskId: string): Observable<any> {

    let variables = {};
    for(let ff of formFields) {
      variables[ff.key] = { "value" : ff.value };
    }

    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.http.post(this.CAMUNDA_URL + `task/${taskId}/complete`, JSON.stringify({variables}), {headers: headers});
  }
}
