package com.delta.algorithms;

import com.delta.models.Elevator;
import com.delta.models.Passenger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LookAlgorithm {
    public int choseElevator(Elevator[] elevatorGroup, Passenger passenger) {
        List<Elevator> elevatorList = new ArrayList<>(Arrays.asList(elevatorGroup));
        elevatorList.sort((a, b) -> a.getCurrentFloor() - b.getCurrentFloor());
        int sourceFloor = passenger.getFloorCall().getFloor();
        int targetFloor = passenger.getCarCall().getFloor();
        int direction = targetFloor - sourceFloor > 0 ? 1 : 0;
        Elevator minDownLift = null;
        Elevator minUpLeft = null;
        if (direction == 1) {
            for (Elevator e : elevatorList) {
                if ((e.getDirection() == 1 || e.isIdle()) && e.getCurrentFloor() <= sourceFloor) {
                    minUpLeft = e;
                } else {
                    if (minUpLeft == null) {
                        minUpLeft = e;
                    }
                }
                if (e.getDirection() == 0) {
                    if (minDownLift == null) {
                        minDownLift = e;
                    }
                }
            }
        } else {
            for (Elevator e : elevatorList) {
                if ((e.getDirection() == 0 || e.isIdle()) && e.getCurrentFloor() >= sourceFloor) {
                    minDownLift = e;
                    break;
                } else {
                    if (minDownLift == null) {
                        minDownLift = e;
                    }
                }
                if (e.getDirection() == 1) {
                    if (minUpLeft == null) {
                        minUpLeft = e;
                    }
                }
            }
        }

        if (direction == 1) {
            if (minUpLeft != null)
                return minUpLeft.getID();
            else
                return minDownLift.getID();
        } else {
            if (minDownLift != null)
                return minDownLift.getID();
            else
                return minUpLeft.getID();
        }

    }
}
