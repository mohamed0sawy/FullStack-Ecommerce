package com.ecommerce.FullStackEcommerce.config;

import com.ecommerce.FullStackEcommerce.entity.Product;
import com.ecommerce.FullStackEcommerce.entity.ProductCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;
    @Autowired
    public MyDataRestConfig(EntityManager entityManager){
        this.entityManager = entityManager;
    }

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

        exposeIds(config);
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        List<Class> entityClasses = new ArrayList<>();
        for (EntityType tempEntity : entities){
            entityClasses.add(tempEntity.getJavaType());
        }
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
