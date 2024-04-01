package com.ecommerce.FullStackEcommerce.config;

import com.ecommerce.FullStackEcommerce.entity.Product;
import com.ecommerce.FullStackEcommerce.entity.ProductCategory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] UnsupportedActions = {HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PUT};

        // disable : PUT, POST, DELETE for Product
        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(UnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(UnsupportedActions));

        // disable : PUT, POST, DELETE for ProductCategory
        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(UnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(UnsupportedActions));
    }
}
