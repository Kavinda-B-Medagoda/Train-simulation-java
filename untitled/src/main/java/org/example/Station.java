package org.example;

import java.util.ArrayList;

public class Station {
    int stationCode;
    float distance;
    ArrayList<String> stationList = new ArrayList<>();

    public Station(int stationCode, float distance, ArrayList<String> stationList) {

        this.stationCode = stationCode;
        this.distance = distance;
        this.stationList = stationList;
    }



    public int getStationCode() {
        return stationCode;
    }


    public void setStationCode(int stationCode) {
        this.stationCode = stationCode;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public ArrayList<String> getStationList() {
        return stationList;
    }

    public void setStationList(ArrayList<String> stationList) {
        this.stationList = stationList;
    }

    @Override
    public String toString() {
        return
                "stationCode=" + stationCode +
                ", distance=" + distance +
                ", stationList=" + stationList
                ;
    }
}