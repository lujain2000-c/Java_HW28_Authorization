package com.example.applestore.Repository;

import com.example.applestore.Model.Customer;
import com.example.applestore.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    //Order findOrderById(Integer id);
    //Order findOrderByName(String name);
    List<Order> findByCustomer(Customer customer);
}
