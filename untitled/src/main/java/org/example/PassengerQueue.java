package org.example;

import java.util.*;
import java.util.logging.Logger;
public class PassengerQueue {

    private final static Logger logger = Logger.getLogger(PassengerQueue.class.getName());
    Map<Integer, Queue<String >> passengerQueues = new HashMap<>();

    public PassengerQueue() {
        for (int i = 0; i<6; i++){
            Queue<String> passengers = new LinkedList<>();
            passengerQueues.put(i,passengers);
        }
    }

    public void addPassengers(Integer stationCode, String passenger) {
        Queue<String> queue = passengerQueues.get(stationCode);
        if (queue != null) {
            queue.add(passenger);
        } else {
            logger.info("Station code not found: " + stationCode);
        }
    }

    public void displayMap(){
        for (Map.Entry<Integer, Queue<String >> entry : passengerQueues.entrySet()){
            System.out.println("Station: " + entry.getKey() + "->" + entry.getValue());
        }
    }

    public void displayPassengerStat(){
        for (Map.Entry<Integer, Queue<String >> entry : passengerQueues.entrySet()){
            System.out.println("Station: " + entry.getKey() + "->" + entry.getValue().size());
        }
    }
}
