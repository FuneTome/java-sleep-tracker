package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.time.Duration.between;

public class SleepingSession {
    private final LocalDateTime startSessionTime;
    private final LocalDateTime endSessionTime;
    private final SleepQuality sleepQuality;

    private boolean isSleeplessNight = false;
    private boolean isDaySession = false;

    private final LocalTime timeStartSleeplessNight = LocalTime.of(0, 0);
    private final LocalTime timeFinishSleeplessNight = LocalTime.of(6, 0);
    private final LocalTime timeStartDaySession = LocalTime.of(12, 0);
    private final LocalTime timeFinishDaySession = LocalTime.of(20, 0);
    private final LocalTime timeStartOwlSession = LocalTime.of(23, 0);
    private final LocalTime timeFinishOwlSession = LocalTime.of(9, 0);
    private final LocalTime timeStartLarkSession = LocalTime.of(22, 0);
    private final LocalTime timeFinishLarkSession = LocalTime.of(7, 0);

    SleepingSession(String sleepLog) {
        String[] tokens = sleepLog.split(";");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

        startSessionTime = LocalDateTime.parse(tokens[0], formatter);
        endSessionTime = LocalDateTime.parse(tokens[1], formatter);
        sleepQuality = SleepQuality.valueOf(tokens[2]);

        isSleeplessNight();
        isDaySession();
    }

    public SleepQuality getSleepQuality() {
        return sleepQuality;
    }

    public long getDuration() {
        return between(startSessionTime, endSessionTime).getSeconds();
    }

    public void isSleeplessNight() {
        isSleeplessNight =
                startSessionTime.isAfter(LocalDateTime.of(endSessionTime.toLocalDate(), timeFinishSleeplessNight)) ||
                endSessionTime.isBefore(LocalDateTime.of(startSessionTime.toLocalDate(), timeStartSleeplessNight));
    }

    public void isDaySession() {
        isDaySession =
                (startSessionTime.toLocalTime().isAfter(timeStartDaySession) &&
                 startSessionTime.toLocalTime().isBefore(timeFinishDaySession)) ||
                 (endSessionTime.toLocalTime().isBefore(timeFinishDaySession) &&
                  endSessionTime.toLocalTime().isAfter(timeStartDaySession));
    }

    public boolean getIsSleeplessNight() { return isSleeplessNight; }

    private boolean isOwl() {
        boolean isOwl;
        boolean sessionLimit = endSessionTime.toLocalTime().isAfter(timeFinishOwlSession) &&
                endSessionTime.toLocalTime().isBefore(timeStartDaySession);
        if (startSessionTime.getDayOfYear() == endSessionTime.getDayOfYear()) {
            isOwl = startSessionTime.isAfter(LocalDateTime.of(startSessionTime.toLocalDate().minusDays(1),
                    timeStartOwlSession));
        } else {
            isOwl = startSessionTime.isAfter(LocalDateTime.of(startSessionTime.toLocalDate(), timeStartOwlSession));
        }
        return sessionLimit && isOwl;
    }

    public boolean isLark() {
        if (startSessionTime.getDayOfYear() == endSessionTime.getDayOfYear()) { return false; }
        return startSessionTime.toLocalTime().isAfter(timeFinishDaySession) &&
                startSessionTime.toLocalTime().isBefore(timeStartLarkSession) &&
                endSessionTime.toLocalTime().isBefore(timeFinishLarkSession);
    }

    public int whatClassification() {
        if (isSleeplessNight && isDaySession) {
            return -2;
        } else if (isOwl()) {
            return 1;
        } else if (isLark()) {
            return -1;
        } else {
            return 0;
        }
    }
}
