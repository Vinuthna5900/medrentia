package com.example.server.controller;

import com.example.server.model.Rental;
import com.example.server.repository.RentalRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin
public class CustomerController {

    private final RentalRepository rentalRepository;

    // ✅ Constructor Injection
    public CustomerController(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    // ✅ Test API
    @GetMapping("/test")
    public String customerTest() {
        return "Customer access granted";
    }

    // ✅ Get rental history
    @GetMapping("/rentals")
    public List<Rental> getMyRentals() {
        return rentalRepository.findAll();
    }
}