import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RestResponse } from '../model/restResponse';
import { environment } from '../../environments/environment';
import { catchError, tap } from 'rxjs/operators';
import { throwError } from 'rxjs';
@Injectable({
    providedIn: 'root'
  })

export class RestService {
  constructor(private http: HttpClient) {}



  public loadPersonData(): Observable<RestResponse> {
    return this.http.get<RestResponse>(environment.apiUrl + '/persons/load').pipe(
      tap(data => console.log(data)),
      catchError(this.handleError)
    );
  }

  private handleError(err) {
    let errorMessage: string;
    if(!err || err == null){
      errorMessage = `Dogodila se nepredviđena greška u sustavu.`;
    }
    if (err instanceof ErrorEvent) {
      errorMessage = `An error occurred: ${err.message}`;
    } else {
      errorMessage = `Dogodila se nepredviđena greška u sustavu.`;
    }

    return throwError(errorMessage);
  }
}