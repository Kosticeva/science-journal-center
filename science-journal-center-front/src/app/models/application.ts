export class Application {
    constructor(
        public paperId: number,
        public version: number,
        public title: string,
        public paperAbstract: string,
        public keyWords: string,
        public author: string,
        public coauthors: number[],
        public magazine: string,
        public field: string,
        public file: string,
        public state: string,
        public accepted: boolean,
        public timestamp: Date
    ) {}
}
