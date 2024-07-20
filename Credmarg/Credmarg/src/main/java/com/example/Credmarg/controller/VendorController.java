package com.example.Credmarg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Credmarg.model.Vendor;
import com.example.Credmarg.service.VendorService;

@RestController
@RequestMapping("/vendor")
@CrossOrigin(origins = "http://localhost:3000")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @PostMapping("/save")
    public ResponseEntity<String> saveVendor(@RequestBody Vendor vendor) {
        vendorService.saveVendor(vendor);
        return ResponseEntity.status(HttpStatus.CREATED).body("Vendor details saved successfully");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Vendor> getVendor(@PathVariable Long id) {
        Vendor vendor = vendorService.getVendor(id);
        return ResponseEntity.ok(vendor);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Vendor>> getAllVendors() {
        List<Vendor> vendors = vendorService.getAllVendors();
        return ResponseEntity.ok(vendors);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateVendor(@RequestBody Vendor vendor) {
        vendorService.updateVendor(vendor);
        return ResponseEntity.ok("Vendor details updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
        return ResponseEntity.ok("Vendor details deleted successfully");
    }

}
