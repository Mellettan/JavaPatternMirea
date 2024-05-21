package ru.mirea.pract_15.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "products")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
public class Product {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "products_id", sequenceName = "products_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;
}
