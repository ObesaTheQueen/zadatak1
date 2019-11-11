import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {TableModule} from 'primeng/table';
import { HttpClientModule } from '@angular/common/http';
import {TooltipModule} from 'primeng/tooltip';


import { BrowserAnimationsModule } from "@angular/platform-browser/animations";  

import { AccordionModule } from 'primeng/components/accordion/accordion';  
import {OrderListModule} from 'primeng/orderlist'; 
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    TableModule,
    HttpClientModule,
    BrowserAnimationsModule,
    AccordionModule,
    OrderListModule,
    TooltipModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
