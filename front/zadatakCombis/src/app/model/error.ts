export class Error {
    errorId: string;
    errorMsg: string;
}

export class ErrorResolved {
    err: Error;
    error?: any;
}
