import { Roles } from "./roles";

export class User {

    userId!: string;
    email!: string;
    username!: string;
    password!: string;
    mobile!:string;
    roles!:string[];
    bookings!:string[];
    constructor(){}
}
