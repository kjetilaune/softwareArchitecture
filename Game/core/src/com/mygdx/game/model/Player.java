package com.mygdx.game.model;

/**
 * Created by annieaa on 10/03/15.
 */
public class Player {

    private int score;
    private int money;
    private Vehicle vehicle;
    private Inventory inventory;
    private Ammunition chosenAmmo;

    public Player() {
        score = 0;
        money = 0;
        vehicle = new Tank();
        inventory = new Inventory();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
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

    public void setChosenAmmo(Ammunition chosenAmmo) {
        this.chosenAmmo = chosenAmmo;
    }
}
