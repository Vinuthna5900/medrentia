package com.example.server.controller;

import com.example.server.model.Equipment;
import com.example.server.repository.EquipmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final EquipmentRepository equipmentRepository;

    public AdminController(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    // ✅ TEST API
    @GetMapping("/test")
    public String adminTest() {
        return "Admin access granted";
    }

    // ✅ GET all equipment
    @GetMapping("/equipment")
    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    // ✅ ADD equipment
    @PostMapping("/equipment")
    public Equipment addEquipment(@RequestBody Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    // ✅ UPDATE equipment
    @PutMapping("/equipment/{id}")
    public Equipment updateEquipment(@PathVariable Long id,
                                     @RequestBody Equipment updatedEquipment) {

        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        equipment.setName(updatedEquipment.getName());
        equipment.setCategory(updatedEquipment.getCategory());
        equipment.setPricePerDay(updatedEquipment.getPricePerDay());
        equipment.setDescription(updatedEquipment.getDescription());
        equipment.setImageUrl(updatedEquipment.getImageUrl());
        equipment.setAvailability(updatedEquipment.isAvailability());

        return equipmentRepository.save(equipment);
    }

    // ✅ DELETE equipment
    @DeleteMapping("/equipment/{id}")
    public String deleteEquipment(@PathVariable Long id) {
        equipmentRepository.deleteById(id);
        return "Equipment deleted";
    }
}