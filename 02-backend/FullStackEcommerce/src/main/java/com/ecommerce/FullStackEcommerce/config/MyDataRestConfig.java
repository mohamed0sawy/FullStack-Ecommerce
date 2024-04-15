package com.ecommerce.FullStackEcommerce.config;

import com.ecommerce.FullStackEcommerce.entity.Country;
import com.ecommerce.FullStackEcommerce.entity.Product;
import com.ecommerce.FullStackEcommerce.entity.ProductCategory;
import com.ecommerce.FullStackEcommerce.entity.State;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfigurer;
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
    public MyDataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] UnsupportedActions = {HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PUT};

        // disable : PUT, POST, DELETE for Product
        disableHttpMethods(config.getExposureConfiguration()
                .forDomainType(Product.class), UnsupportedActions);

        // disable : PUT, POST, DELETE for ProductCategory
        disableHttpMethods(config.getExposureConfiguration()
                .forDomainType(ProductCategory.class), UnsupportedActions);

        // disable : PUT, POST, DELETE for Country
        disableHttpMethods(config.getExposureConfiguration()
                .forDomainType(Country.class), UnsupportedActions);

        // disable : PUT, POST, DELETE for State
        disableHttpMethods(config.getExposureConfiguration()
                .forDomainType(State.class), UnsupportedActions);

        exposeIds(config);
    }

    private static void disableHttpMethods(ExposureConfigurer config, HttpMethod[] UnsupportedActions) {
        config
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(UnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(UnsupportedActions));
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        List<Class> entityClasses = new ArrayList<>();
        for (EntityType tempEntity : entities) {
            entityClasses.add(tempEntity.getJavaType());
        }
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
