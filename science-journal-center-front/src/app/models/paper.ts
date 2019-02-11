export class Paper {
    constructor(
        public doi: string,
        public title: string,
        public paperAbstract: string,
        public keyTerms: string,
        public author: string,
        public coauthors: number[],
        public issue: number,
        public field: string,
        public file: string,
        public price: number,
        public currency: string,
        public lastRevision?: number
    ) {}
}
