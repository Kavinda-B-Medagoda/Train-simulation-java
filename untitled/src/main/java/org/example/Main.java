package org.example;

import java.util.*;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        Scanner scanner = null;
        try {
            // Create a StationGraph with 5 stations
            StationGraph stationGraph = new StationGraph(7);

            // Add connections with distances
            stationGraph.addConnection(0, 1, 5);
            stationGraph.addConnection(0, 2, 1);
            stationGraph.addConnection(1, 2, 3);
            stationGraph.addConnection(1, 3, 9);
            stationGraph.addConnection(2, 3, 8);
            stationGraph.addConnection(2, 4, 7);
            stationGraph.addConnection(3, 4, 2);
            stationGraph.addConnection(0, 5, 4);
            stationGraph.addConnection(1, 4, 6);
            stationGraph.addConnection(3, 5, 5);
            stationGraph.addConnection(4, 5, 3);
            stationGraph.addConnection(0, 6, 2);
            stationGraph.addConnection(1, 5, 7);
            stationGraph.addConnection(2, 6, 3);
            stationGraph.addConnection(3, 6, 1);
            stationGraph.addConnection(4, 6, 4);
            stationGraph.addConnection(5, 6, 6);

            PassengerQueue passengerQueue = new PassengerQueue();
            passengerQueue.addPassengers(0, "kavinda");
            passengerQueue.addPassengers(0, "ashan");
            passengerQueue.addPassengers(3, "chamidu");
            passengerQueue.addPassengers(3, "naveen");
            passengerQueue.addPassengers(0, "bathiya");
            passengerQueue.addPassengers(3, "ashan");
            passengerQueue.addPassengers(1, "chamidu");
            passengerQueue.addPassengers(1, "naveen");
            passengerQueue.addPassengers(1, "bathiya");

            LinkedList<String> schedule1 = new LinkedList<>(Arrays.asList("0", "6", "3"));
            LinkedList<String> schedule2 = new LinkedList<>(Arrays.asList("1", "2", "6", "3"));

            int userInput;
            scanner = new Scanner(System.in);

            do {
                System.out.println("==============Train Simulation================");
                System.out.println("1. view train graph");
                System.out.println("2. view all stations");
                System.out.println("3. view shortest path from given station");
                System.out.println("4. view shortest path among two stations");
                System.out.println("5. view train schedule");
                System.out.println("6. view train priority");
                System.out.println("7. search a station in a path");
                System.out.println("8. view passenger boarding");
                System.out.println("9. number of passengers at each station");
                System.out.println("10. EXIT");
                System.out.printf("Enter your choice: ");
                userInput = scanner.nextInt();
                scanner.nextLine();

                switch (userInput) {
                    case 1:
                        stationGraph.displayGraph();
                        break;

                    case 2:
                        for (int i = 0; i < stationGraph.getNumberOfStations(); i++) {
                            System.out.println(i);
                        }
                        break;

                    case 3:
                        System.out.printf("Enter a station: ");
                        int station = scanner.nextInt();
                        Map<Integer, DijkstraResult> results = dijkstraShortestPath(stationGraph, station);

                        // Display the shortest distances and paths
                        for (int stationCode : results.keySet()) {
                            DijkstraResult result = results.get(stationCode);
                            System.out.println("Shortest Distance from Station 0 to Station " + stationCode + ": " + result.getDistance());
                            System.out.println("Shortest Path: " + result.getPath());
                            System.out.println();
                        }
                        break;

                    case 4:
                        logger.info("Starting station: ");
                        int start = scanner.nextInt();
                        logger.info("Destination station: ");
                        int end = scanner.nextInt();
                        Map<Integer, DijkstraResult> results1 = dijkstraShortestPath(stationGraph, start);
                        List<Integer> pathList = results1.get(end).getPath();
                        DLinkedList dLinkedList = new DLinkedList();
                        for (Integer path : pathList) {
                            Station station1 = new Station(path, stationGraph.getDistance(path, start), stationGraph.getConnections(path));
                            dLinkedList.addToTail(station1);
                        }
                        dLinkedList.display();
                        break;

                    case 5:
                        TrainSchedule trainSchedule = new TrainSchedule();

                        trainSchedule.addTrain(new Train("ExpressTrain", 3, schedule1));
                        trainSchedule.addTrain(new Train("LocalTrain", 1, schedule2));

                        trainSchedule.displaySchedule();
                        break;

                    case 6:

                        TrainArrivalPriority trainQueue = new TrainArrivalPriority();

                        trainQueue.enqueueTrain("ExpressTrain1", 2, schedule1);
                        trainQueue.enqueueTrain("LocalTrain2", 1, schedule2);
                        trainQueue.enqueueTrain("ExpressTrain3", 3, schedule1);

                        trainQueue.displayQueue();

                        break;

                    case 7:
                        System.out.printf("Enter the starting station: ");
                        int searchStart = scanner.nextInt();
                        stationGraph.BFS(searchStart);
                        break;

                    case 8:
                        passengerQueue.displayMap();
                        break;

                    case 9:
                        passengerQueue.displayPassengerStat();

                    default:
                        System.out.println("Enter valid Number");

                }
            } while (userInput != 10);
        } catch (Exception e) {
            logger.info("Exception " + e);
        } finally {
            scanner.close();
        }
    }

    public static Map<Integer, DijkstraResult> dijkstraShortestPath(StationGraph stationGraph, int sourceStation) {
        int numberOfStations = stationGraph.getNumberOfStations();
        int[][] graph = stationGraph.getStationGraph();

        int[] distance = new int[numberOfStations]; //array that contains distances to each station
        Arrays.fill(distance, Integer.MAX_VALUE); //fill all distances to max value
        distance[sourceStation] = 0; //starting distance 0 for starting station

        PriorityQueue<StationDistance> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new StationDistance(sourceStation, 0, new ArrayList<>(Arrays.asList(sourceStation))));

        Map<Integer, DijkstraResult> results = new HashMap<>(); //final map that contains station code and shortest distance and shortest path

        while (!priorityQueue.isEmpty()) {
            StationDistance current = priorityQueue.poll();
            int currentStation = current.getStation();

            for (int neighbor = 0; neighbor < numberOfStations; neighbor++) {
                int edgeDistance = graph[currentStation][neighbor];

                if (edgeDistance > 0 && distance[currentStation] + edgeDistance < distance[neighbor]) { //check whether the current distance to neighbor is less or greater than new distance
                    List<Integer> path = new ArrayList<>(current.getPath());
                    path.add(neighbor);
                    distance[neighbor] = distance[currentStation] + edgeDistance;
                    priorityQueue.offer(new StationDistance(neighbor, distance[neighbor], path));

                    // Update results map, put the station code and shortest path and the destination
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

    private static class DijkstraResult { //this class creates object that have the shortest distance from the src station to destination station and path
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
