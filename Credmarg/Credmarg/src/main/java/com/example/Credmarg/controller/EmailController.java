package com.example.Credmarg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Credmarg.model.Email;
import com.example.Credmarg.model.Vendor;
import com.example.Credmarg.service.EmailService;
import com.example.Credmarg.service.VendorService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/emails")
@CrossOrigin(origins = "http://localhost:3000")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private VendorService vendorService;

    @PostMapping("/send")
    public ResponseEntity<List<Email>> sendEmailsToVendors(@RequestParam List<String> vendorEmails) {
        System.out.println("Sending emails to vendors");
        List<Email> sentEmails = new ArrayList<>();
        System.out.println("Received vendorEmails: " + vendorEmails);
        for (String email : vendorEmails) {
            System.out.println("Processing email: " + email);
            Vendor vendor = vendorService.getVendorByEmail(email);
            System.out.println("Vendor: " + vendor);
            if (vendor != null) {
                System.out.println("Sending email to vendor: " + vendor.getName());
                String subject = "Payment Notification";
                String body = "Sending payments to vendor " + vendor.getName() + " at UPI " + vendor.getUpi();
                emailService.sendEmail(email, subject, body);
                sentEmails.add(new Email(email, subject, body));
            }
        }
        return ResponseEntity.ok(sentEmails);
    }

    @GetMapping("/sent")
    public List<Email> getSentEmails() {
        return emailService.getSentEmails();
    }
}
