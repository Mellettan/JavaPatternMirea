package ru.mirea.pract_15.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "markets")
@Getter
@Setter
public class Market {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "markets_id", sequenceName = "markets_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "markets_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;
}
