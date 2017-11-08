package util;

import app.util.LocalDateTimeUtil;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LocalDateTimeUtilTest {


    @Test
    public void testFromWithDate() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.toString());
        LocalDateTime localDateTime = LocalDateTimeUtil.from("20170501", "yyyyMMdd");
        assertNotNull(localDateTime);
        assertEquals("Assert Year", localDateTime.getYear(), 2017);
        assertEquals("Assert Month", localDateTime.getMonth(), Month.MAY);
        assertEquals("Assert Day", localDateTime.getDayOfMonth(), 1);
    }

    @Test
    public void testFromWithDateTime() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.toString());
        LocalDateTime localDateTime = LocalDateTimeUtil.from("20170501T101010", "yyyyMMdd'T'HHmmss");
        assertNotNull(localDateTime);
        assertEquals("Assert Year", localDateTime.getYear(), 2017);
        assertEquals("Assert Month", localDateTime.getMonth(), Month.MAY);
        assertEquals("Assert Day", localDateTime.getDayOfMonth(), 1);
        assertEquals("Assert Hour", localDateTime.getHour(), 10);
        assertEquals("Assert Minute", localDateTime.getMinute(), 10);
        assertEquals("Assert Second", localDateTime.getSecond(), 10);
    }

    @Test
    public void testFromWithDateTimeAndNano() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.toString());
        LocalDateTime localDateTime = LocalDateTimeUtil.from("20170501T101010111", "yyyyMMdd'T'HHmmssSSS");
        assertNotNull(localDateTime);
        assertEquals("Assert Year", localDateTime.getYear(), 2017);
        assertEquals("Assert Month", localDateTime.getMonth(), Month.MAY);
        assertEquals("Assert Day", localDateTime.getDayOfMonth(), 1);
        assertEquals("Assert Hour", localDateTime.getHour(), 10);
        assertEquals("Assert Minute", localDateTime.getMinute(), 10);
        assertEquals("Assert Second", localDateTime.getSecond(), 10);
        assertEquals("Assert Nano", localDateTime.getNano(), 111000000);
    }

    @Test
    public void testFromWithDifferenceFormat() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.toString());
        LocalDateTime localDateTime = LocalDateTimeUtil.from("20170501", "yyyy-MM-dd");
        assertNotNull(localDateTime);
        assertEquals("Assert Year", localDateTime.getYear(), 2017);
        assertEquals("Assert Year", localDateTime.getMonth(), now.getMonth());
        assertEquals("Assert Year", localDateTime.getDayOfYear(), now.getDayOfYear());
    }
}
