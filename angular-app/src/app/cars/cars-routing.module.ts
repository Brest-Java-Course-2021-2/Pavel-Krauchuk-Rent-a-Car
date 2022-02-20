import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CarListComponent} from "./components";
import {carFormComponent} from "./components/car-form/car-form.component";
import {CarsComponent} from "./cars.component";

const routes: Routes = [
  {
    path: '',
    component: CarsComponent,
    children: [
      {
        path: 'add',
        component: CarFormComponent
      },
      {
        path: 'edit/:carID',
        component: CarFormComponent
      },
      {
        path: '',
        component: CarListComponent,
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CarsRoutingModule { }
