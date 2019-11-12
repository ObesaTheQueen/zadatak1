import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RestResponse } from '../model/restResponse';
import { environment } from '../../environments/environment';
import { catchError, tap } from 'rxjs/operators';
import { throwError } from 'rxjs';
import {Message} from 'primeng/components/common/api';

@Injectable({
    providedIn: 'root'
  })

export class RestService {
  constructor(private http: HttpClient) {}
  msgs: Message[] = [];
  public loadPersonData(): Observable<RestResponse> {
    return this.http.get<RestResponse>(environment.apiUrl + '/persons/load').pipe(
      tap(data => console.log(data)),
      catchError(this.handleError)
    );
  }

  private handleError(err) {
    let errorMessage: string;
    if ( !err || err == null) {
      errorMessage = `Dogodila se greška u sustavu.`;
      this.msgs.push({severity: 'error', summary: 'Greška', detail: errorMessage});
    }
    if (err instanceof ErrorEvent) {
      errorMessage = `An error occurred: ${err.message}`;
      this.msgs.push({severity: 'error', summary: 'Greška', detail: errorMessage});
    } else {
      errorMessage = `Dogodila se greška u sustavu.`;
    }

    return throwError(errorMessage);
  }
}
