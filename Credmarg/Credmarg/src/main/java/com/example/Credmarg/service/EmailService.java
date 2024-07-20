package com.example.Credmarg.service;

import org.springframework.stereotype.Service;

import com.example.Credmarg.model.Email;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {

    private List<Email> sentEmails = new ArrayList<>();

    public void sendEmail(String to, String subject, String body) {
        System.out.println("Sending email to: " + to);
        Email email = new Email(to, subject, body);
        sentEmails.add(email);
        System.out.println("Email sent: " + email);
    }

    public List<Email> getSentEmails() {
        System.out.println("Sent Emails Size: " + sentEmails.size());
        return sentEmails;
    }
}
