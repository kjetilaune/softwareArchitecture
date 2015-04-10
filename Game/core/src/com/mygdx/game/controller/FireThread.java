package com.mygdx.game.controller;

import com.mygdx.game.model.Ammunition;
import com.mygdx.game.model.BulletPhysics;
import com.mygdx.game.model.Environment;
import com.mygdx.game.model.Tank;
import com.mygdx.game.model.Vehicle;

/**
 * Created by annieaa on 07/04/15.
 */
public class FireThread extends Thread {

    // use blinker to safely replace Thread.stop()
    private boolean blinker;

    // holds the ammo to be moved
    private Ammunition ammo;

    // holds the environment to move according to
    private Environment environment;

    //
    private float step;

    //
    private BulletPhysics physics;


    public FireThread() {
        blinker = true;
        step = 0;
    }

    public void run() {
        System.out.println("FireThread started.");

        // should run until killThread() is called
        while (blinker) {

            try {

                // checks if the ammunition has collided with the ground
                if (!environment.isColliding(ammo.getPosition())) {

                    // unsure about the necessity of synchronized
                    // enables only this thread to have access to the ammo during fireThread
                    synchronized (ammo) {

                        ammo.setPosition(physics.getPosition(step));
                        System.out.println("Setting ammo to " + ammo.getPosition().x + ", " + ammo.getPosition().y + " at step " + step);
                        step += 1;

                    }

                    // cause the thread to halt for 50 ms to prevent the bullet from moving to fast
                    sleep(50);
                }

                else {

                    ammo.setPosition(null);
                    killThread();
                }
            }
            catch (InterruptedException e) {
                System.err.println("Error in FireThread: " + e.getMessage());
            }
        }

        System.out.println("FireThread died.");
    }


    // enables the if-clause in run() and updates information about how and what to move
    public void fire(Vehicle vehicle, Ammunition ammo, Environment environment) {
        physics = new BulletPhysics(((Tank)vehicle).getBarrel().getAngle());
        physics.startPosition = ammo.getPosition();
        this.ammo = ammo;
        this.environment = environment;

        start();

    }


    // calling this ends the while-loop in run(), stopping the thread from doing anything
    public void killThread() {
        blinker = false;
    }

}
