package com.delta;

import com.delta.models.ElevatorControlSystem;

import java.util.Scanner;

public class Main {


    //Run this function for the simulation.
    public static void main(String[] args) throws InterruptedException {
        // write your code here\
        Scanner reader = new Scanner(System.in);
        System.out.println("Please enter total Number of floors: ");
        int totalFloors = reader.nextInt();
        System.out.println("Please enter total number of elevators: ");
        int totalElevators = reader.nextInt();
        ElevatorControlSystem ecs = new ElevatorControlSystem(totalFloors, totalElevators);
        int tempAlgo = 2;
        ecs.setAlgorithm(tempAlgo);
        ecs.getController().setAlgorithm(tempAlgo);
        System.out.println("\nAll elevators start on the central floor of the building with direction up.\n");
        ecs.createFloors();
        ecs.createElevators();
        new Thread(ecs.getController()).start(); // Activates the GroupElevatorController to scan the floors array


        // Generate a passenger on one of the floors
        ecs.sendRequest(0, 7);
        ecs.sendRequest(3, 0);
        Thread.sleep(1000);
        ecs.sendRequest(4, 6);
        /*while(true){
            System.out.println("Please enter source floor: ");
            int source = reader.nextInt();
            System.out.println("Please enter dest floor: ");
            int dest = reader.nextInt();
            ecs.sendRequest(source, dest);
            Thread.sleep(1000);
        }*/


    }
}
