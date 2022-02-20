import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {CarsRoutingModule} from './cars-routing.module';
import {CarListComponent} from './components/car-list/car-list.component';
import {CarFormComponent} from './components/car-form/car-form.component';
import {CarsComponent} from './cars.component';
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [CarListComponent, CarFormComponent, CarsComponent],
  imports: [
    CommonModule,
    FormsModule,
    CarsRoutingModule
  ]
})
export class CarsModule { }
