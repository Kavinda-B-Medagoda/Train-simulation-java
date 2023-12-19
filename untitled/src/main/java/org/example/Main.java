package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Station> stations = new ArrayList<>();
        Map<String , Station> stationMap = new HashMap<>();

        Station A = new Station(103,"Station A", 20.5f, new ArrayList<>(Arrays.asList("B", "C", "E")));
        Station B = new Station(103, "Station B", 20.5f, new ArrayList<>(Arrays.asList("A", "D")));
        Station C = new Station(103, "Station C", 20.5f, new ArrayList<>(Arrays.asList("A", "D", "E")));
        Station D = new Station(103, "Station D", 20.5f, new ArrayList<>(Arrays.asList("B", "C")));
        Station E = new Station(103, "Station E", 20.5f, new ArrayList<>(Arrays.asList("A", "C")));

        stationMap.put("A", A);
        stationMap.put("B", B);
        stationMap.put("C", C);
        stationMap.put("D", D);
        stationMap.put("E", E);

        stations.add(A);
        stations.add(B);
        stations.add(C);
        stations.add(D);
        stations.add(E);


        System.out.printf("Enter starting station: ");
        String  start = scanner.nextLine();

        System.out.printf("Enter destination station: ");
        String  end = scanner.nextLine();

        int startIndex = stations.indexOf(stationMap.get(start));
        int endIndex = stations.indexOf(stationMap.get(end));

        DLinkedList track = new DLinkedList();

        for (int i = startIndex; i<= endIndex; i++){
            track.addToFront(stations.get(i));
        }
        track.display();

    }


}