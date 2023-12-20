package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Create a StationGraph with 5 stations
        StationGraph stationGraph = new StationGraph(5);

        // Add connections with distances
        stationGraph.addConnection(0, 1, 5);
        stationGraph.addConnection(0, 2, 10);
        stationGraph.addConnection(1, 2, 3);
        stationGraph.addConnection(1, 3, 9);
        stationGraph.addConnection(2, 3, 8);
        stationGraph.addConnection(2, 4, 7);
        stationGraph.addConnection(3, 4, 2);

        // Display the original graph
        stationGraph.displayGraph();

        // Run Dijkstra's algorithm from Station 0
        Map<Integer, DijkstraResult> results = dijkstraShortestPath(stationGraph, 0);

        // Display shortest distances and paths
        for (int stationCode : results.keySet()) {
            DijkstraResult result = results.get(stationCode);
            System.out.println("Shortest Distance from Station 0 to Station " + stationCode + ": " + result.getDistance());
            System.out.println("Shortest Path: " + result.getPath());
            System.out.println();
        }
    }

    public static Map<Integer, DijkstraResult> dijkstraShortestPath(StationGraph stationGraph, int sourceStation) {
        int numberOfStations = stationGraph.getNumberOfStations();
        int[][] graph = stationGraph.getStationGraph();

        int[] distance = new int[numberOfStations];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[sourceStation] = 0;

        PriorityQueue<StationDistance> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new StationDistance(sourceStation, 0, new ArrayList<>(Arrays.asList(sourceStation))));

        Map<Integer, DijkstraResult> results = new HashMap<>();

        while (!priorityQueue.isEmpty()) {
            StationDistance current = priorityQueue.poll();
            int currentStation = current.getStation();

            for (int neighbor = 0; neighbor < numberOfStations; neighbor++) {
                int edgeDistance = graph[currentStation][neighbor];

                if (edgeDistance > 0 && distance[currentStation] + edgeDistance < distance[neighbor]) {
                    List<Integer> path = new ArrayList<>(current.getPath());
                    path.add(neighbor);
                    distance[neighbor] = distance[currentStation] + edgeDistance;
                    priorityQueue.offer(new StationDistance(neighbor, distance[neighbor], path));

                    // Update results with the current path
                    results.put(neighbor, new DijkstraResult(distance[neighbor], path));
                }
            }
        }

        return results;
    }

    private static class StationDistance implements Comparable<StationDistance> {
        private int station;
        private int distance;
        private List<Integer> path;

        public StationDistance(int station, int distance, List<Integer> path) {
            this.station = station;
            this.distance = distance;
            this.path = path;
        }

        public int getStation() {
            return station;
        }

        public int getDistance() {
            return distance;
        }

        public List<Integer> getPath() {
            return path;
        }

        @Override
        public int compareTo(StationDistance other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    private static class DijkstraResult {
        private int distance;
        private List<Integer> path;

        public DijkstraResult(int distance, List<Integer> path) {
            this.distance = distance;
            this.path = path;
        }

        public int getDistance() {
            return distance;
        }

        public List<Integer> getPath() {
            return path;
        }
    }
}
