export class Task {
    constructor(
        public id: number,
        public user: string,
        public deadline: Date,
        public application: number,
        public summary: string,
        public type: string,
        public finished: boolean
    ) {}
}
