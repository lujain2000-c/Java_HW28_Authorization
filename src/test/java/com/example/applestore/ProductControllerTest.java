package com.example.applestore;

import com.example.applestore.Api.ApiResponse;
import com.example.applestore.Controller.ProductController;
import com.example.applestore.Model.Customer;
import com.example.applestore.Model.Details;
import com.example.applestore.Model.Product;
import com.example.applestore.Repository.AuthRepository;
import com.example.applestore.Repository.DetailsRepository;
import com.example.applestore.Repository.ProductRepository;
import com.example.applestore.Serviec.AuthService;
import com.example.applestore.Serviec.DetailsService;
import com.example.applestore.Serviec.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ProductController.class , excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class ProductControllerTest {
    @MockBean
    ProductService productService;

    @MockBean
    AuthService authService;

    @MockBean
    DetailsService detailsService;


    @Autowired
    MockMvc mockMvc;


    Product product1,product2,product3;
    Customer customer;
    Details details;
    ApiResponse apiResponse;

    List<Product> products,productList;

    @BeforeEach
    void setUp() {
        customer = new Customer(null,"lujain","1234","ADMIN",null,null);

        details = new Details(null,"lujain2gmail.com","lll",20000.3,customer);

        product1 = new Product(null,"iphone13",10,3500.0,"black","iphone",true,null);
        product2 = new Product(null,"iphone14",20,4000.90,"black","iphone",true,null);
        products= Arrays.asList(product1);
        productList=Arrays.asList(product2);

    }

    @Test
    public void getAllProducts() throws Exception{
        Mockito.when(productService.allProducts()).thenReturn(products);
        mockMvc.perform(get("/api/v1/product/get"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("product1"));
    }

    @Test
    public void testAddProduct() throws  Exception {
        mockMvc.perform(post("/api/v1/product/add")
                        .contentType(MediaType.APPLICATION_JSON)
                            .content( new ObjectMapper().writeValueAsString(product1)))
                .andExpect(status().isOk());

    }
    @Test
    public void testDeleteProduct() throws Exception{
        mockMvc.perform(delete("/api/v1/product/delete/{id}",product1.getId()))
                .andExpect(status().isOk());

    }
    @Test
    public void updateProductTest() throws Exception{
        mockMvc.perform(put("/api/v1/product/update/{id}",product1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(product2)))
                .andExpect(status().isOk());

    }

    @Test
    public void buyProductTest() throws Exception{
        mockMvc.perform(put("/api/v1/product/buy/{id}",product1.getId()))
                .andExpect(status().isOk());

    }
}
