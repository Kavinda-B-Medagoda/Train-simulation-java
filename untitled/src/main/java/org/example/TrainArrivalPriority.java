package org.example;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.logging.Logger;

public class TrainArrivalPriority {

    private static final Logger logger = Logger.getLogger(TrainArrivalPriority.class.getName());
    private PriorityQueue<Train> trainArrivalQueue;

    public PriorityQueue<Train> getTrainArrivalQueue() {
        return trainArrivalQueue;
    }

    public TrainArrivalPriority() {
        trainArrivalQueue = new PriorityQueue<>(Comparator.comparingInt(train -> train.priority));
    }

    public void enqueueTrain(String trainName, int priority, LinkedList schedule) {
        Train train = new Train(trainName, priority, schedule);
        trainArrivalQueue.offer(train);
        logger.info("Train Enqueued: " + train);
    }

    public void dequeueTrains() {
        logger.info("Dequeueing and displaying trains in order of priority:");
        while (!trainArrivalQueue.isEmpty()) {
            Train nextTrain = trainArrivalQueue.poll();
            logger.info("Train Arrived: " + nextTrain);
        }
    }

    public void displayQueue() {
        logger.info("Current state of the priority queue:");
        for (Train train : trainArrivalQueue) {
            System.out.println(train);
        }
    }
}
