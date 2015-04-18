package com.mygdx.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Environment;
import com.mygdx.game.model.TextureManager;
import com.mygdx.game.model.Vehicle;

/**
 * Created by Mikal on 17.03.2015.
 */

public class MoveThread extends Thread {

    // use blinker to safely replace Thread.stop()
    private boolean blinker;

    // holds direction of movement (either "arrowLeft" or "arrowRight")
    private String direction;

    // holds the vehicle to be moved
    private Vehicle vehicle;

    // holds the environment to move according to
    private Environment environment;

    // true if a move-button is held down, else false
    // used to decide whether to move or not
    private boolean heldDown;

    public MoveThread() {
        blinker = true;
        start();
    }

    public void run() {
        //System.out.println("MoveThread started.");

        // should run until killThread() is called
        while (blinker) {

            try {

                // checks if the button is held down
                if (heldDown) {

                    // unsure about the necessity of synchronized
                    // enables only this thread to have access to the vehicle during movement
                    synchronized (vehicle) {

                        if (vehicle.getFuel() > 0) {

                            //float angleToTheLeft = environment.getAngle(vehicle.getPosition().x - 1, vehicle.getPosition().x + vehicle.getRelativeWidth() - 1, vehicle.getRelativeWidth());
                            //float angleToTheRight = environment.getAngle(vehicle.getPosition().x + 1, vehicle.getPosition().x + vehicle.getRelativeWidth() + 1, vehicle.getRelativeWidth());

                            // moves the tank by updating its position according to the direction-input and environment
                            // somehow, changing the position with less than 10 seems to cause the vehicle to bounce around
                            if (direction.equals("arrowLeft") /*&& canTraverse(angleToTheLeft)*/){
                                vehicle.setPosition(new Vector2(vehicle.getPosition().x - 10, environment.getGroundHeight(vehicle.getPosition().x - 10)));
                            }
                            else if (direction.equals("arrowRight") /*&& canTraverse(angleToTheRight)*/){
                                vehicle.setPosition(new Vector2(vehicle.getPosition().x + 10, environment.getGroundHeight(vehicle.getPosition().x + 10)));
                            }

                            // rotates the vehicle according to the environment
                            vehicle.setRotation(environment.getAngle(vehicle.getPosition().x, vehicle.getPosition().x + vehicle.getRelativeWidth(), vehicle.getRelativeWidth()));

                            // diminishes fuel
                            //vehicle.decreaseFuel();

                        }

                    }

                    // cause the thread to halt for 50 ms to prevent from moving to fast
                    sleep(50);
                }

                else {
                    // cause the thread to halt for 200 ms before checking again for movement
                    // probably not very effective
                    sleep(100);
                }
            }
            catch (InterruptedException e) {
                System.err.println("Error in MoveThread: " + e.getMessage());
            }
        }

        //System.out.println("MoveThread died.");
    }

    // enables the if-clause in run() and updates information about how and what to move
    public void initiateMovement(String direction, Vehicle vehicle, Environment environment) {
        this.heldDown = true;
        this.direction  = direction;
        this.vehicle = vehicle;
        this.environment = environment;
    }

    // causes the the else-clause in run() to be triggered, effectively ending movement
    public void endMovement() {
        heldDown = false;
    }

    // calling this ends the while-loop in run(), stopping the thread from doing anything
    public void killThread() {
        blinker = false;
    }

    public boolean canTraverse(float angle) {
        return Math.abs(angle) < 60.0f;
    }

}
