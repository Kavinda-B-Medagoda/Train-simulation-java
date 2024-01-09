package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class TrainArrivalPriority {

    private static final Logger logger = Logger.getLogger(TrainArrivalPriority.class.getName());
    private List<Train> trainArrivalQueue;

    public List<Train> getTrainArrivalQueue() {
        return trainArrivalQueue;
    }

    public TrainArrivalPriority() {
        trainArrivalQueue = new LinkedList<>();
    }

    public void enqueueTrain(String trainName, int priority, LinkedList schedule) {
        Train train = new Train(trainName, priority, schedule);
        int index = 0;
        while (index < trainArrivalQueue.size() && train.priority >= trainArrivalQueue.get(index).priority) {
            index++;
        }
        trainArrivalQueue.add(index, train);
        logger.info("Train Enqueued: " + train);
    }

    public void dequeueTrains() {
        logger.info("Dequeueing and displaying trains in order of priority:");
        while (!trainArrivalQueue.isEmpty()) {
            Train nextTrain = trainArrivalQueue.remove(0);
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
