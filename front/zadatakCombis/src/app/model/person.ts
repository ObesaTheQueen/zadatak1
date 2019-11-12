export class Person {
    personId: number;
    lastName: string;
    firstName: string;
    zipCodeString: string;
    city: string;
    phone: string;
    errorMsgs: string[];
    exists: boolean;
  }

export class PersonResolved {
    person: Person;
    error?: any;
}