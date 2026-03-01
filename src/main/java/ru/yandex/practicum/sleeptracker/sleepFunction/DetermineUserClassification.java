package ru.yandex.practicum.sleeptracker.sleepFunction;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.SleepingSessionResult;

import java.util.List;
import static java.lang.Math.max;

public class DetermineUserClassification implements SleepAnalyzerFunction {
    @Override
    public SleepingSessionResult function(List<SleepingSession> sleepSession) {
        long countOwl = sleepSession.stream()
                .filter(SleepingSession::isOwl)
                .count();
        long countLark = sleepSession.stream()
                .filter(SleepingSession::isLark)
                .count();
        long countDayAndSleepless = sleepSession.stream()
                .filter(sesseion -> sesseion.getIsDaySession() && sesseion.getIsSleeplessNight())
                .count();
        long countPigeon = sleepSession.size() - (countOwl + countLark - countDayAndSleepless);
        long mx = max(max(countOwl, countLark), countPigeon);
        if (mx == countPigeon || countLark == countOwl) {
            return new SleepingSessionResult("Данный тип пользователя относится к категории \"%s\"",
                    String.valueOf(Chronotypes.Голубь));
        } else if (mx == countOwl) {
            return new SleepingSessionResult("Данный тип пользователя относится к категории \"%s\"",
                    String.valueOf(Chronotypes.Сова));
        } else {
            return new SleepingSessionResult("Данный тип пользователя относится к категории \"%s\"",
                    String.valueOf(Chronotypes.Жаворонок));
        }
    }
}