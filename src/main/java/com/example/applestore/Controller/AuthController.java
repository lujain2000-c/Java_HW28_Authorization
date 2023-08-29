package com.example.applestore.Controller;


import com.example.applestore.Api.ApiResponse;
import com.example.applestore.Model.Customer;
import com.example.applestore.Serviec.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid Customer customer){
        authService.register(customer);
        return ResponseEntity.status(200).body(new ApiResponse("registered"));

    }
}
