import {Component, OnInit} from '@angular/core';
import {Car} from '../../../model/car';
import {CarService} from '../../../services/car.service';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {switchMap} from 'rxjs/operators';

@Component({
  templateUrl: './car-form.component.html',
  styleUrls: ['./car-form.component.scss']
})
export class CarFormComponent implements OnInit {
  car: Car;

  constructor(
    private carService: CarService,
    private router: Router,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.car = new Car();
    const observer = {
      next: (car: Car) => (this.car = {...car}),
      error: (err: any) => console.log(err)
    };
    this.route.paramMap
      .pipe(
        switchMap((params: ParamMap) => {
            const id = params.get('carID');
            console.log(id);
            if (id) {
              return this.carService.findCarById(params.get('carID'));
            }
          }
        )
      )
      .subscribe(observer);
  }

  onSubmit(): void {
    // console.log('onSave ' + JSON.stringify(this.department));
    const car = {...this.car} as Car;

    if (car.carId) {
      this.carService.updateCar(car).subscribe();
    } else {
      this.departmentService.createCar(car).subscribe();
    }

    this.onGoBack();
  }

  onGoBack(): void {
    this.router.navigate(['/cars']);
  }

}
