package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.sleepFunction.*;


import java.util.ArrayList;
import java.util.List;

public class SleepTrackerAppTest {
    SleepingSessionResult ssr;

    @Test
    public void testBadSessionZero() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 23:00;GOOD"));
        sessions.add(new SleepingSession("02.01.25 23:00;03.01.25 23:00;GOOD"));
        ssr = new FindBadSession().function(sessions);
        Assertions.assertEquals(0L, ssr.getResult(0));
    }

    @Test
    public void testBadSession() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 09:00;GOOD"));
        sessions.add(new SleepingSession("02.01.25 23:00;03.01.25 09:00;BAD"));
        ssr = new FindBadSession().function(sessions);
        Assertions.assertEquals(1L, ssr.getResult(0));
    }

    @Test
    public void testAverageTimeOneSession() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 09:00;GOOD"));
        ssr = new FindAverageSessionDuration().function(sessions);
        Assertions.assertEquals(10.0, ssr.getResult(0));
    }

    @Test
    public void testAverageTimeMultySession() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 09:00;GOOD"));
        sessions.add(new SleepingSession("02.01.25 23:00;03.01.25 11:00;BAD"));
        ssr = new FindAverageSessionDuration().function(sessions);
        Assertions.assertEquals(11.0, ssr.getResult(0));
    }

    @Test
    public void testCountSessionsForOneSession() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 09:00;GOOD"));
        ssr = new CalculateSession().function(sessions);
        Assertions.assertEquals(1, ssr.getResult(0));
    }

    @Test
    public void testCountSessionsForMultySession() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 09:00;GOOD"));
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 09:00;GOOD"));
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 09:00;GOOD"));
        ssr = new CalculateSession().function(sessions);
        Assertions.assertEquals(3, ssr.getResult(0));
    }

    @Test
    public void testMaxSessionDurationForOneSession() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 09:00;GOOD"));
        ssr = new FindMaxSessionDuration().function(sessions);
        Assertions.assertEquals(10.0, ssr.getResult(0));
    }

    @Test
    public void testMaxSessionDurationForMultySession() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 09:00;GOOD"));
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 10:00;GOOD"));
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 11:00;GOOD"));
        ssr = new FindMaxSessionDuration().function(sessions);
        Assertions.assertEquals(12.0, ssr.getResult(0));
    }

    @Test
    public void testMinSessionDurationForOneSession() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 09:00;GOOD"));
        ssr = new FindMinSessionDuration().function(sessions);
        Assertions.assertEquals(10.0, ssr.getResult(0));
    }

    @Test
    public void testMinSessionDurationForMultySession() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 09:00;GOOD"));
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 10:00;GOOD"));
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 11:00;GOOD"));
        ssr = new FindMinSessionDuration().function(sessions);
        Assertions.assertEquals(10.0, ssr.getResult(0));
    }

    @Test
    public void testSleeplessNightSession1() {
        SleepingSession session = new SleepingSession("01.01.25 19:00;02.01.25 05:00;GOOD");
        Assertions.assertFalse(session.getIsSleeplessNight());
    }

    @Test
    public void testSleeplessNightSession2() {
        SleepingSession session = new SleepingSession("01.01.25 01:00;01.01.25 10:00;GOOD");
        Assertions.assertFalse(session.getIsSleeplessNight());
    }

    @Test
    public void testSleeplessNightSession3() {
        SleepingSession session = new SleepingSession("01.01.25 17:00;01.01.25 23:00;GOOD");
        Assertions.assertTrue(session.getIsSleeplessNight());
    }

    @Test
    public void testSleeplessNightSession4() {
        SleepingSession session = new SleepingSession("01.01.25 07:00;01.01.25 11:00;GOOD");
        Assertions.assertTrue(session.getIsSleeplessNight());
    }

    @Test
    public void testClassificationOwlSession() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 23:30;02.01.25 10:00;GOOD"));
        ssr = new DetermineUserClassification().function(sessions);
        Assertions.assertEquals(Chronotypes.Owl, ssr.getResult(0));
    }

    @Test
    public void testClassificationLarkSession() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 21:30;02.01.25 06:00;GOOD"));
        ssr = new DetermineUserClassification().function(sessions);
        Assertions.assertEquals(Chronotypes.Lark, ssr.getResult(0));
    }

    @Test
    public void testClassificationPigeonSession() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 21:30;02.01.25 12:00;GOOD"));
        ssr = new DetermineUserClassification().function(sessions);
        Assertions.assertEquals(Chronotypes.Pigeon, ssr.getResult(0));
    }

    @Test
    public void testClassificationOwlAndLarkSession() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 23:30;02.01.25 10:00;GOOD"));//Сова
        sessions.add(new SleepingSession("01.01.25 23:30;02.01.25 10:00;GOOD"));//Сова
        sessions.add(new SleepingSession("01.01.25 21:30;02.01.25 06:00;GOOD"));//Жаворонок
        sessions.add(new SleepingSession("01.01.25 21:30;02.01.25 06:00;GOOD"));//Жаворонок
        sessions.add(new SleepingSession("01.01.25 23:30;02.01.25 06:00;GOOD"));//Голубь
        ssr = new DetermineUserClassification().function(sessions);
        Assertions.assertEquals(Chronotypes.Pigeon, ssr.getResult(0));
    }

    @Test
    public void testWhereFileEmpty() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        ssr = new CalculateSession().function(sessions);
        Assertions.assertEquals(0, ssr.getResult(0));
    }


    @Test
    public void testWhereSessionAfterZero() {
        List<SleepingSession> session = new ArrayList<SleepingSession>();
        session.add(new SleepingSession("02.10.25 00:10;02.10.25 06:20;GOOD"));
        session.add(new SleepingSession("02.10.25 14:10;02.10.25 15:00;NORMAL"));
        session.add(new SleepingSession("02.10.25 23:50;03.10.25 06:40;NORMAL"));
        ssr = new CalculateSession().function(session);
        Assertions.assertEquals(3, ssr.getResult(0));
    }

    @Test
    public void testWhereSessionTransferInMonth() {
        List<SleepingSession> session = new ArrayList<SleepingSession>();
        session.add(new SleepingSession("29.09.25 23:50;30.09.25 06:40;NORMAL"));
        session.add(new SleepingSession("30.09.25 23:40;01.10.25 08:00;BAD"));
        session.add(new SleepingSession("02.10.25 00:10;02.10.25 06:20;GOOD"));
        ssr = new CalculateSession().function(session);
        Assertions.assertEquals(3, ssr.getResult(0));
    }
}