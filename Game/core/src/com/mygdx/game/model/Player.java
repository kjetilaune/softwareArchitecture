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
    private int roundsWon, turnsTaken;
    private int playerNumber;

    public Player(Team team, Environment environment, Vector2 vehiclePosition, int playerNumber) {
        roundsWon = 0;
        inventory = new Inventory();
        chosenAmmo = Store.getAmmunition("YummyGrenade");
        vehicle = new Tank(team, environment, vehiclePosition);
        //vehicle = new Tank(team, environment, vehiclePosition);
        this.team = team;
        this.playerNumber = playerNumber;
        turnsTaken = 0;
    }

    // set how many turns this player has taken
    public void setTurnsTaken(int turnsTaken) {
        this.turnsTaken = turnsTaken;
    }

    // return the number of turns taken by this player
    public int getTurnsTaken() {
        return turnsTaken;
    }

    public void changeAmmo() {
        chosenAmmo = inventory.getNextAmmo(chosenAmmo);
    }

    public boolean isAlive() { return vehicle.getHealth() > 0; }

    public void reset(Environment newEnvironment, Vector2 newStartPosition) {
        vehicle.reset(newEnvironment, newStartPosition);
        turnsTaken = 0;
    }

    public boolean buy(String ammoName, Integer amount) {
        return inventory.buyAmmunition(ammoName, amount);
    }

    public void setScore(int newScore) {
        inventory.setScore(newScore);
    }

    public int getScore() {
        return inventory.getScore();
    }

    public int getHealthUpgrade() {
        return inventory.getHealthUpgrade();
    }

    public int getFuelUpgrade() {
        return inventory.getFuelUpgrade();
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

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getRoundsWon() {
        return roundsWon;
    }

    public void setRoundsWon(int roundsWon) {
        this.roundsWon = roundsWon;
    }

}