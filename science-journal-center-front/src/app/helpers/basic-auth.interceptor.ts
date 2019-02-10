import { Injectable } from "@angular/core";
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable()
export class BasicAuthInterceptor implements HttpInterceptor {
  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    let token = localStorage.getItem("token");
    if (token) {
      request = request.clone({
        headers: request.headers.set("Authorization", "Bearer " + token)
      });
    }
    request.headers.append("Content-Type", "application/json");

    return next.handle(request);
  }
}