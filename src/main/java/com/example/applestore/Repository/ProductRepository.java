package com.example.applestore.Repository;


import com.example.applestore.Model.Order;
import com.example.applestore.Model.Product;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    Product findProductById(Integer id);
  List<Product> findProductByOrder(Order order);


}
