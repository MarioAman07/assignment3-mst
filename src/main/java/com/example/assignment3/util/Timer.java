package com.example.assignment3.util;

public class Timer {
    private long startTime;
    private long endTime;

    public void start() {
        this.startTime = System.nanoTime();
    }

    public void stop() {
        this.endTime = System.nanoTime();
    }

    public double getElapsedTimeMs() {
        if (startTime == 0 || endTime == 0) {
            return 0.0;
        }
        return (double) (endTime - startTime) / 1_000_000.0;
    }

    public void reset() {
        this.startTime = 0;
        this.endTime = 0;
    }
}