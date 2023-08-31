package com.example.applestore;

import com.example.applestore.Model.Customer;
import com.example.applestore.Model.Details;
import com.example.applestore.Model.Product;
import com.example.applestore.Repository.AuthRepository;
import com.example.applestore.Repository.DetailsRepository;
import com.example.applestore.Repository.ProductRepository;
import com.example.applestore.Serviec.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;
    @Mock
    AuthRepository authRepository;

    @Mock
    DetailsRepository detailsRepository;

    Customer customer;
    Details details;
    Product product1;
    Product product2;
    Product product;
    List<Product> products;

    @BeforeEach
    void setUp(){
        customer = new Customer(null,"lujain","1234","ADMIN",null,null);
        authRepository.save(customer);
        details = new Details(null,"lujain2gmail.com","lll",20000.3,customer);
        detailsRepository.save(details);
        product1 = new Product(null,"iphone13",10,3500.0,"black","iphone",true,null);
        product2 = new Product(null,"iphone14",20,4000.90,"black","iphone",true,null);

        products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

    }

    @Test
    public void getAllProductsTest(){
        when(productRepository.findAll()).thenReturn(products);
        List<Product> productList = productService.allProducts();
        Assertions.assertEquals(productList,products);

        verify(productRepository,times(1)).findAll();
    }

    @Test
    public void addProduct(){
        productService.addProduct(product1);
        verify(productRepository,times(1)).save(product1);
    }

    @Test
    public void updateProduct(){
        when(productRepository.findProductById(product1.getId()));

        productService.updateProduct(product1.getId(),product2);

        verify(productRepository,times(1)).findProductById(product1.getId());
        verify(productRepository,times(1)).save(product2);
    }

    @Test
    public void deleteProduct(){
        when(productRepository.findProductById(product1.getId()));

        productService.deleteProduct(product1.getId());

        verify(productRepository,times(1)).findProductById(product1.getId());
        verify(productRepository,times(1)).delete(product1);

    }

    @Test
    public void buyProduct(){
        when(productRepository.findProductById(product1.getId())).thenReturn(product1);
        when(authRepository.findCustomerById(customer.getId())).thenReturn(customer);
        when(detailsRepository.findDetailsById(customer.getId())).thenReturn(details);

        productService.buyProduct(customer.getId(), product1.getId());

        verify(productRepository,times(1)).findProductById(product1.getId());
        verify(authRepository,times(1)).findCustomerById(customer.getId());
        verify(detailsRepository,times(1)).findDetailsById(customer.getId());
        verify(productRepository,times(2)).save(product1);
        verify(detailsRepository,times(1)).save(details);
    }



}
