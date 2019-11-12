import { Document } from './document';
import { Error } from './error';

export interface RestResponse {
    error: Error;
    data: Document;
}

export class RestResponseResolved {
    restResponse: RestResponse;
    error?: any;
}