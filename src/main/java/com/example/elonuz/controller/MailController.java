package com.example.elonuz.controller;

import com.example.elonuz.mail.MailtrapService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import java.io.IOException;

@Controller
@RequestMapping("/mail")
public class MailController {
    @GetMapping("/sendMail")
    public String sendMail() throws IOException, MessagingException {
        MailtrapService mailService = new MailtrapService();

        String recipientEmail = "recipient@gmail.com";
        String subject = "This is Subject For Test Message";
        String body = """
                <div>
                <h1 style="color:red;">Body of mail here</h1>
                <img src="data:image/jpg;base64,%s" width=1000 >
                </div>
                """.formatted(MailtrapService.getImageAsBase64());

        mailService.sendEmail(recipientEmail, subject, body);

        return "mailSent";
    }
}
