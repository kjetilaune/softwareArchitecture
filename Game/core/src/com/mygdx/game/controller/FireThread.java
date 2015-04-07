package com.mygdx.game.controller;

import com.badlogic.gdx.math.Vector2;
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

    // holds direction of movement (either "arrowLeft" or "arrowRight")
    private Vector2 direction;

    private Vehicle vehicle;

    // holds the ammo to be moved
    private Ammunition ammo;

    // holds the environment to move according to
    private Environment environment;


    private float t;
    private BulletPhysics physics;


    public FireThread() {
        blinker = true;
        t = 0;
    }

    public void run() {
        System.out.println("FireThread started.");

        // should run until killThread() is called
        while (blinker) {

            try {

                // checks if the ammunition has collided with the ground
                if (!environment.isColliding(ammo.getPosition())) {

                    // unsure about the necessity of synchronized
                    // enables only this thread to have access to the vehicle during movement
                    synchronized (ammo) {

                        ammo.setPosition(physics.getPosition(t));
                        t += 1;

                    }

                    // cause the thread to halt for 50 ms to prevent from moving to fast
                    sleep(50);
                }

                else {
                    // cause the thread to halt for 200 ms before checking again for movement
                    // probably not very effective
                    //sleep(200);
                    ammo.setPosition(null);
                    killThread();
                }
            }
            catch (InterruptedException e) {
                System.err.println("Error in MoveThread: " + e.getMessage());
            }
        }

        System.out.println("Thread died.");
    }


    // enables the if-clause in run() and updates information about how and what to move
    public void move(Vehicle vehicle, Ammunition ammo, Environment environment) {
        physics = new BulletPhysics(((Tank)vehicle).getBarrel().getAngle());
        physics.startPosition = vehicle.getPosition();
        this.vehicle = vehicle;
        this.ammo = ammo;
        this.environment = environment;

        this.start();
    }


    // calling this ends the while-loop in run(), stopping the thread from doing anything
    public void killThread() {
        blinker = false;
    }

}
