export class PaperPurchase {
    constructor(
        public id: string,
        public timeOfPurchase: Date,
        public user: string,
        public successful: string,
        public paymentOption: number,
        public paper: string,
        public amount: number,
        public currency: string
    ) {}
}
