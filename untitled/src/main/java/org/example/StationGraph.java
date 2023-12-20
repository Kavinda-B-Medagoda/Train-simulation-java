package org.example;

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

}


