package com.webapi.tactile.service;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
