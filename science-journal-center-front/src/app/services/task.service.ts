import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Task } from '../models/task';
import { HttpClient } from '@angular/common/http';
import { Application } from '../models/application';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(
    private http: HttpClient
  ) { }

  public getTasks(): Observable<Task[]> {
    return this.http.get<Task[]>(`http://localhost:8090/api/tasks/my`);
  }

  public createTask(task: Task): Observable<Task> {
    return this.http.post<Task>(`http://localhost:8090/api/tasks`, task);
  }

  public updateTask(task: Task, id: number): Observable<Task> {
    return this.http.put<Task>(`http://localhost:8090/api/tasks/${id}`, task);
  }

  public getApplication(id: number): Observable<Application> {
    return this.http.get<Application>(`http://localhost:8090/api/applications/${id}`);
  }
}
