export class IssuePurchase {
    constructor(
        public id: string,
        public timeOfPurchase: Date,
        public user: string,
        public successful: string,
        public paymentOption: number,
        public issuePK: number,
        public amount: number,
        public currency: string
    ) {}
}
