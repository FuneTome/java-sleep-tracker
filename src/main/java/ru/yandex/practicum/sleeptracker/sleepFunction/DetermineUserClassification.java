package ru.yandex.practicum.sleeptracker.sleepFunction;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.SleepingSessionResult;

import java.util.List;
import static java.lang.Math.max;

public class DetermineUserClassification implements SleepAnalyzerFunction {
    @Override
    public SleepingSessionResult function(List<SleepingSession> sleepSession) {
        long countOwl = sleepSession.stream()
                .filter(session -> session.whatClassification() == 1)
                .count();
        long countLark = sleepSession.stream()
                .filter(session -> session.whatClassification() == -1)
                .count();
        long countDayAndSleepless = sleepSession.stream()
                .filter(session -> session.whatClassification() == -2)
                .count();
        long countPigeon = sleepSession.size() - (countOwl + countLark - countDayAndSleepless);
        long mx = max(max(countOwl, countLark), countPigeon);
        if (mx == countPigeon || countLark == countOwl) {
            return new SleepingSessionResult("Данный тип пользователя относится к категории \"%s\"",
                    "Голубь");
        } else if (mx == countOwl) {
            return new SleepingSessionResult("Данный тип пользователя относится к категории \"%s\"",
                    "Сова");
        } else {
            return new SleepingSessionResult("Данный тип пользователя относится к категории \"%s\"",
                    "Жаворонок");
        }
    }
}