package org.example;

public class DLinkedList {
    private Node head;
    private Node tail;

    private class Node{
        Station station;
        Node next;
        Node prev;

        Node(Station station){
            this.station = station;
            this.next = null;
            this.prev = null;
        }
    }

    public void addToFront(Station station){
        Node newNode = new Node(station);

        if (head == null){
            head = newNode;
            tail = newNode;
        }
        else{
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    public void addToTail(Station station){
        Node newNode = new Node(station);

        if (tail == null){
            head = newNode;
            tail = newNode;
        }
        else{
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
    }

    public boolean contains(Station targetStation) {
        Node current = head;
        while (current != null) {
            if (current.station == targetStation) {
                return true;
            }
            current = current.next;
        }
        return false;
    }


    public void display(){
        Node current = head;
        while (current != null){
            System.out.println(current.station + " ");
            current = current.next;
        }
    }
}
