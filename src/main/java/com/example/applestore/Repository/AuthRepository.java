package com.example.applestore.Repository;


import com.example.applestore.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Customer,Integer> {

    Customer findCustomerById(Integer id);
    Customer findCustomerByUsername(String username);

}
