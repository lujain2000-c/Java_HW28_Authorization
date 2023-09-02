package com.example.applestore.Controller;

import com.example.applestore.Api.ApiResponse;
import com.example.applestore.Model.Customer;
import com.example.applestore.Model.Product;
import com.example.applestore.Serviec.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("/get")
    public ResponseEntity getAllProduct(){
        return ResponseEntity.status(200).body(productService.allProducts());
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody @Valid Product product){
        productService.addProduct(product);
        return ResponseEntity.status(200).body(new ApiResponse("added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id,@RequestBody @Valid Product product){
        productService.updateProduct(id,product);
        return ResponseEntity.status(200).body(new ApiResponse("updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProuduct(@PathVariable Integer id){
        productService.deleteProduct(id);
        return ResponseEntity.status(200).body(new ApiResponse("deleted"));
    }

    @PostMapping("/buy")
    public ResponseEntity buyProduct(@AuthenticationPrincipal Customer customer,@PathVariable Integer product_id){
       productService.buyProduct(customer.getId(),product_id);
        return ResponseEntity.status(200).body(new ApiResponse("ok"));
    }

}
