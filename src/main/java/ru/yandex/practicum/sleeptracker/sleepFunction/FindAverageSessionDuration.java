package ru.yandex.practicum.sleeptracker.sleepFunction;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.SleepingSessionResult;

import java.util.List;

public class FindAverageSessionDuration implements SleepAnalyzerFunction {
    private static double SECOND_TO_HOURS = 60 * 60;
    private static double SECOND_TO_MINUTES = 60;

    @Override
    public SleepingSessionResult function(List<SleepingSession> sleepSession) {
        long sum = sleepSession.stream()
                .mapToLong(SleepingSession::getDuration)
                .reduce(0, Long::sum);
        return new SleepingSessionResult("Средняя продолжительность сна составляет: %.5s часов",
                (sum / SECOND_TO_HOURS) / sleepSession.size(),
                (sum / SECOND_TO_MINUTES) / sleepSession.size());
    }
}