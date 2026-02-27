package ru.yandex.practicum.sleeptracker.sleepFunction;

import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.SleepingSessionResult;

import java.util.List;

public class FindBadSession implements SleepAnalyzerFunction {
    @Override
    public SleepingSessionResult function(List<SleepingSession> sleepSession) {
        long count = sleepSession.stream()
                .filter(session -> session.getSleepQuality() == SleepQuality.BAD)
                .count();
        return new SleepingSessionResult("За все время количество сессий с плохим сном было: %s",
                String.valueOf(count));
    }
}
