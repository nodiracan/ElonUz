package com.example.elonuz.mail;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.Properties;

public class MailtrapService {

    private static final String username = "055c87f72db45b";
    private static final String password = "5d562097a714a5";

    public void sendEmail(String recipientEmail, String subject, String body) throws AddressException, MessagingException {

        var properties = MailtrapService.getProperties();
        var session = MailtrapService.getSession(properties, username, password);
        var message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
        message.setSubject(subject);
        var multipart = new MimeMultipart();

        var contentMessage = new MimeBodyPart();
        contentMessage.setContent(body, "text/html");

        multipart.addBodyPart(contentMessage);
        message.setContent(multipart);
        Transport.send(message);
        System.out.println("Message Sent Successfully");
    }
    public static String getImageAsBase64() throws IOException {
        Base64.Encoder encoder = Base64.getEncoder();
        Path path = Path.of("animal.jpg");
        byte[] allBytes = Files.readAllBytes(path);
        String encodeToString = encoder.encodeToString(allBytes);
        Files.writeString(Path.of("imageAstring.txt"), encodeToString, StandardOpenOption.TRUNCATE_EXISTING);
        return encodeToString;
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        properties.put("mail.smtp.port", "2525");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        return properties;
    }


    private static Session getSession(Properties properties, String username, String password) {
        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }
}