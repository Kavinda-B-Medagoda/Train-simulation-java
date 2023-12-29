package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class StationGraph {
    private int numberOfStations;
    private int[][] stationGraph;

    public int getNumberOfStations() {
        return numberOfStations;
    }

    public void setNumberOfStations(int numberOfStations) {
        this.numberOfStations = numberOfStations;
    }

    public int[][] getStationGraph() {
        return stationGraph;
    }

    public void setStationGraph(int[][] stationGraph) {
        this.stationGraph = stationGraph;
    }

    public StationGraph(int numberOfStations) {
        this.numberOfStations = numberOfStations;
        this.stationGraph = new int[numberOfStations][numberOfStations];
    }

    public void addConnection(int sourceStation, int destinationStation, int distance){
        stationGraph[sourceStation][destinationStation] = distance;
        stationGraph[destinationStation][sourceStation] = distance;
    }

    public int getDistance(int sourceStation, int destinationStation){
        return stationGraph[sourceStation][destinationStation];
    }

    public ArrayList getConnections(int src){
        ArrayList connections = new ArrayList();
        for (int i=0; i<numberOfStations; i++){
            if (stationGraph[src][i] != 0){
                connections.add(i);
            }
        }
        return connections;
    }

    public void displayGraph() {
        System.out.println("Train Station Graph (Adjacency Matrix):");
        for (int i = 0; i < numberOfStations; i++) {
            System.out.print("Station " + i + ": ");
            for (int j = 0; j < numberOfStations; j++) {
                System.out.print(stationGraph[i][j] + " ");
            }
            System.out.println();
        }
    }


    public void BFS(int start) {
        boolean[] visited = new boolean[numberOfStations];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            ArrayList<Integer> connections = getConnections(current);
            for (int neighbor : connections) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }


}


