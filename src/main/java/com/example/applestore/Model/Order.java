package com.example.applestore.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "name should be not empty")
    @Size(min = 3 ,message = "name is too short")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;
    @NotNull(message = "price should be not empty")
    @Positive
    @Column(columnDefinition = "int default 1")
    private Double price;
    @Size(min = 3 ,message = "name is too short")
    private String color;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "order")
    private Set<Product> products;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;


}
