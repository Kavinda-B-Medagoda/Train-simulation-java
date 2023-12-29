package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainScheduleTest {
    @Test
    void addTrain() {
        TrainSchedule trainSchedule = new TrainSchedule();

        // Add trains to the schedule
        Train train1 = new Train("ExpressTrain", 3, null);
        Train train2 = new Train("LocalTrain", 1, null);

        trainSchedule.addTrain(train1);
        trainSchedule.addTrain(train2);

        // Verify that trains were added to the schedule
        assertEquals(train1, trainSchedule.head.train);
        assertEquals(train2, trainSchedule.tail.train);
    }
}