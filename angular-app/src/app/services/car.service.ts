import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {CarDto} from "../model/car-dto";
import {catchError, map, tap} from 'rxjs/operators';
import {Car} from "../model/car";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CarService {

  private basePath = environment.basePath;

  constructor(protected httpClient: HttpClient) {
  }

  public getCarDTOs(): Observable<Array<CarDto>> {
    return this.httpClient.get<Array<CarDto>>(`${this.basePath}/car-dtos`)
      .pipe(
        map(response => response || []),
        tap(response => console.log(JSON.stringify(response))),
        catchError(this.handleError)
      );
  }

  public findCarById(id: number | string): Observable<Car> {
    return this.httpClient.get<Car>(`${this.basePath}/cars/${encodeURIComponent(String(id))}`);
  }

  public createCar(car: Car): Observable<number> {
    if (car === null || car === undefined) {
      throw new Error(
        'Required parameter car was null or undefined when calling createCar.'
      );
    }

    // let headers = new HttpHeaders();
    // headers.append('Accept', 'application/json');
    // headers.append('Content-Type', 'application/json');
    return this.httpClient.post<number>(`${this.basePath}/cars`, car);
  }

  public updateCar(car: Car): Observable<number> {
    if (car === null || car === undefined) {
      throw new Error(
        'Required parameter car was null or undefined when calling updateCar.'
      );
    }
    return this.httpClient.put<number>(`${this.basePath}/Cars`, car);
  }

  public deleteCar(id: number): Observable<number> {
    if (id === null || id === undefined) {
      throw new Error(
        'Required parameter id was null or undefined when calling deleteCar.'
      );
    }
    return this.httpClient.delete<number>(`${this.basePath}/Cars/${encodeURIComponent(String(id))}`);
  }

  private handleError(err: HttpErrorResponse): Observable<never> {
    let errorMessage: string;

    // A client-side or network error occurred.
    if (err.error instanceof Error) {
      errorMessage = `An error occurred: ${err.error.message}`;
    }
      // The backend returned an unsuccessful response code.
    // The response body may contain clues as to what went wrong,
    else {
      errorMessage = `Backend returned code ${err.status}, body was: ${err.error}`;
    }
    console.error(errorMessage);
    return Observable.throw(errorMessage);
  }
}
