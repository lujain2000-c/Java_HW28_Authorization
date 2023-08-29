package com.example.applestore.Serviec;

import com.example.applestore.Api.ApiException;
import com.example.applestore.Model.Customer;
import com.example.applestore.Model.Order;
import com.example.applestore.Model.Product;
import com.example.applestore.Repository.AuthRepository;
import com.example.applestore.Repository.OrderRepository;
import com.example.applestore.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final AuthRepository authRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;
    public List<Order> allOrders(){///only admin

        return orderRepository.findAll();
    }

    public List<Order> allOrdersByCustomer(Integer customer_id){
        Customer customer = authRepository.findCustomerById(customer_id);

      return  orderRepository.findByCustomer(customer);
    }

//    public void NewOrder(Customer customer,Product product){
//
//        Order order = new Order();
//        order.setProducts((Set<Product>) product);
//        order.setCustomer(customer);
//        order.setName(product.getName());
//        order.setPrice(product.getPrice());
//        order.setColor(product.getColor());
//        orderRepository.save(order);
//
//    }


}
