import { Roles } from "./roles";

export class Userd {
    userId!: string;
    email!: string;
    username!: string;
    password!: string;
    mobile!:string;
    roles!:Roles[];
    bookings!:string[];
    constructor(){}
}
