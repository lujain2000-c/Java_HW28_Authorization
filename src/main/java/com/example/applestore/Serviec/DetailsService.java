package com.example.applestore.Serviec;

import com.example.applestore.Api.ApiException;
import com.example.applestore.DTO.DetailsDTO;
import com.example.applestore.Model.Customer;
import com.example.applestore.Model.Details;
import com.example.applestore.Repository.AuthRepository;
import com.example.applestore.Repository.DetailsRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class DetailsService {
    private final AuthRepository authRepository;
    private final DetailsRepository detailsRepository;

    public Details getDetails(Integer id){
        Customer customer = authRepository.findCustomerById(id);
        if(customer == null){
            throw new ApiException("not found");
        }

        return detailsRepository.findDetailsById(id);
    }
    public void addDetails(Integer customer_id, DetailsDTO detailsDTO){
        Customer customer = authRepository.findCustomerById(detailsDTO.getCustomer_id());
        if(customer == null){
            throw new ApiException("not found");
        }
        Details details = new Details(null,detailsDTO.getEmail(),detailsDTO.getAddress(),detailsDTO.getBalance(),customer);

        detailsRepository.save(details);
    }


    public void updateDetails(Integer customer_id,DetailsDTO detailsDTO){
        Customer customer = authRepository.findCustomerById(detailsDTO.getCustomer_id());
        if(customer == null) {
            throw new ApiException("not found");
        }

       Details details = detailsRepository.findDetailsById(customer.getId());
        details.setEmail(detailsDTO.getEmail());
        details.setAddress(detailsDTO.getAddress());
        details.setBalance(detailsDTO.getBalance());

        detailsRepository.save(details);


    }

    public void deleteDetails(Integer customerId ){
        Customer customer = authRepository.findCustomerById(customerId);
        if(customer == null) {
            throw new ApiException("not found");
        }
        Details details = detailsRepository.findDetailsById(customer.getId());
        detailsRepository.delete(details);

    }
}

