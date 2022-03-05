import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {CCarOrdersRoutingModule} from './carOrders-routing.module';
import {CarOrderListComponent} from './components/carOrder-list/carOrder-list.component';
import {CarOrdersComponent} from './carOrders.component';


@NgModule({
  declarations: [CarOrderListComponent, CarOrdersComponent],
  imports: [
    CommonModule,
    CarOrdersRoutingModule
  ]
})
export class CarOrderModule { }
