package com.ecommerce.FullStackEcommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sku")
    private String sku;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    @Column(name = "image_url")
    private String imageURL;
    @Column(name = "active")
    private boolean active;
    @Column(name = "units_in_stock")
    private int unitsInStocks;
    @Column(name = "date_created")
    @CreationTimestamp
    private LocalDate dateCreated;
    @Column(name = "last_updated")
    @UpdateTimestamp
    private LocalDate lastUpdated;

    @ManyToOne
    @JoinColumn(name = "category_id")
    ProductCategory productCategory;
}
