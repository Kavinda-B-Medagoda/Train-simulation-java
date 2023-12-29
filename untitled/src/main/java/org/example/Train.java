package org.example;

import java.util.LinkedList;

public class Train {
    String trainName;
    int priority;
    LinkedList<String> schedule;

    public Train(String trainName, int priority, LinkedList<String> schedule) {
        this.trainName = trainName;
        this.priority = priority;
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Train: " + trainName + " (Priority: " + priority + ")" + " schedule: " + schedule;
    }
}
