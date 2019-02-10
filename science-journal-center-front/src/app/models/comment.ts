export class Comment {
    constructor(
        public id: number,
        public task: number,
        public publicComment: string,
        public privateComment: string,
        public summary: string,
        public timestamp: Date
    ) {}
}
