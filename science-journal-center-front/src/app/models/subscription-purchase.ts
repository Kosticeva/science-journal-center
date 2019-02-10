export class SubscriptionPurchase {
    constructor(
        public id: string,
        public timeOfPurchase: Date,
        public user: string,
        public paymentOption: number,
        public subscription: number,
        public amount: number,
        public currency: string,
        public successful?: string
    ) {}
}
