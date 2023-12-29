package org.example;
import java.util.logging.Logger;


public class TrainSchedule {

    private static final Logger logger = Logger.getLogger(TrainSchedule.class.getName());
    Node head;
    Node tail;

    static class Node {
        Train train;
        Node next;

        public Node(Train train) {
            this.train = train;
            this.next = null;
        }
    }

    public void addTrain(Train train) {
        Node newNode = new Node(train);

        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        tail.next = head;
    }

    public void displaySchedule() {
        if (head == null){
            logger.info("Train schedule empty");
        }

        Node current = head;
        do {
            System.out.println("Train: " + current.train.trainName);
            System.out.println("with priority: " + current.train.priority);
            System.out.println("Schedule: "+ current.train.schedule);
            current = current.next;
        }while (current != head);
    }
}
