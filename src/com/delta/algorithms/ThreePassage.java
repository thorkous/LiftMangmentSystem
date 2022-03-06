package com.delta.algorithms;

import com.delta.models.Elevator;
import com.delta.models.Passenger;

public class ThreePassage {

    /**
     * Three Passage Group Elevator Scheduling.
     * <p>
     * Estimates the costs that would result from assigning the new call to the elevator.
     * <p>
     * Stop costs are static and include the period of time necessary for opening the door,
     * unloading and loading each one passenger and closing the door.
     * <p>
     * <p>
     * The call is assigned to the elevator with the lowest costs.
     */
    public int choseElevator(Elevator[] elevatorGroup, Passenger passenger) {

        int pick = 0;
        int cost = Integer.MAX_VALUE;


        // Find the elevator with lowest cost
        for (Elevator elevator : elevatorGroup) {

            int calls = elevator.getSequence().size(); // Current number of calls in sequence
            int elevatorCost = (calls + 1) * ((elevator.getVelocity() * elevator.getInterFloorHeight()) +
                    elevator.getPassengerLoadingTime() + elevator.getPassengerUnloadingTime()); // Total cost of all calls plus new call

            if (elevatorCost < cost) {
                cost = elevatorCost;
                pick = elevator.getID();
            }
        }

        return pick;
    }

}
