import { SearchFieldQuery } from "./search-field-query";

export class SearchQuery {
    constructor(
        public queriesByFields: SearchFieldQuery[],
        public operatorsBetweenFields: string,
        public size?: number
    ) {}
}
