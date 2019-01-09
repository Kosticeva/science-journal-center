import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { HttpResponse, HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'science-journal-center-front';
  form: any;
  urlLink = "https://localhost:4200/#/choose-payment/";
  username: string;
  issn: string;
  price: number;
  currency: string;
  merchandise: string;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router) {
    this.form = this.fb.group({
        avatar: null
    });
    this.username = "";
    this.merchandise = "";
    this.issn = "";
    this.currency = "";
    this.price = 0;
  }

  generateSession(){
    this.http.post<any>('https://localhost:8080/sessions', {'username': this.username, 'issn': this.issn, 'merchandise': this.merchandise, 'price': this.price, 'currency': this.currency})
    .subscribe(
      (data) => {
        window.location.href = "https://localhost:4200/#/choose-payment/" + data.token.substring(6, data.token.length);
      }
    )
  }

  

  onFileChange(event) {
    if (event.target.files.length > 0) {
        const file = event.target.files[0];
        this.form.get('avatar').setValue(file);
    }
  }

  private prepareSave(): any {
    const input = new FormData();
    input.append('file', this.form.get('avatar').value);
    return input;
  }

  onSubmit() {
    const formModel = this.prepareSave();
    this.placePhoto(formModel)
      .subscribe((res) => {
        alert(res.body);
      }
    );
  }

  placePhoto(formData: FormData): Observable<HttpResponse<Object>> {
    return this.http.post('http://localhost:8090/api/files', formData, { observe: 'response' });
  }

}
