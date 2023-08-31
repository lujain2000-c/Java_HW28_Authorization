package com.example.applestore;

import com.example.applestore.Model.Customer;
import com.example.applestore.Model.Details;
import com.example.applestore.Repository.AuthRepository;
import com.example.applestore.Repository.DetailsRepository;
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
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE )
public class DetailsRepositoryTest {


    @Autowired
    AuthRepository authRepository;

    @Autowired
    DetailsRepository detailsRepository;

    Customer customer;

    Details details1;
    Details details;


    @BeforeEach
    void setUp(){
        customer = new Customer(null,"lujain","1234","CUSTOMER",null,null);
        authRepository.save(customer);
        details1 = new Details(null,"lujain2gmail.com","lll",20000.3,customer);
        detailsRepository.save(details);
    }
    @Test
    public void findCustomerByUsernameTest(){
        details = detailsRepository.findDetailsById(customer.getId());

        Assertions.assertThat(details).isEqualTo(details1);
    }
}
