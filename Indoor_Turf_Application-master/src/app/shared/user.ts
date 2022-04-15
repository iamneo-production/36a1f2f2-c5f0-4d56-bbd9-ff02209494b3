import { booking } from "./booking";

export class User {
	id!: number;
	email! : string;
	password! : string;
    userName!: string;
	mobile!: string;
	role!:string;
	bookings!:String[];
}

