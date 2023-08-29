package com.example.applestore.Controller;

import com.example.applestore.Api.ApiResponse;
import com.example.applestore.DTO.DetailsDTO;
import com.example.applestore.Model.Customer;
import com.example.applestore.Serviec.DetailsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/vi/details")
@RequiredArgsConstructor
public class DetailsController {

    private final DetailsService detailsService;

    @GetMapping("/get/{id}")
    public ResponseEntity getDetails(@AuthenticationPrincipal Customer customer){
        return ResponseEntity.status(200).body(detailsService.getDetails(customer.getId()));
    }


    @PostMapping("/add")
    public ResponseEntity addDetails(@AuthenticationPrincipal Customer customer, @RequestBody @Valid DetailsDTO detailsDTO){

        detailsService.addDetails(customer.getId(),detailsDTO);
        return ResponseEntity.status(200).body(new ApiResponse("added"));

    }

    @PutMapping("/update")
    public ResponseEntity updateDetails(@AuthenticationPrincipal Customer customer,@RequestBody @Valid DetailsDTO detailsDTO){
        detailsService.updateDetails(customer.getId(),detailsDTO);
        return ResponseEntity.status(200).body(new ApiResponse("updated"));
    }
    @DeleteMapping("/delete")
    public ResponseEntity deletedCustomer(@AuthenticationPrincipal Customer customer) {
        detailsService.deleteDetails(customer.getId());
        return ResponseEntity.status(200).body(new ApiResponse("deleted"));

    }

}
