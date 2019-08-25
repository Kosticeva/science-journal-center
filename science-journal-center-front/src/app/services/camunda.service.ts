import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CamundaService {

  CAMUNDA_URL: string = 'http://localhost:8090/rest/'

  constructor(private http: HttpClient) {}

  startProcess(processKey: string, variableList?: any[]): Observable<any> {
    
    let variable = {};
    for(let v of variableList) {
      variable[v.name] = { "value": v.value};
    }

    return this.http.post(this.CAMUNDA_URL + `process-definition/key/${processKey}/start`, variable);
  }

  getTasksForProcessId(processId: String): Observable<any[]> {
    return this.http.get<any[]>(this.CAMUNDA_URL + `task?processInstanceId=${processId}`);
  }

  getTasksFromUser(user: string) {}

  getTaskForm(taskId: string): Observable<any[]> {
    return this.http.get<any[]>(this.CAMUNDA_URL + `task/${taskId}/form-variables`);
  }

  submitTaskForm(formFields: any[], taskId: string): Observable<any> {

    let variables = {};
    for(let ff of formFields) {
      variables[ff.key] = ff;
    }

    return this.http.post(this.CAMUNDA_URL + `task/${taskId}/submit-form`, {variables});
  }

  completeTask() {}
}
