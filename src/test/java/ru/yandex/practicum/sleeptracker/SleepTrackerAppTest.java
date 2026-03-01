package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.sleepFunction.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SleepTrackerAppTest {
    SleepingSessionResult ssr;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    public void testBadSessionZero() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 23:00;GOOD"));
        sessions.add(new SleepingSession("02.01.25 23:00;03.01.25 23:00;GOOD"));
        ssr = new FindBadSession().function(sessions);
        Assertions.assertEquals("0", ssr.getResult()[0]);
    }

    @Test
    public void testBadSession() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 09:00;GOOD"));
        sessions.add(new SleepingSession("02.01.25 23:00;03.01.25 09:00;BAD"));
        ssr = new FindBadSession().function(sessions);
        Assertions.assertEquals("1", ssr.getResult()[0]);
    }

    @Test
    public void testAverageTimeOneSession() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 09:00;GOOD"));
        ssr = new FindAverageSessionDuration().function(sessions);
        Assertions.assertEquals("10.0", ssr.getResult()[0]);
    }

    @Test
    public void testAverageTimeMultySession() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 09:00;GOOD"));
        sessions.add(new SleepingSession("02.01.25 23:00;03.01.25 11:00;BAD"));
        ssr = new FindAverageSessionDuration().function(sessions);
        Assertions.assertEquals("11.0", ssr.getResult()[0]);
    }

    @Test
    public void testCountSessionsForOneSession() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 09:00;GOOD"));
        ssr = new CalculateSession().function(sessions);
        Assertions.assertEquals("1", ssr.getResult()[0]);
    }

    @Test
    public void testCountSessionsForMultySession() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 09:00;GOOD"));
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 09:00;GOOD"));
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 09:00;GOOD"));
        ssr = new CalculateSession().function(sessions);
        Assertions.assertEquals("3", ssr.getResult()[0]);
    }

    @Test
    public void testMaxSessionDurationForOneSession() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 09:00;GOOD"));
        ssr = new FindMaxSessionDuration().function(sessions);
        Assertions.assertEquals("10.0", ssr.getResult()[0]);
    }

    @Test
    public void testMaxSessionDurationForMultySession() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 09:00;GOOD"));
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 10:00;GOOD"));
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 11:00;GOOD"));
        ssr = new FindMaxSessionDuration().function(sessions);
        Assertions.assertEquals("12.0", ssr.getResult()[0]);
    }

    @Test
    public void testMinSessionDurationForOneSession() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 09:00;GOOD"));
        ssr = new FindMinSessionDuration().function(sessions);
        Assertions.assertEquals("10.0", ssr.getResult()[0]);
    }

    @Test
    public void testMinSessionDurationForMultySession() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 09:00;GOOD"));
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 10:00;GOOD"));
        sessions.add(new SleepingSession("01.01.25 23:00;02.01.25 11:00;GOOD"));
        ssr = new FindMinSessionDuration().function(sessions);
        Assertions.assertEquals("10.0", ssr.getResult()[0]);
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
        Assertions.assertEquals(String.valueOf(Chronotypes.Сова), ssr.getResult()[0]);
    }

    @Test
    public void testClassificationLarkSession() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 21:30;02.01.25 06:00;GOOD"));
        ssr = new DetermineUserClassification().function(sessions);
        Assertions.assertEquals(String.valueOf(Chronotypes.Жаворонок), ssr.getResult()[0]);
    }

    @Test
    public void testClassificationPigeonSession() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 21:30;02.01.25 12:00;GOOD"));
        ssr = new DetermineUserClassification().function(sessions);
        Assertions.assertEquals(String.valueOf(Chronotypes.Голубь), ssr.getResult()[0]);
    }

    @Test
    public void testClassificationOwlAndLarkSession() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        sessions.add(new SleepingSession("01.01.25 23:30;02.01.25 10:00;GOOD"));
        sessions.add(new SleepingSession("01.01.25 21:30;02.01.25 06:00;GOOD"));
        ssr = new DetermineUserClassification().function(sessions);
        Assertions.assertEquals(String.valueOf(Chronotypes.Голубь), ssr.getResult()[0]);
    }

    @Test
    public void testWhereFileEmpty() throws IOException {
        System.setOut(new PrintStream(outContent));
        String[] args = {"src/main/resources/empty_file.txt"};
        SleepTrackerApp.main(args);
        Assertions.assertEquals("Файл пустой!" + System.lineSeparator(), outContent.toString());
        System.setOut(originalOut);
    }


    @Test
    public void testWhereSessionAfterZero() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        try(Reader fileReader = new FileReader("src/main/resources/session_after_zero_test.txt");
            BufferedReader br = new BufferedReader(fileReader);) {
            br.lines().forEach(line -> sessions.add(new SleepingSession(line)));
        } catch (IOException exception){
            System.out.println("Ошибка считываемого файла: " + exception.getMessage());
        }
        Assertions.assertEquals("6", new CalculateSession().function(sessions).getResult()[0]);
    }

    @Test
    public void testWhereSessionTransferInMonth() {
        List<SleepingSession> sessions = new ArrayList<SleepingSession>();
        try(Reader fileReader = new FileReader("src/main/resources/session_transfer_month_test.txt");
            BufferedReader br = new BufferedReader(fileReader);) {
            br.lines().forEach(line -> sessions.add(new SleepingSession(line)));
        } catch (IOException exception){
            System.out.println("Ошибка считываемого файла: " + exception.getMessage());
        }
        Assertions.assertEquals("10", new CalculateSession().function(sessions).getResult()[0]);
    }
}