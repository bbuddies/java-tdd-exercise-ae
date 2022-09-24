package com.odde.tdd;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NowTest {
    @Test
    void now_string() throws ParseException {
        //Arrange
        TimeProvider timeProvider = mock(TimeProvider.class);
        when(timeProvider.getDate()).thenReturn(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse("2022-09-24 02:10:20.555"));
        Now now =new Now(timeProvider);
        //Act
        String nowString = now.getString();
        //Assert
        assertEquals("2022-09-24 02:10:20.555", nowString);
    }

    public class StubTimeProvider implements TimeProvider {

        @Override
        public Date getDate() {
            try {
                return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse("2022-09-24 02:10:20.555");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

