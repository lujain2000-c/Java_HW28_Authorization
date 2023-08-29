package com.example.applestore.Serviec;

import com.example.applestore.Api.ApiException;
import com.example.applestore.Model.Customer;
import com.example.applestore.Model.Details;
import com.example.applestore.Model.Order;
import com.example.applestore.Model.Product;
import com.example.applestore.Repository.AuthRepository;
import com.example.applestore.Repository.DetailsRepository;
import com.example.applestore.Repository.OrderRepository;
import com.example.applestore.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final AuthRepository authRepository;
    private final DetailsRepository detailsRepository;
   // private final OrderService orderService;
    public List<Product> allProducts(){
        return productRepository.findAll();
    }

    public void addProduct(Product product){
        productRepository.save(product);

    }

    public void updateProduct(Integer id, Product product){

        Product product1 = productRepository.findProductById(id);

        if (product1 == null){
            throw new ApiException("not found");

        }

        product1.setName(product.getName());
        product1.setCategory(product.getCategory());
        product1.setColor(product.getColor());
        product1.setPrice(product.getPrice());
        product1.setQuantity(product.getQuantity());
        productRepository.save(product1);

    }

    public void deleteProduct(Integer id){
        Product product = productRepository.findProductById(id);

        if (product == null){
            throw new ApiException("not found");

        }

        productRepository.delete(product);
    }

    public void buyProduct(Integer customer_id, Integer product_id){
        Customer customer = authRepository.findCustomerById(customer_id);
        Product product = productRepository.findProductById(product_id);
        Details details = detailsRepository.findDetailsById(customer_id);

        if (product.getQuantity() == 0 || product == null){
            throw new ApiException("not available");
        }
        if (product.getPrice() > details.getBalance()){
            throw new ApiException("you do not have enough money");

        }

        product.setQuantity(product.getQuantity()-1);
        details.setBalance(details.getBalance()-product.getPrice());
        productRepository.save(product);
        authRepository.save(customer);
        if (product.getQuantity() == 0){
            product.setIsAvailable(false);
            productRepository.save(product);
        }

        Order order = new Order();
        order.setProducts((Set<Product>) product);
        order.setCustomer(customer);
        order.setName(product.getName());
        order.setPrice(product.getPrice());
        order.setColor(product.getColor());
        orderRepository.save(order);
        //orderService.NewOrder(customer,product);

    }
}
