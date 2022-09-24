package com.odde.tdd;

import java.util.Date;

public class SystemTimeProvider implements TimeProvider {
    public SystemTimeProvider() {
    }

    @Override
    public Date getDate() {
        return new Date();
    }
}