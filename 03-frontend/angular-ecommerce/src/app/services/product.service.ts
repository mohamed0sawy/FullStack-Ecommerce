import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../common/product';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(public httpclient: HttpClient) {}

  private baseUrl: string = 'http://localhost:8080/api/products';

  getProductList(): Observable<Product[]> {
    return this.httpclient
      .get<GetResponse>(this.baseUrl)
      .pipe(map((response) => response._embedded.products));
  }
}

interface GetResponse {
  _embedded: {
    products: Product[];
  };
}
