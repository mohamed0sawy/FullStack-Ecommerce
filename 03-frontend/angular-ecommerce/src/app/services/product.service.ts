import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../common/product';
import { map } from 'rxjs/operators';
import { ProductCategory } from '../common/product-category';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(public httpclient: HttpClient) {}

  private baseUrl: string = 'http://localhost:8080/api/products';
  private categoryUrl = 'http://localhost:8080/api/category';

  getProductListPaginate(
    pageNumber: number,
    pageSize: number,
    categoryId: number
  ): Observable<GetResponseProducts> {
    const url = `${this.baseUrl}/search/findByCategoryId?id=${categoryId}&page=${pageNumber}&size=${pageSize}`;
    return this.httpclient.get<GetResponseProducts>(url);
  }

  getProductCategories(): Observable<ProductCategory[]> {
    return this.httpclient
      .get<GetResponseProductCategory>(this.categoryUrl)
      .pipe(map((response) => response._embedded.productCategory));
  }

  searchProductsPaginate(
    pageNumber: number,
    pageSize: number,
    value: string
  ): Observable<GetResponseProducts> {
    const searchUrl = `${this.baseUrl}/search/findByNameContaining?name=${value}&page=${pageNumber}&size=${pageSize}`;
    return this.httpclient.get<GetResponseProducts>(searchUrl);
  }

  getProduct(productID: number): Observable<Product> {
    const productUrl = `${this.baseUrl}/${productID}`;
    return this.httpclient.get<Product>(productUrl);
  }
}

interface GetResponseProducts {
  _embedded: {
    products: Product[];
  };
  page: {
    size: number;
    totalElements: number;
    totalPages: number;
    number: number;
  };
}

interface GetResponseProductCategory {
  _embedded: {
    productCategory: ProductCategory[];
  };
}
