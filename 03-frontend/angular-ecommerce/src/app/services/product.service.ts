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

  getProductList(categoryId: number): Observable<Product[]> {
    const url = `${this.baseUrl}/search/findByCategoryId?id=${categoryId}`;
    return this.httpclient
      .get<GetResponse>(url)
      .pipe(map((response) => response._embedded.products));
  }

  getProductCategories(): Observable<ProductCategory[]> {
    return this.httpclient
      .get<GetResponseProductCategory>(this.categoryUrl)
      .pipe(map((response) => response._embedded.productCategory));
  }

  searchProducts(value: string): Observable<Product[]> {
    const searchUrl = `${this.baseUrl}/search/findByNameContaining?name=${value}`;
    return this.httpclient
      .get<GetResponse>(searchUrl)
      .pipe(map((response) => response._embedded.products));
  }

  getProduct(productID: number): Observable<Product> {
    const productUrl = `${this.baseUrl}/${productID}`;
    return this.httpclient.get<Product>(productUrl);
  }
}

interface GetResponse {
  _embedded: {
    products: Product[];
  };
}

interface GetResponseProductCategory {
  _embedded: {
    productCategory: ProductCategory[];
  };
}
