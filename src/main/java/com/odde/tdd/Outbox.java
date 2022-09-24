package com.odde.tdd;

public interface Outbox {
    void send(Email mail);
}
