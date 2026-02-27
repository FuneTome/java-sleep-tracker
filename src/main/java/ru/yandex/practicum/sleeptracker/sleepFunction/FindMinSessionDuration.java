package ru.yandex.practicum.sleeptracker.sleepFunction;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.SleepingSessionResult;

import java.util.List;

public class FindMinSessionDuration implements SleepAnalyzerFunction {
    private static double SECOND_TO_HOURS = 60 * 60;
    private static double SECOND_TO_MINUTES = 60;

    @Override
    public SleepingSessionResult function(List<SleepingSession> sleepSession) {
        long min = sleepSession.stream()
                .mapToLong(SleepingSession::getDuration)
                .reduce(Integer.MAX_VALUE, Math::min);
        return new SleepingSessionResult("Самая короткая сессия длилась %.2s часов (%.2s минут)",
                String.valueOf(min / SECOND_TO_HOURS), String.valueOf(min / SECOND_TO_MINUTES));
    }
}
