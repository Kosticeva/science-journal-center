export class Magazine {
    constructor(
        public issn: string,
        public name: string,
        public type: string,
        public membership: number,
        public currency: string,
        public editor: number,
        public fields: string[],
        public options: string[]
    ) {}
}
