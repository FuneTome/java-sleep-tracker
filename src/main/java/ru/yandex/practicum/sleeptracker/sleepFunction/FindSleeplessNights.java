package ru.yandex.practicum.sleeptracker.sleepFunction;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.SleepingSessionResult;

import java.util.List;

public class FindSleeplessNights implements SleepAnalyzerFunction {
    @Override
    public SleepingSessionResult function(List<SleepingSession> sleepSession) {
        long count = sleepSession.stream()
                .filter(SleepingSession::getIsSleeplessNight)
                .count();
        return new SleepingSessionResult("За все время количество бессонных ночей было: %s", count);
    }
}
