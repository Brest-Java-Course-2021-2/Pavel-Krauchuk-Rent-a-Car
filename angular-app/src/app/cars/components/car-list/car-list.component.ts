import {Component, OnInit} from '@angular/core';
import {CarService} from '../../../services/car.service';
import {CarDto} from '../../../model/car-dto';
import {Router} from '@angular/router';

@Component({
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.scss']
})
export class CarListComponent implements OnInit {

  dtos;

  constructor(
    private carService: CarService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.dtos = this.carService.getCarDTOs();
  }

  onEdit(carDto: CarDto): void {
    const link = ['/cars/edit', carDto.carId];
    this.router.navigate(link);
  }

  onAdd(): void {
    const link = ['/cars/add'];
    this.router.navigate(link);
  }
}
