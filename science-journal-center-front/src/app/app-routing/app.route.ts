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

export const routes: Routes = [
 {
    path: 'login',
    component: LoginComponent
 },
 {
     path: 'register',
     component: RegisterComponent
 },
 {
     path: 'profile/:id',
     component: ProfileComponent
 },
 {
     path: 'magazine/:id',
     component: MagazineComponent
 },
 {
    path: 'magazine/new',
    component: NewMagazineComponent
 },
 {
     path: 'paper/:id',
     component: PaperComponent
 },
 {
    path: 'paper/new',
    component: NewPaperComponent
 },
 {
     path: 'issue/:magazine/:edition',
     component: IssueComponent
 },
 {
     path: 'issue/new',
     component: NewIssueComponent
 },
 {
     path: 'tasks',
     component: TasksComponent
 },
 {
     path: 'comment/:id',
     component: CommentComponent
 },
 {
     path: 'search',
     component: SearchComponent
 },
 {
     path: 'home',
     component: HomeComponent
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