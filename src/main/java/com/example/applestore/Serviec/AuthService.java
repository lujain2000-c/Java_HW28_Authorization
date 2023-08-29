package com.example.applestore.Serviec;

import com.example.applestore.Model.Customer;
import com.example.applestore.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public void register(Customer customer){

        String hash = new BCryptPasswordEncoder().encode(customer.getPassword());
        customer.setPassword(hash);
        customer.setRole("customer");
        authRepository.save(customer);
    }
}
