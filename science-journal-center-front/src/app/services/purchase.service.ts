import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IssuePurchase } from '../models/issue-purchase';
import { PaperPurchase } from '../models/paper-purchase';
import { SubscriptionPurchase } from '../models/subscription-purchase';
import { Subscription } from '../models/subscription';
import { Issue } from '../models/issue';
import { Paper } from '../models/paper';

@Injectable({
  providedIn: 'root'
})
export class PurchaseService {

  constructor(
    private http: HttpClient
  ) { }

  public getIssuePurchases(): Observable<IssuePurchase[]> {
    return this.http.get<IssuePurchase[]>(`http://localhost:8090/api/issuePurchases/my`);
  }

  public getPaperPurchases(): Observable<PaperPurchase[]> {
    return this.http.get<PaperPurchase[]>(`http://localhost:8090/api/paperPurchases/my`);
  }

  public getSubscriptionPurchases(): Observable<SubscriptionPurchase[]> {
    return this.http.get<SubscriptionPurchase[]>(`http://localhost:8090/api/subscriptionPurchases/my`);
  }

  public getActiveSubscription(issn: string): Observable<SubscriptionPurchase> {
    return this.http.get<SubscriptionPurchase>(`http://localhost:8090/api/subscriptions/magazine/${issn}`);
  }

  public createSubscription(subscription: Subscription): Observable<Subscription> {
    return this.http.post<Subscription>(`http://localhost:8090/api/subscriptions`, subscription);
  }

  public purchaseSubscription(purchase: SubscriptionPurchase): Observable<SubscriptionPurchase> {
    return this.http.post<SubscriptionPurchase>(`http://localhost:8090/api/subscriptionPurchases`, purchase);
  }

  public getIssuesForMagazine(issn: string): Observable<Issue[]> {
    return this.http.get<Issue[]>(`http://localhost:8090/api/issues/${issn}`);
  }

  public getPaper(doi: string): Observable<Paper> {
    return this.http.get<Paper>(`http://localhost:8090/api/papers/${doi}`);
  }

  public buyPaper(doi: string): Observable<string> {
    return this.http.get<string>(`http://localhost:8090/api/paperPurchases/buy/${doi}`);
  }
}
