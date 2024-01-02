package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class DLinkedListTest {

    @Test
    public void testAddToFront() {
        DLinkedList dLinkedList = new DLinkedList();
        Station station1 = new Station(0,2,null);
        Station station2 = new Station(1,2,null);

        dLinkedList.addToFront(station1);
        dLinkedList.addToFront(station2);

        assertTrue(dLinkedList.contains(station2));
        assertTrue(dLinkedList.contains(station1));

    }

    @Test
    public void testAddToTail() {
        DLinkedList dLinkedList = new DLinkedList();
        Station station1 = new Station(1,2,null);
        Station station2 = new Station(2,2,null);


        dLinkedList.addToTail(station1);
        dLinkedList.addToTail(station2);

        assertTrue(dLinkedList.contains(station1));
        assertTrue(dLinkedList.contains(station2));
    }

    @Test
    public void testDisplay() {
        DLinkedList dLinkedList = new DLinkedList();
        Station station1 = new Station(1,2,null);
        Station station2 = new Station(2,2,null);
        Station station3 = new Station(3,2,null);



        dLinkedList.addToFront(station1);
        dLinkedList.addToFront(station2);
        dLinkedList.addToFront(station3);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        dLinkedList.display();

        assertEquals("stationCode=3, distance=2.0, stationList=null \nstationCode=2, distance=2.0, stationList=null \nstationCode=1, distance=2.0, stationList=null \n", outputStream.toString());
    }
}
