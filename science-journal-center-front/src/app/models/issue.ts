export class Issue {
    constructor(
        public id: number,
        public magazine: string,
        public edition: string,
        public date: Date,
        public price: number
    ) {}
}
