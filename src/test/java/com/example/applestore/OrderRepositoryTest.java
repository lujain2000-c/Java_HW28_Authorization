package com.example.applestore;

import com.example.applestore.Model.Customer;
import com.example.applestore.Model.Order;
import com.example.applestore.Repository.AuthRepository;
import com.example.applestore.Repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryTest {

    @Autowired
    AuthRepository authRepository;

    @Autowired
    OrderRepository orderRepository;

   Order order1;
   List<Order> orders;
    Customer customer;
    @BeforeEach
    void setUp(){
        customer = new Customer(null,"lujain","1234","CUSTOMER",null,null);
        authRepository.save(customer);
        order1 = new Order(null,"ipad",2500.7,"white",null,customer);

    }

    @Test
    public void findByCustomerTest(){

        orderRepository.save(order1);

        orders = orderRepository.findByCustomer(customer);

        Assertions.assertThat(orders.get(0).getCustomer().getId()).isEqualTo(customer.getId());
    }



}
