package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Enums.Team;

/**
 * Created by annieaa on 10/03/15.
 */
public class Player {

    private Vehicle vehicle;
    private Inventory inventory;
    private Ammunition chosenAmmo;
    private Team team;
    private float timeLeft;
    private int playerNumber;
    private int money;

    public Player(Team team, Environment environment, Vector2 vehiclePosition, int playerNumber) {
        this.team = team;
        inventory = new Inventory();
        chosenAmmo = Store.getAmmunition("Bullet");
        vehicle = new Tank(team, environment, vehiclePosition);
        this.money = 100000;
        this.playerNumber = playerNumber;

    }

    /*public void fireShot() {
        vehicle.fire(chosenAmmo);
    }*/

    public void changeAmmo() {
        chosenAmmo = inventory.getNextAmmo(chosenAmmo);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Ammunition getChosenAmmo() {
        return chosenAmmo;
    }

    public int getChosenAmmoAmount() {
        return inventory.getAmmoLeft(chosenAmmo.getName());
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

    public void setMoney(int money){ this.money = money; }

    public int getMoney(){ return this.money; }

    public int getPlayerNumber() {
        return playerNumber;
    }

}