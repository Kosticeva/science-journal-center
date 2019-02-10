export class Order {
    constructor(
        public merchantOrderId: string,
        public merchantTimestamp: Date,
        public payerId: string,
        public merchantId: string,
        public amount: number
    ) {}
}
