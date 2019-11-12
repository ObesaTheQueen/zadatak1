import { Component, OnInit } from '@angular/core';
import { RestService } from './shared/rest.service';
import { Person } from './model/person';
import { RestResponse } from './model/restResponse';

import {Message} from 'primeng/components/common/api';
import {MessageService} from 'primeng/components/common/messageservice';
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
  msgs: Message[] = [];
  loadButt = true;
  saveButt = true;
  constructor(private restService: RestService) { }

  ngOnInit() {

      this.restService.loadPersonData().subscribe(
        data => {
          console.log(data), this.personsResponse = data;

          if (data.error && data.error != null) {
            this.msgs.push({severity: 'error', summary: 'Greška', detail: data.error.errorMsg});
          } else {
            this.tableData = data.data.persons;
          }

        },
        error => {this.errorMessage = error as any;
                  this.msgs = [];
                  this.msgs.push({severity: 'error', summary: 'Greška', detail: this.errorMessage});
        }
        );

      this.cols = [
          { field: 'lastName', header: 'Prezime'},
          { field: 'firstName', header: 'Ime' },
          { field: 'zipCode', header: 'Poštanski broj'},
          { field: 'city', header: 'Grad' },
          { field: 'phone', header: 'Telefon' },
          { field: 'errorMsgs', header: 'Greške' }
      ];
  }

  loadPersonData() {

  }

  savePersonData() {

  }
}
