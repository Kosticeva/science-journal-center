import { Component, OnInit, TemplateRef } from '@angular/core';
import { User } from 'src/app/models/user';
import { LoginService } from 'src/app/services/login.service';
import { IssuePurchase } from 'src/app/models/issue-purchase';
import { PaperPurchase } from 'src/app/models/paper-purchase';
import { SubscriptionPurchase } from 'src/app/models/subscription-purchase';
import { PurchaseService } from 'src/app/services/purchase.service';
import { TaskService } from 'src/app/services/task.service';
import { Task } from 'src/app/models/task';
import { Application } from 'src/app/models/application';
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import { SearchService } from 'src/app/services/search.service';
import { Credentials } from 'src/app/models/credentials';
import { NewPaperService } from 'src/app/services/new-paper.service';
import { Paper } from 'src/app/models/paper';
import { Issue } from 'src/app/models/issue';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  page: number = 0;
  user: User = null;
  issuePurchases: IssuePurchase[] = [];
  paperPurchases: PaperPurchase[] = [];
  subscriptionPurchases: SubscriptionPurchase[] = [];
  tasks: Task[] = [];
  application: Application = null;
  task: Task = null;
  reviewers: Credentials[] = [];
  chosens: string[] = [];
  doi: string;
  price: number;
  currency: string;
  issue: number;
  issues: Issue[];

  constructor(
    private loginService: LoginService,
    private purchaseService: PurchaseService,
    private taskService: TaskService,
    private modalService: NgbModal,
    private searchService: SearchService,
    private paperService: NewPaperService,
    private router: Router
  ) { }

  openModal(template: TemplateRef<any>) {
    this.modalService.open(template, { backdrop: "static" });
  }

  ngOnInit() {
    this.loginService.getPrincipal().subscribe(
      (data) => {
        this.user = data;
      }
    );

    this.purchaseService.getIssuePurchases().subscribe(
      (data) => {
        this.issuePurchases = data;
      }
    );

    this.purchaseService.getPaperPurchases().subscribe(
      (data) => {
        this.paperPurchases = data;
      }
    );

    this.purchaseService.getSubscriptionPurchases().subscribe(
      (data) => {
        this.subscriptionPurchases = data;
      }
    );

    this.taskService.getTasks().subscribe(
      (data) => {
        this.tasks = data;
      }
    )
  }

  viewApplication(id: number, templateRef: TemplateRef<any>) {
    this.taskService.getApplication(id).subscribe(
      (data) => {
        this.application = data;
        this.openModal(templateRef);
      }
    )
  }

  download(file: string) {
    alert("download!");
  }

  finishTask(task: Task, template: TemplateRef<any>) {
    this.task = task;
    this.openModal(template);
    if(this.task.type === "REVIEWER_PROPOSAL") {
      this.taskService.getApplication(this.task.application).subscribe(
        (data) => {
          this.searchService.getReviewers(data).subscribe(
            (data1) => {
              this.reviewers = data1;
            }
          )
        }
      )
    } else if(this.task.type === "REVIEW_ANALYSIS") {
      this.doi = "";
      this.currency = "";
      this.price = 0;
      this.taskService.getApplication(this.task.application).subscribe(
        (data) => {
          this.purchaseService.getIssuesForMagazine(data.magazine).subscribe(
            (data1) => {
              this.issues = data1;
            }
          )
        }
      )
    }
  }

  submitFinishedTask() {
    this.task.finished = true;
    if(this.task.type === "REVIEWER_PROPOSAL") {
      for(let i = 0; i < this.chosens.length; i++){
        let review = new Task(null, this.chosens[i], new Date(new Date(this.task.deadline).getTime() + 2*24*3600), this.task.application, "Recenzija", "REVIEW", false);
        this.taskService.createTask(review).subscribe(
          (data) => {
            console.log(data);
            this.modalService.dismissAll();
          }
        )
      }

      let bigReview = new Task(null, this.loginService.getUser(), new Date(new Date(this.task.deadline).getTime() + 3*24*3600), this.task.application, "Analiziranje recenzija", "REVIEW_ANALYSIS", false);
      this.taskService.createTask(bigReview).subscribe(
        (data) => {
          console.log(data);
          this.tasks.splice(this.tasks.indexOf(this.task), 1);
          this.task = null;
        }
      )
    } else if(this.task.type === "REVIEW_ANALYSIS") {
      this.taskService.getApplication(this.task.application).subscribe(
        (data) => {
          let paper: Paper = {
              doi: this.doi,
              title: data.title,
              author: data.author,
              coauthors: data.coauthors,
              field: data.field,
              file: data.file,
              issue: this.issue,
              keyTerms: data.keyTerms,
              lastRevision: data.paperId,
              paperAbstract: data.paperAbstract,
              price: this.price,
              currency: this.currency
          };
          this.paperService.publishPaper(paper).subscribe(
            (data) => {
              this.modalService.dismissAll();
            }
          )
        }
      )
    }

    this.taskService.updateTask(this.task, this.task.id).subscribe(
      (data) => {
        console.log(data);
        this.tasks.splice(this.tasks.indexOf(this.task), 1);
        this.task = null;
      }
    )
  }

}
