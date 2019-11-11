import { Person } from './person';
import { Error } from './error';

export interface RestResponse {
    error: Error;
    data: Person[];
}

export class RestResponseResolved {
    restResponse: RestResponse;
    error?: any;
}