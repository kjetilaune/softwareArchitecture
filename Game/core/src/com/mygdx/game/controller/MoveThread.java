package com.mygdx.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Environment;
import com.mygdx.game.model.TextureManager;
import com.mygdx.game.model.Vehicle;

/**
 * Created by Mikal on 17.03.2015.
 */
public class MoveThread extends Thread {

    private String direction;
    private float x, y;
    private Vehicle vehicle;
    private Environment environment;
    private boolean heldDown;

    public MoveThread() {

    }

    public void run() {
        System.out.println("MoveThread started.");
        while (heldDown) {

            vehicle.setRotation(environment.getAngle(vehicle.getPosition().x, vehicle.getPosition().x + TextureManager.tank.getWidth()));
            if (direction.equals("arrowLeft")){
                vehicle.setPosition(new Vector2(vehicle.getPosition().x - 1, environment.getGroundHeight(vehicle.getPosition().x - 1)));
            }
            else if (direction.equals("arrowRight")){
                vehicle.setPosition(new Vector2(vehicle.getPosition().x + 1, environment.getGroundHeight(vehicle.getPosition().x + 1)));
            }

            try { Thread.sleep(50); }
            catch (InterruptedException e) {
                System.err.println("Error in MoveThread: " + e.getMessage());
            }

        }
    }

    public static void main(String args[]) {

    }

    public void move(String direction, Vehicle vehicle, Environment environment) {
        this.heldDown = true;
        this.direction  = direction;
        this.vehicle = vehicle;
        this.environment = environment;
        this.start();
    }

    public void end() {
        heldDown = false;
    }


}
