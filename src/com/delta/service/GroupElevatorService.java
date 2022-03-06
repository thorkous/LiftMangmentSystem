package com.delta.service;

import com.delta.algorithms.LookAlgorithm;
import com.delta.algorithms.ThreePassage;
import com.delta.models.Elevator;
import com.delta.models.Floor;
import com.delta.models.Passenger;

public class GroupElevatorService implements Runnable {
    private Elevator elevatorGroup[];
    private Floor floors[];

    private int algorithm;
    private int start;

    private int totalFloors; // Number of floors
    private int totalElevators; // Number of elevators
    private ThreePassage threePassage;
    private LookAlgorithm lookAlgorithm;

    public GroupElevatorService(Elevator[] elevatorGroup, Floor[] floors) {

        super();
        this.elevatorGroup = elevatorGroup;
        this.floors = floors;
        this.start = 0;
        this.threePassage = new ThreePassage();
        this.lookAlgorithm = new LookAlgorithm();
    }

    public void setAlgorithm(int algorithm) {
        this.algorithm = algorithm;
    }

    public int getTotalFloors() {
        return totalFloors;
    }

    public void setTotalFloors(int totalFloors) {
        this.totalFloors = totalFloors;
    }

    public int getTotalElevators() {
        return totalElevators;
    }

    public void setTotalElevators(int totalElevators) {
        this.totalElevators = totalElevators;
    }


    @Override
    public void run() {
        try {
            while (true) {
                scheduler();
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            System.out.println("GroupElevatorController thread failed!");
        }
    }

    private void scheduler() throws InterruptedException {

        int chosenElevator = 0;
        boolean foundPassenger = false;

        Passenger tempPassenger = null; // Create a dummy Passenger object

        // Look for a floor with at least one passenger
        for (int i = this.start; i < floors.length; ++i) {

            Floor floor = floors[i];

            if (floor.getPassengers().size() > 0) {

                tempPassenger = floor.getPassengers().take(); // Remove the passenger from queue

                // Remembers from which index to start scanning next time
                if (i == (floors.length - 1)) {
                    this.start = 0;
                } else {
                    this.start = i + 1;
                }

                foundPassenger = true;
                break;
            }

            // Remembers from which index to start scanning next time
            // even though no passenger was found
            if (i == (floors.length - 1)) {
                this.start = 0;
            } else {
                this.start = i + 1;
            }
        }

        if (foundPassenger) {

            // Each algorithm returns the index of the chosen elevator
            // The chosen elevator will be given a task (receive job)
            if (this.algorithm == 1) {
                chosenElevator = threePassage.choseElevator(elevatorGroup, tempPassenger);
            } else {
                chosenElevator = lookAlgorithm.choseElevator(elevatorGroup, tempPassenger);
            }
            this.elevatorGroup[chosenElevator].receiveJob(tempPassenger); // Assign a passenger to an elevator
        }

    }
}
