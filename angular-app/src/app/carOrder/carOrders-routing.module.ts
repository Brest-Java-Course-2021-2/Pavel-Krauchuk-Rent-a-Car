import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CarOrdersComponent} from "./carOrders.component";

const routes: Routes = [
  {
    path: '',
    component: CarOrdersComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CarOrdersRoutingModule { }
