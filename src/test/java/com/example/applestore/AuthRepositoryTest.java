package com.example.applestore;

import com.example.applestore.Model.Customer;
import com.example.applestore.Repository.AuthRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthRepositoryTest {

    @Autowired
    AuthRepository authRepository;

    Customer customer1;
    Customer customer;
    @BeforeEach
    void setUp(){
        customer1 = new Customer(null,"lujain","1234","CUSTOMER",null,null);


    }

    @Test
    public void findCustomerByIdTest(){
        authRepository.save(customer1);
      customer = authRepository.findCustomerById(customer1.getId());

        Assertions.assertThat(customer).isEqualTo(customer1);
    }


    @Test
    public void findCustomerByUsernameTest(){
        authRepository.save(customer1);
        customer = authRepository.findCustomerByUsername(customer1.getUsername());

        Assertions.assertThat(customer).isEqualTo(customer1);
    }
}
