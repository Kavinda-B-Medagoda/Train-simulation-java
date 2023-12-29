package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PassengerQueueTest {
    @Test
    void addPassengers() {
        PassengerQueue passengerQueue = new PassengerQueue();

        passengerQueue.addPassengers(0, "passenger01");
        passengerQueue.addPassengers(1, "passenger02");
        passengerQueue.addPassengers(1, "passenger03");

        assertEquals(2, passengerQueue.passengerQueues.get(1).size());
        assertTrue(passengerQueue.passengerQueues.get(0).contains("passenger01"));
    }

    @Test
    void invalidStationNumber(){
        PassengerQueue passengerQueue = new PassengerQueue();

        passengerQueue.addPassengers(10, "pass1");

        assertNull(passengerQueue.passengerQueues.get(10));
    }

    @Test
    void displayMap() {
        PassengerQueue passengerQueue = new PassengerQueue();

        // Add passengers to stations
        passengerQueue.addPassengers(0, "Passenger1");
        passengerQueue.addPassengers(1, "Passenger2");

        // Display the map and verify the output
        String expectedOutput = "Station: 0->[Passenger1]\nStation: 1->[Passenger2]\nStation: 2->[]\nStation: 3->[]\nStation: 4->[]\nStation: 5->[]\n";
        assertEquals(expectedOutput, getConsoleOutput(passengerQueue::displayMap));
    }

    @Test
    void displayPassengerStat() {
        PassengerQueue passengerQueue = new PassengerQueue();

        // Add passengers to stations
        passengerQueue.addPassengers(0, "Passenger1");
        passengerQueue.addPassengers(1, "Passenger2");
        passengerQueue.addPassengers(1, "Passenger3");

        // Display the passenger statistics and verify the output
        String expectedOutput = "Station: 0->1\nStation: 1->2\nStation: 2->0\nStation: 3->0\nStation: 4->0\nStation: 5->0\n";
        assertEquals(expectedOutput, getConsoleOutput(passengerQueue::displayPassengerStat));
    }

    private String getConsoleOutput(Runnable runnable) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        runnable.run();
        System.setOut(originalOut);
        return outputStream.toString();
    }

}