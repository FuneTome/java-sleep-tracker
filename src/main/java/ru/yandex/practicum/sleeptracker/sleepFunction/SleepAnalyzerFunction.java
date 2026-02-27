package ru.yandex.practicum.sleeptracker.sleepFunction;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.SleepingSessionResult;

import java.util.List;

public interface SleepAnalyzerFunction {
    SleepingSessionResult function(List<SleepingSession> sleepSession);
}
