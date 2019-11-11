import { Component, OnInit } from '@angular/core';
import { RestService } from './shared/rest.service';
import { Person } from './model/person';
import { RestResponse } from './model/restResponse';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'zadatakCombis';
  personsResponse: RestResponse;
  tableData: Person[];
  errorMessage;
  toolTipCheck = '2012';
  cols: any[];
  constructor(private restService: RestService) { }

  ngOnInit() {

      this.restService.loadPersonData().subscribe(
        data => {console.log(data), this.personsResponse = data; this.tableData = data.data; },
        error => this.errorMessage = error as any
        );

      this.cols = [
          { field: 'lastName', header: 'lastName'},
          { field: 'firstName', header: 'firstName' },
          { field: 'zipCode', header: 'zipCode'},
          { field: 'city', header: 'city' },
          { field: 'phone', header: 'phone' },
          { field: 'errorMsgs', header: 'error' }
      ];
  }
}
