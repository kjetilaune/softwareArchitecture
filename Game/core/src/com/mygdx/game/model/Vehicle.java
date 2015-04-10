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
    private float power;
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

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        if (power > 100.0) {
            this.power = 100.0f;
        }
        else if (power < 0.0) {
            this.power = 0.0f;
        }
        else {
            this.power = power;
        }
    }

}
