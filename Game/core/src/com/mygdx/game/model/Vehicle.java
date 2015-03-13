package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by annieaa on 10/03/15.
 */
public abstract class Vehicle extends GameObject {

    private int health;
    private int fuel;
    private float angle;
    private float power;
    private Location vehicleLocation;

    private Texture texture;


    public Vehicle(Texture texture) {
        health = 100;
        fuel = 100;
        this.texture = texture;
    }

    public void fire(Ammunition ammo) {

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

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

}
