package ru.yandex.practicum.sleeptracker.sleepFunction;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.SleepingSessionResult;

import java.util.List;

public class FindMaxSessionDuration implements SleepAnalyzerFunction {
    private static double SECOND_TO_HOURS = 60 * 60;
    private static double SECOND_TO_MINUTES = 60;

    @Override
    public SleepingSessionResult function(List<SleepingSession> sleepSession) {
        long max = sleepSession.stream()
                .mapToLong(SleepingSession::getDuration)
                .reduce(Integer.MIN_VALUE, Math::max);
        return new SleepingSessionResult("Самая продолжительная сессия длилась %.2s часов (%.2s минут)",
                String.valueOf(max / SECOND_TO_HOURS), String.valueOf(max / SECOND_TO_MINUTES));
    }
}