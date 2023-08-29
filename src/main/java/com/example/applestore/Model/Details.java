package com.example.applestore.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(30) not null unique")
    private String email;
    @Column(columnDefinition = "varchar(100) not null ")
    private String address;
    @Column(columnDefinition = "int default 0 ")
    private Double balance;

    @OneToOne
    @MapsId //take id frome customer
    @JsonIgnore// Alwase put it في التابع
    private Customer customer;
}
