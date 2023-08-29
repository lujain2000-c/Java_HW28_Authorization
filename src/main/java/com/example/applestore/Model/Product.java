package com.example.applestore.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "name should be not empty")
    @Size(min = 3 ,message = "name is too short")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;
    @NotNull(message = "quantity should be not empty")
    @PositiveOrZero
    @Column(columnDefinition = "int default 1")
    private Integer quantity;
    @NotNull(message = "price should be not empty")
    @Positive
    @Column(columnDefinition = "int default 1")
    private Double price;
    @Size(min = 3 ,message = "name is too short")
    private String color;
    @NotEmpty(message = "category should be not empty")
    @Column(columnDefinition = "varchar(20) not null check (category = 'iphone' || category = 'ipad' || category = 'airpods' ||  category = 'watch' || category = 'mac' || category = 'tv')")
    private String category;
    private Boolean isAvailable;
    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    @JsonIgnore
    private Order order;
}
