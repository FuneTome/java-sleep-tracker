package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.sleepFunction.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class SleepTrackerApp {
    public static void main(String[] args) throws IOException {
        List<SleepingSession> sessions = new ArrayList<>();
        List<SleepAnalyzerFunction> functions = createSleepingSessionList();

        Reader fileReader = new FileReader(args[0]);
        BufferedReader br = new BufferedReader(fileReader);
        br.lines().forEach(line -> sessions.add(new SleepingSession(line)));
        br.close();

        functions.forEach(function -> function.function(sessions));
    }

    static List<SleepAnalyzerFunction> createSleepingSessionList(){
        List<SleepAnalyzerFunction> sessionList = new ArrayList<>();
        sessionList.add(new CalculateSession());
        sessionList.add(new FindMinSessionDuration());
        sessionList.add(new FindMaxSessionDuration());
        sessionList.add(new FindAverageSessionDuration());
        sessionList.add(new FindBadSession());
        sessionList.add(new FindSleeplessNights());
        sessionList.add(new DetermineUserClassification());
        return sessionList;
    }
}