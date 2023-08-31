package com.example.applestore.Repository;

import com.example.applestore.Model.Customer;
import com.example.applestore.Model.Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsRepository extends JpaRepository<Details,Integer> {

    Details findDetailsById(Integer id);
    //Details findDetailsByCustomer(Customer customer);
}
