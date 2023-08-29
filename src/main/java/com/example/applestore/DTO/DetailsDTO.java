package com.example.applestore.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class DetailsDTO {

    private Integer customer_id;
    @NotEmpty(message = "email should be not empty")
    @Email(message = "email is invalid")
    private String email;
    @NotEmpty(message = "address should be not empty")
    private String address;
    @NotNull(message = "balance should be not empty")
    @PositiveOrZero
    private Double balance;
}
