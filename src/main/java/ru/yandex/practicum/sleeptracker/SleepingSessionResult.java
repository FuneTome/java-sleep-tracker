package ru.yandex.practicum.sleeptracker;

public class SleepingSessionResult {
    private String description;
    private String[] result;

    public SleepingSessionResult(String description, String... result) {
        this.description = description;
        this.result = result;

        System.out.printf(description + "\n", result);
    }

    public String[] getResult() {
        return result;
    }
}
