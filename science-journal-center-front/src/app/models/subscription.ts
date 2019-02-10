export class Subscription {
    constructor(
        public id: number,
        public user: string,
        public magazine: string,
        public date: Date,
        public paid?: boolean,
        public cancelled?: boolean,
        public type?: string
    ) {}
}
