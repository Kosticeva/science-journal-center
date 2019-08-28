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
import { CamundaService } from 'src/app/services/camunda.service';

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
    private router: Router,
    private camundaService: CamundaService
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

    this.camundaService.getTasksFromUser(this.loginService.getUser()).subscribe(
      (data) => {
        this.tasks = data;
      }
    )

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
  }

  submitFinishedTask(taskId: string) {
    this.router.navigate([`/task/${taskId}`]);
  }

  download(file: string) {
    alert("download!");
  }

}
