package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by annieaa on 10/03/15.
 */
public class Player {

    //private Vehicle vehicle;
    private Tank vehicle;
    private Inventory inventory;
    private Ammunition chosenAmmo;
    private Team team;
    private float timeLeft;

    public Player(Environment environment) {
        inventory = new Inventory();
        chosenAmmo = Store.getAmmunition("Bullet");
        vehicle = new Tank(environment, new Vector2(Gdx.graphics.getWidth()/3, environment.getGroundHeight(Gdx.graphics.getWidth()/3)));
        //vehicle = new Tank(environment, new Vector2(Gdx.graphics.getWidth()/3, environment.getGroundHeight(Gdx.graphics.getWidth()/3)));

    }

    public void fireShot() {
        vehicle.fire(chosenAmmo);
    }

    public void changeAmmo() {
        chosenAmmo = inventory.getNextAmmo(chosenAmmo);
    }

    public Tank getVehicle() {
        return vehicle;
    }

    /*public Vehicle getVehicle() {
        return vehicle;
    }*/

    /*public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }*/

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Ammunition getChosenAmmo() {
        return chosenAmmo;
    }

    public void setChosenAmmo(Ammunition chosenAmmo) {
        this.chosenAmmo = chosenAmmo;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public float getTimeLeft() {
        return timeLeft;
    }

    public void reduceTimeLeft(float reducedTime) {
        timeLeft -= reducedTime;
    }
}