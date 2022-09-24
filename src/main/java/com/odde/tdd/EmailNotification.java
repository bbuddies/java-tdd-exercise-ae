package com.odde.tdd;

public class EmailNotification {

    private final Outbox outbox;

    public EmailNotification() {
        outbox = new MailService();
    }

    public EmailNotification(Outbox outbox) {
        this.outbox = outbox;
    }

    public void welcome(String email) {
        Email mail = new Email();
        mail.setFrom("zbcjackson@odd-e.com");
        mail.setTo(email);
        mail.setTitle("Welcome");
        mail.setContent("Hello");
        outbox.send(mail);
    }
}
