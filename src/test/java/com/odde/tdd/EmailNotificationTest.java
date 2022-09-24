package com.odde.tdd;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class EmailNotificationTest {
    @Test
    void send_welcome_email() {
        //Arrange
        Outbox mockOutbox = mock(Outbox.class);
        EmailNotification notification = new EmailNotification(mockOutbox);
        //Act
        notification.welcome("a@gmail.com");
        //Assert
        ArgumentCaptor<Email> captor = ArgumentCaptor.forClass(Email.class);
        verify(mockOutbox).send(captor.capture());
        Email email = captor.getValue();
        assertEquals("Welcome", email.getTitle());
    }

    private class MockOutbox implements Outbox {
        private boolean called;
        private Email mail;

        @Override
        public void send(Email mail) {
            this.called = true;
            this.mail = mail;
        }

        public void verify() {
            assertTrue(this.called);
            assertEquals("Welcome", this.mail.getTitle());
            assertEquals("a@gmail.com", this.mail.getTo());
        }
    }
}