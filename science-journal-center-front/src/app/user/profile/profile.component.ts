import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { LoginService } from 'src/app/services/login.service';
import { IssuePurchase } from 'src/app/models/issue-purchase';
import { PaperPurchase } from 'src/app/models/paper-purchase';
import { SubscriptionPurchase } from 'src/app/models/subscription-purchase';
import { PurchaseService } from 'src/app/services/purchase.service';
import { TaskService } from 'src/app/services/task.service';
import { Task } from 'src/app/models/task';
import { TestObject } from 'protractor/built/driverProviders';

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
  
  constructor(
    private loginService: LoginService,
    private purchaseService: PurchaseService,
    private taskService: TaskService
  ) { }

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

}
