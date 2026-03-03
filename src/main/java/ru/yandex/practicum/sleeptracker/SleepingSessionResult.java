package ru.yandex.practicum.sleeptracker;

import java.util.Objects;

public class SleepingSessionResult {
    private String description;
    private Object[] result;

    public SleepingSessionResult(String description, Object... result) {
        this.description = description;
        this.result = result;

        System.out.printf(description + "\n", result);
    }

    public Object getResult(int index) {
        return result[index];
    }
}
