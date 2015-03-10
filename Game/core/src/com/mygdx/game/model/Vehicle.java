package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by annieaa on 10/03/15.
 */
public abstract class Vehicle {

    private int health;
    private int fuel;
    private float angle;
    private float power;

    private Texture image;

    public Vehicle(Texture image) {
        health = 100;
        fuel = 100;
        this.image = image;
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

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
    }

}
