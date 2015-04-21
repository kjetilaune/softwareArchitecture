package com.mygdx.game.controller.threads;

import com.mygdx.game.model.Vehicle;

/**
 * Created by Mikal on 19.03.2015.
 */
public class PowerThread extends Thread {

    // use blinker to safely replace Thread.stop()
    private boolean blinker;

    // true if a move-button is held down, else false
    // used to decide whether to move or not
    private boolean heldDown;

    // decides if power is to be increased or decreased
    private boolean countUp;

    // the vehicle which power is changed in this thread
    private Vehicle vehicleModel;

    public PowerThread() {
        blinker = true;
        this.start();
    }

    public void run() {
        //System.out.println("PowerThread started.");

        // should run until killThread() is called
        while (blinker) {

            try {

                // checks if the button is held down
                if (heldDown) {

                    // unsure about the necessity of synchronized
                    // enables only this thread to have access to the vehicle during fluctuation
                    synchronized (vehicleModel) {

                        // decides if fluctuation should change direction
                        if (vehicleModel.getPower() >= 100.0f) {
                            countUp = false;
                        }
                        else if (vehicleModel.getPower() <= 0.0f) {
                            countUp = true;
                        }


                        // fluctuates the power
                        if (countUp) {
                            vehicleModel.setPower(vehicleModel.getPower() + 1.0f);
                        }
                        else {
                            vehicleModel.setPower(vehicleModel.getPower() - 1.0f);
                        }

                        // cause the thread to halt for 10 ms to prevent to rapid fluctuation
                        sleep(10);

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



        //System.out.println("PowerThread died.");
    }

    // enables the if-clause in run() and starts fluctuation
    public void initiateFluctuation(Vehicle vehicleModel) {
        this.vehicleModel = vehicleModel;
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
