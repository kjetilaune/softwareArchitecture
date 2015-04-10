package com.mygdx.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Environment;
import com.mygdx.game.model.TextureManager;
import com.mygdx.game.model.Vehicle;

/**
 * Created by Mikal on 19.03.2015.
 */
public class PowerThread extends Thread {

    // use blinker to safely replace Thread.stop()
    private boolean blinker;

    // holds the vehicle to be moved
    private Vehicle vehicle;

    // true if a move-button is held down, else false
    // used to decide whether to move or not
    private boolean heldDown;

    private float fluctuation = 1.5f;

    public PowerThread() {
        blinker = true;
        this.start();
    }

    public void run() {
        System.out.println("PowerThread started.");

        // should run until killThread() is called
        while (blinker) {

            try {

                // checks if the button is held down
                if (heldDown) {

                    // unsure about the necessity of synchronized
                    // enables only this thread to have access to the vehicle during movement
                    synchronized (vehicle) {

                        if ((vehicle.getPower() == 0.0) || (vehicle.getPower() == 100.0)) {
                            fluctuation = -fluctuation;
                        }

                        vehicle.setPower(vehicle.getPower() + fluctuation);

                        

                    }

                    // cause the thread to halt for 50 ms to prevent from moving to fast
                    sleep(50);
                }

                else {
                    // cause the thread to halt for 200 ms before checking again for movement
                    // probably not very effective
                    sleep(200);
                }
            }
            catch (InterruptedException e) {
                System.err.println("Error in MoveThread: " + e.getMessage());
            }
        }

        System.out.println("Thread died.");
    }

    // enables the if-clause in run() and updates information about how and what to move
    public void move(String direction, Vehicle vehicle, Environment environment) {
        this.heldDown = true;
        this.vehicle = vehicle;
    }

    // causes the the else-clause in run() to be triggered, effectively ending movement
    public void endFluctuation() {
        heldDown = false;
    }

    // calling this ends the while-loop in run(), stopping the thread from doing anything
    public void killThread() {
        blinker = false;
    }

}
