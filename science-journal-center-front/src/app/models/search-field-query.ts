export class SearchFieldQuery {
    constructor(
        public term: string,
        public field: string,
        public must?: string
    ) {
        this.must = "DA";
    }
}
