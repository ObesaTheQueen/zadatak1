export class Person {
    personId: number;
    lastName: string;
    firstName: string;
    zipCode: number;
    city: string;
    phone: string;
    errorMsgs: string[];
    exists: boolean;
  }

export class PersonResolved {
    person: Person;
    error?: any;
}