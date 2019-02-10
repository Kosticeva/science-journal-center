import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, FormBuilder } from '@angular/forms';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { TooltipModule } from 'ngx-bootstrap/tooltip';

import { AppComponent } from './app.component';
import { LoginComponent } from './user/login/login.component';
import { RegisterComponent } from './user/register/register.component';
import { SearchComponent } from './common/search/search.component';
import { HomeComponent } from './common/home/home.component';
import { NavbarComponent } from './common/navbar/navbar.component';
import { NewMagazineComponent } from './magazine/new-magazine/new-magazine.component';
import { MagazineComponent } from './magazine/magazine/magazine.component';
import { NewPaperComponent } from './magazine/new-paper/new-paper.component';
import { PaperComponent } from './magazine/paper/paper.component';
import { IssueComponent } from './magazine/issue/issue.component';
import { NewIssueComponent } from './magazine/new-issue/new-issue.component';
import { TasksComponent } from './common/tasks/tasks.component';
import { CommentComponent } from './common/comment/comment.component';
import { ProfileComponent } from './user/profile/profile.component';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { BasicAuthInterceptor } from './helpers/basic-auth.interceptor';
import { AuthGuardService, AuthNotGuardService } from './helpers/auth-guard.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    SearchComponent,
    HomeComponent,
    NavbarComponent,
    NewMagazineComponent,
    MagazineComponent,
    NewPaperComponent,
    PaperComponent,
    IssueComponent,
    NewIssueComponent,
    TasksComponent,
    CommentComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AngularFontAwesomeModule,
    TooltipModule.forRoot(),
    AppRoutingModule
  ],
  providers: [
    FormBuilder, 
    { provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptor, multi: true },
    AuthGuardService,
    AuthNotGuardService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }