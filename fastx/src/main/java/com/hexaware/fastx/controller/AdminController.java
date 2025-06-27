package com.hexaware.fastx.controller;

import com.hexaware.fastx.entity.Booking;
import com.hexaware.fastx.entity.User;
import com.hexaware.fastx.entity.Bus;
import com.hexaware.fastx.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Get all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    // Delete user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    // Get all bus operators
    @GetMapping("/operators")
    public ResponseEntity<List<User>> getAllOperators() {
        return ResponseEntity.ok(adminService.getAllOperators());
    }

    // Delete operator
    @DeleteMapping("/operators/{id}")
    public ResponseEntity<String> deleteOperator(@PathVariable Long id) {
        adminService.deleteOperator(id);
        return ResponseEntity.ok("Operator deleted successfully");
    }

    // Get all bookings
    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(adminService.getAllBookings());
    }

    // Get all buses
    @GetMapping("/buses")
    public ResponseEntity<List<Bus>> getAllBuses() {
        return ResponseEntity.ok(adminService.getAllBuses());
    }

    // Delete a bus
    @DeleteMapping("/buses/{id}")
    public ResponseEntity<String> deleteBus(@PathVariable Long id) {
        adminService.deleteBus(id);
        return ResponseEntity.ok("Bus deleted successfully");
    }
}
