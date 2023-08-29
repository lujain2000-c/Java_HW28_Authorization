package com.example.applestore.Controller;

import com.example.applestore.Model.Customer;
import com.example.applestore.Serviec.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/get_all_orders")
    public ResponseEntity getAllOrders(){
       return ResponseEntity.status(200).body(orderService.allOrders());
    }

    @GetMapping("/get_customer_order")
    public ResponseEntity getCustomerOrders(@AuthenticationPrincipal Customer customer){
        return ResponseEntity.status(200).body( orderService.allOrdersByCustomer(customer.getId()));
    }
}

