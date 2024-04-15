import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map, of } from 'rxjs';
import { Country } from '../common/country';
import { State } from 'src/app/common/state';

@Injectable({
  providedIn: 'root',
})
export class FormService {
  constructor(private httpClient: HttpClient) {}

  getCreditCardMonths(): Observable<number[]> {
    let data: number[] = [];
    const startMonth: number = 1;

    for (let theMonth = startMonth; theMonth <= 12; theMonth++) {
      data.push(theMonth);
    }
    return of(data);
  }

  getCreditCardYears(): Observable<number[]> {
    let data: number[] = [];
    const startYear: number = new Date().getFullYear();
    const endYear: number = startYear + 10;
    for (let theYear = startYear; theYear <= endYear; theYear++) {
      data.push(theYear);
    }
    return of(data);
  }

  getCountries(): Observable<Country[]> {
    const url = 'http://localhost:8080/api/countries';
    return this.httpClient
      .get<GetResponseCountries>(url)
      .pipe(map((response) => response._embedded.countries));
  }

  getStates(code: string): Observable<State[]> {
    const url = `http://localhost:8080/api/states/search/findByCountryCode?code=${code}`;
    return this.httpClient
      .get<GetResponseStates>(url)
      .pipe(map((response) => response._embedded.states));
  }
}

interface GetResponseCountries {
  _embedded: {
    countries: Country[];
  };
}

interface GetResponseStates {
  _embedded: {
    states: State[];
  };
}
