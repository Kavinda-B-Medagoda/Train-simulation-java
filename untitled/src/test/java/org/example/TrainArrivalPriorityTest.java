package org.example;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class TrainArrivalPriorityTest {

    @Test
    void enqueueTrain() {
        TrainArrivalPriority trainArrivalPriority = new TrainArrivalPriority();

        // Enqueue trains with different priorities
        trainArrivalPriority.enqueueTrain("Train1", 3, new LinkedList<>());
        trainArrivalPriority.enqueueTrain("Train2", 1, new LinkedList<>());
        trainArrivalPriority.enqueueTrain("Train3", 5, new LinkedList<>());

        // Check if the trains were enqueued correctly
        assertEquals(3, trainArrivalPriority.getTrainArrivalQueue().size());
    }

}