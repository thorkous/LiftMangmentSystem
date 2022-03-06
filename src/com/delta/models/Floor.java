package com.delta.models;

import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

public class Floor {

    LinkedBlockingQueue<Passenger> passengers; // Holds passengers at this floor
    private int ID;
    private boolean DEBUG = false;

    public Floor(int ID) {
        this.passengers = new LinkedBlockingQueue<>();
        this.ID = ID;
    }

    public LinkedBlockingQueue<Passenger> getPassengers() {
        return passengers;
    }

    public int getID() {
        return ID;
    }

    public void setRequest( int toFloor, int fromFloor) throws InterruptedException {

        String ID = UUID.randomUUID().toString();

        int direction = 0;
        if (toFloor < fromFloor) {
            direction = 1;
        }

        if (DEBUG) {
            System.out.println(direction);
            System.out.println("toFloor: " + toFloor);
        }

        Call floorCall = new Call(1, toFloor, direction, ID);

        if (DEBUG) {
            System.out.println("FromFloor: " + fromFloor);
        }

        Call carCall = new Call(0, fromFloor, direction, ID);

        this.passengers.put(new Passenger(floorCall, carCall, ID)); // Create a Passenger object and add it the to the passengers array
    }
}
