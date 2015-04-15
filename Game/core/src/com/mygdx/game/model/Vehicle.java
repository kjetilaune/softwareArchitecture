package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by annieaa on 10/03/15.
 */
public abstract class Vehicle extends GameObject {

    private int health;
    private int fuel;
    private int power;
    private Location vehicleLocation;


    public Vehicle(Texture texture) {
        super(texture);
        health = 100;
        fuel = 100;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }


    public void setPower(int power) {
        // divided by 2 to match other calculations
        this.power = power/2;
    }

    public int getPower() {
        return power;
    }

}
