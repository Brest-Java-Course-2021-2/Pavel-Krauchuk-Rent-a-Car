import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PageNotFoundComponent} from "./components";

const routes: Routes = [
  {
    path: '',
    redirectTo: '/cars',
    pathMatch: 'full'
  },
  {
    path: 'departments',
    loadChildren: () => import('./cars/cars.module').then(m => m.CarsModule)
  },
  {
    path: 'orderCar',
    loadChildren: () => import('./orderCars/orderCars.module').then(m => m.OrderCarsModule)
  },
  {
    path: '**',
    component: PageNotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
