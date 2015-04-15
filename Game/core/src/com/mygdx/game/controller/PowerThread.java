package com.mygdx.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.BulletPhysics;
import com.mygdx.game.model.Environment;
import com.mygdx.game.model.TextureManager;
import com.mygdx.game.model.Vehicle;

/**
 * Created by Mikal on 19.03.2015.
 */
public class PowerThread extends Thread {



    // THIS THREAD IS NOT FINISHED. IT SHOULD CALL A .setPower(power), this.getPower() should be removed and killing the thread should be done in FireController

    // use blinker to safely replace Thread.stop()
    private boolean blinker;

    // true if a move-button is held down, else false
    // used to decide whether to move or not
    private boolean heldDown;

    // holds the power of which the bullet is to be fired
    // ranges from 0 to 100
    private int power;

    // decides if power is to be increased or decreased
    private boolean countUp;

    // the vehicle which power is changed in this thread
    private Vehicle vehicle;

    public PowerThread() {
        blinker = true;
        this.start();
    }

    public void run() {
        System.out.println("PowerThread started.");

        power = 0;

        // should run until killThread() is called
        while (blinker) {

            try {

                // checks if the button is held down
                if (heldDown) {

                    // unsure about the necessity of synchronized
                    // enables only this thread to have access to the vehicle during fluctuation
                    synchronized (vehicle) {

                        // decides if fluctuation should change direction
                        if (power == 100) {
                            countUp = false;
                        }
                        else if (power == 0) {
                            countUp = true;
                        }


                        // fluctuates the power
                        if (countUp) {
                            vehicle.setPower(vehicle.getPower() + 1);
                        }
                        else {
                            vehicle.setPower(vehicle.getPower() - 1);
                        }

                        System.out.println("Power: " + Integer.toString(power));

                        // cause the thread to halt for 50 ms to prevent to rapid fluctuation
                        sleep(25);

                    }

                }

                else {
                    // cause the thread to halt for 100 ms before checking again for movement
                    // probably not very effective
                    sleep(100);
                }
            }
            catch (InterruptedException e) {
                System.err.println("Error in PowerThread: " + e.getMessage());
            }
        }

        System.out.println("PowerThread died.");
    }

    // enables the if-clause in run() and starts fluctuation
    public void initiateFluctuation(Vehicle vehicle) {
        this.vehicle = vehicle;
        heldDown = true;
    }

    // causes the the else-clause in run() to be triggered, effectively ending fluctuation
    public void endFluctuation() {
        heldDown = false;
    }

    // calling this ends the while-loop in run(), stopping the thread from doing anything
    public void killThread() {
        blinker = false;
    }

}
