package com.example.applestore.Serviec;

import com.example.applestore.Api.ApiException;
import com.example.applestore.Model.Customer;
import com.example.applestore.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final AuthRepository authRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = authRepository.findCustomerByUsername(username);
        if(customer == null){
            throw new ApiException("not found");
        }
        return customer;
    }
}
