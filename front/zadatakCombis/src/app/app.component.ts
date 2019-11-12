import { Component, OnInit } from '@angular/core';
import { RestService } from './shared/rest.service';
import { Person } from './model/person';
import { RestResponse } from './model/restResponse';
import { Document } from './model/document';
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
  loadButt = false;
  saveButt = true;
  constructor(private restService: RestService,
              private messageService: MessageService) { }

  ngOnInit() {


  }

  loadPersonData() {
    this.restService.loadPersonData().subscribe(
      data => {
        console.log(data), this.personsResponse = data;

        if (data.error && data.error != null) {
          this.msgs.push({severity: 'error', summary: 'Greška', detail: data.error.errorMsg});
        } else {
          this.tableData = data.data.persons;
          this.saveButt = false;
        }

      },
      error => {this.errorMessage = error as any;
                this.msgs = [];
                this.msgs.push({severity: 'error', summary: 'Greška', detail: this.errorMessage});
      }
      );
    this.msgs = [];
    this.cols = [
        { field: 'lastName', header: 'Prezime', width: '15%'},
        { field: 'firstName', header: 'Ime', width: '15%' },
        { field: 'zipCodeString', header: 'Poštanski broj', width: '15%'},
        { field: 'city', header: 'Grad', width: '15%' },
        { field: 'phone', header: 'Telefon', width: '10%' },
        { field: 'errorMsgs', header: 'Greške', width: '30%' }
    ];
  }

  savePersonData() {

    this.restService.savePersonData(this.personsResponse.data).subscribe(
      data => {
        this.personsResponse = data;
        if (data.error && data.error != null) {
          this.msgs.push({severity: 'error', summary: 'Greška', detail: data.error.errorMsg});
        } else {
          this.messageService.add({key: 'tc', severity: 'warn', summary: 'Poruka upozorenja', 
          detail: 'Podaci su spremljeni!'});
          this.tableData = null;
          this.saveButt = true;
        }
      },
      error => {this.errorMessage = error as any;
                this.msgs = [];
                this.msgs.push({severity: 'error', summary: 'Greška', detail: this.errorMessage});
      }
      );
 
  }
}
