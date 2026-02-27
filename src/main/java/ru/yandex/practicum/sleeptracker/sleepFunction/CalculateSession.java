package ru.yandex.practicum.sleeptracker.sleepFunction;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.SleepingSessionResult;

import java.util.List;

public class CalculateSession implements SleepAnalyzerFunction {
    @Override
    public SleepingSessionResult function(List<SleepingSession> sleepSession) {
        return new SleepingSessionResult("За весь период было %s сессий сна.",
                String.valueOf(sleepSession.size()));
    }
}
