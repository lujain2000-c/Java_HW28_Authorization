package com.example.applestore;

import com.example.applestore.Model.Customer;
import com.example.applestore.Model.Product;
import com.example.applestore.Repository.AuthRepository;
import com.example.applestore.Repository.ProductRepository;
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
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    AuthRepository authRepository;
    Customer customer;
    Product product1;
    Product product2;
    Product product;



    @BeforeEach
    void setUp(){
        customer = new Customer(null,"lujain","1234","CUSTOMER",null,null);
        authRepository.save(customer);
        product1 = new Product(null,"iphone13",10,3500.0,"black","iphone",true,null);
        product2 = new Product(null,"iphone14",20,4000.90,"black","iphone",true,null);

    }


    @Test
    public void findProductById(){
        productRepository.save(product1);
        product = productRepository.findProductById(product1.getId());
        Assertions.assertThat(product).isEqualTo(product1);
    }


}
