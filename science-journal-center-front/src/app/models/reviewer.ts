export class Reviewer {
    constructor(
        public id: number,
        public title: string,
        public user: string,
        public magazines: string[],
        public fields: string[]
    ) {}
}
