package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.sleepFunction.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SleepTrackerApp {
    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        if (file.length() == 0){
            System.out.println("Файл пустой!");
            return;
        }
        List<SleepingSession> sessions = new ArrayList<>();
        List<SleepAnalyzerFunction> functions = createSleepingSessionList();
        try(Reader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);) {
            br.lines().forEach(line -> sessions.add(new SleepingSession(line)));
        } catch (IOException exception){
            System.out.println("Ошибка считываемого файла: " + exception.getMessage());
        }

        functions.forEach(function -> function.function(sessions));
    }

    static List<SleepAnalyzerFunction> createSleepingSessionList() {
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