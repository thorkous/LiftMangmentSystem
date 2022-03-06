package com.delta.models;

import com.delta.service.GroupElevatorService;

public class ElevatorControlSystem {

    private int totalFloors; // Number of floors
    private int totalElevators; // Number of elevators
    private int algorithm;

    private Elevator elevatorGroup[]; // An array of L elevators
    private Floor floors[]; // An array of N floors
    private GroupElevatorService controller; // reference to the controller used for controller setup

    public ElevatorControlSystem(int totalFloors, int totalElevators) {
        this.totalFloors = totalFloors;
        this.totalElevators = totalElevators;

        this.elevatorGroup = new Elevator[this.totalElevators];
        this.floors = new Floor[this.totalFloors];

        this.controller = new GroupElevatorService(this.elevatorGroup, this.floors);
        this.controller.setTotalElevators(this.totalElevators);
        this.controller.setTotalFloors(this.totalFloors);
    }

    public int getTotalFloors() {
        return totalFloors;
    }

    public int getTotalElevators() {
        return totalElevators;
    }

    public void createElevators() {
        for (int i = 0; i < this.totalElevators; ++i) {
            this.elevatorGroup[i] = new Elevator(i, this.algorithm, 1,
                    1, 1, 1);
            this.elevatorGroup[i].setCurrentFloor(0);
            this.elevatorGroup[i].setDirection(1);
            this.elevatorGroup[i].setIdle(true);
            this.elevatorGroup[i].setTotalFloors(totalFloors);
            this.elevatorGroup[i].setTotalElevators(totalElevators);
        }

        // Create elevator threads
        for (int i = 0; i < this.totalElevators; ++i) {
            this.elevatorGroup[i].elevatorControllerThread();
            this.elevatorGroup[i].performJobThread();

        }
    }
    public void createFloors() {
        for (int i = 0; i < this.totalFloors; ++i) {
            this.floors[i] = new Floor(i);
        }
    }
    public GroupElevatorService getController() {
        return controller;
    }
    public void sendRequest(int toFloor, int fromFloor) throws InterruptedException {
        floors[toFloor].setRequest(toFloor, fromFloor);
    }

    public void setAlgorithm(int algorithm) {
        this.algorithm = algorithm;
    }
}
