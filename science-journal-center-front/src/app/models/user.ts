export class User {
    constructor(
        public id: number,
        public fName: string,
        public lName: string,
        public city: string,
        public country: string,
        public email: string,
        public latitude: number,
        public longitude: number
    ) {}
}
