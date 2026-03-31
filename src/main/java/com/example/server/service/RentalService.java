package com.example.server.service;

import com.example.server.model.Rental;
import com.example.server.model.Equipment;
import com.example.server.repository.RentalRepository;
import com.example.server.repository.EquipmentRepository;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RentalService {

    private final RentalRepository rentalRepo;
    private final EquipmentRepository equipmentRepo;

    public RentalService(RentalRepository rentalRepo,
                         EquipmentRepository equipmentRepo) {
        this.rentalRepo = rentalRepo;
        this.equipmentRepo = equipmentRepo;
    }
    // ✅ GET ALL RENTALS
    public List<Rental> findAll() {
        return rentalRepo.findAll();
    }

    // 🔥 SMART BOOKING METHOD
    public Rental createRental(Rental rental) {

        // 1️⃣ Get equipment
        Equipment equipment = equipmentRepo.findById(rental.getEquipmentId())
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        // 2️⃣ Check availability
        if (!equipment.isAvailability()) {
            throw new RuntimeException("Equipment not available");
        }

        // 3️⃣ Calculate days
        long days = ChronoUnit.DAYS.between(
                rental.getStartDate(),
                rental.getEndDate()
        );

        if (days <= 0) {
            days = 1;
        }

        // 4️⃣ Calculate total price
        double totalPrice = days * equipment.getPricePerDay();
        rental.setTotalPrice(totalPrice);

        // 5️⃣ Mark equipment unavailable
        equipment.setAvailability(false);
        equipmentRepo.save(equipment);

        // 6️⃣ Set status
        rental.setStatus("BOOKED");

        // 7️⃣ Save rental
        return rentalRepo.save(rental);
    }
}