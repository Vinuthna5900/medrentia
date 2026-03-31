package com.example.server.controller;

import com.example.server.model.Rental;
import com.example.server.service.RentalService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
@CrossOrigin
public class RentalController {

    private final RentalService rentalService;

public RentalController(RentalService rentalService) {
    this.rentalService = rentalService;
}

    

    @GetMapping
    public List<Rental> getAll() {
        return rentalService.findAll();
    }

    @PostMapping
    public Rental create(@RequestBody Rental rental) {
        return rentalService.createRental(rental);
}
}