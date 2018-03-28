package com.bus;

/**
 *    "RUID": 117016396,
 "CurrentBusStop": "Quads",
 "DestinationBusStop": "Werblin Back Entrance",
 "Route": "B",
 "Date": "03/21/2017",
 "Time": "15:49"
 */

public class Bus {
    private String ruid;
    private String currentBusStop;
    private String destinationBusStop;
    private String route;
    private String date;
    private String time;

    public Bus(String ruid, String currentBusStop, String destinationBusStop, String route, String date, String time) {
        this.ruid = ruid;
        this.currentBusStop = currentBusStop;
        this.destinationBusStop = destinationBusStop;
        this.route = route;
        this.date = date;
        this.time = time;
    }

    public String getRuid() {
        return ruid;
    }

    public void setRuid(String ruid) {
        this.ruid = ruid;
    }

    public String getCurrentBusStop() {
        return currentBusStop;
    }

    public void setCurrentBusStop(String currentBusStop) {
        this.currentBusStop = currentBusStop;
    }

    public String getDestinationBusStop() {
        return destinationBusStop;
    }

    public void setDestinationBusStop(String destinationBusStop) {
        this.destinationBusStop = destinationBusStop;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
