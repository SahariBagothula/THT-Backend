package com.example.Credmarg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Credmarg.model.Vendor;
import com.example.Credmarg.repository.VendorRepository;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    public void saveVendor(Vendor vendor) {
        vendorRepository.save(vendor);
    }

    public Vendor getVendor(Long id) {
        Optional<Vendor> optionalVendor = vendorRepository.findById(id);
        if (optionalVendor.isPresent()) {
            return optionalVendor.get();
        } else {
            System.out.println("No vendor found with ID: " + id);
            return null;
        }
    }

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public void updateVendor(Vendor vendor) {
        Optional<Vendor> optionalVendor = vendorRepository.findById(vendor.getId());
        if (optionalVendor.isPresent()) {
            Vendor existingVendor = optionalVendor.get();
            existingVendor.setName(vendor.getName());
            existingVendor.setEmail(vendor.getEmail());
            existingVendor.setUpi(vendor.getUpi());
            vendorRepository.save(existingVendor);
        } else {
            throw new RuntimeException("Vendor not found with id: " + vendor.getId());
        }
    }

    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }

    public Vendor getVendorByEmail(String email) {
        // Convert email to lowercase for case-insensitive lookup
        email = email.toLowerCase();
        System.out.println("Looking up vendor for email: " + email);

        // Use VendorRepository to find vendor by email
        return vendorRepository.findByEmail(email)
                .orElse(null);
    }

}
