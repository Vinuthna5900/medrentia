package com.example.server.controller;

import com.example.server.model.Equipment;
import com.example.server.repository.EquipmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
@CrossOrigin
public class EquipmentController {

    private final EquipmentRepository repo;

    public EquipmentController(EquipmentRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Equipment> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Equipment add(@RequestBody Equipment equipment) {
        return repo.save(equipment);
    }
}