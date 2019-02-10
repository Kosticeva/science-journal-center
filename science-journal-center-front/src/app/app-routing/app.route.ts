import { Routes } from "@angular/router";
import { LoginComponent } from "../user/login/login.component";
import { RegisterComponent } from "../user/register/register.component";
import { ProfileComponent } from "../user/profile/profile.component";
import { MagazineComponent } from "../magazine/magazine/magazine.component";
import { PaperComponent } from "../magazine/paper/paper.component";
import { IssueComponent } from "../magazine/issue/issue.component";
import { NewMagazineComponent } from "../magazine/new-magazine/new-magazine.component";
import { NewPaperComponent } from "../magazine/new-paper/new-paper.component";
import { NewIssueComponent } from "../magazine/new-issue/new-issue.component";
import { TasksComponent } from "../common/tasks/tasks.component";
import { CommentComponent } from "../common/comment/comment.component";
import { SearchComponent } from "../common/search/search.component";
import { HomeComponent } from "../common/home/home.component";
import { AuthGuardService, AuthNotGuardService } from "../helpers/auth-guard.service";

export const routes: Routes = [
 {
    path: 'login',
    component: LoginComponent,
    canActivate: [AuthNotGuardService]
 },
 {
     path: 'register',
     component: RegisterComponent,
     canActivate: [AuthNotGuardService]
 },
 {
     path: 'profile',
     component: ProfileComponent,
     canActivate: [AuthGuardService]
 },
 {
    path: 'magazine/new',
    component: NewMagazineComponent,
    canActivate: [AuthGuardService]
 },
 {
     path: 'magazine/:id',
     component: MagazineComponent,
     canActivate: [AuthGuardService]
 },
 {
    path: 'paper/new',
    component: NewPaperComponent,
    canActivate: [AuthGuardService]
 },
 {
     path: 'paper/:id',
     component: PaperComponent,
     canActivate: [AuthGuardService]
 },
 {
     path: 'issue/new',
     component: NewIssueComponent,
     canActivate: [AuthGuardService]
 },
 {
     path: 'issue/:magazine/:edition',
     component: IssueComponent,
     canActivate: [AuthGuardService]
 },
 {
     path: 'tasks',
     component: TasksComponent,
     canActivate: [AuthGuardService]
 },
 {
     path: 'comment/:id',
     component: CommentComponent,
     canActivate: [AuthGuardService]
 },
 {
    path: 'search',
    component: SearchComponent,
    canActivate: [AuthGuardService]
 },
 {
     path: 'home',
     component: HomeComponent,
     canActivate: [AuthGuardService]
 },
 {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
 },
 {
    path: '**',
    redirectTo: '/home',
    pathMatch: 'full'
 }
];