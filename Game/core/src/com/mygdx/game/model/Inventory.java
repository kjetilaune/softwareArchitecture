package com.mygdx.game.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by annieaa on 10/03/15.
 */
public class Inventory {

    // Two lists, one with available Ammunition and the other with the amount, with corresponding placement.
    private ArrayList<Ammunition> ammunitions;
    private ArrayList<Integer> ammoAmount;

    // Maps name of the upgrades to the inventory amount
    private HashMap<String, Integer> upgrades;

    // keeps track of the player's score
    private int score;

    public Inventory() {
        ammunitions = new ArrayList<Ammunition>();
        ammoAmount = new ArrayList<Integer>();
        upgrades = new HashMap<String, Integer>();

        score = 0;

        // set default ammunition
        ammunitions.add(Store.getAmmunition("YummyGrenade"));
        ammoAmount.add(1337);
        ammunitions.add(Store.getAmmunition("TastyMissile"));
        ammoAmount.add(5);
        ammunitions.add(Store.getAmmunition("Instastuffed"));
        ammoAmount.add(1);

        // set default upgrades
        upgrades.put("Health", 0);
        upgrades.put("Fuel", 0);



    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<Ammunition> getAmmunitions() {
        return ammunitions;
    }

    public ArrayList<Integer> getAmmoAmount() {
        return ammoAmount;
    }

    public HashMap<String, Integer> getUpgrades() {
        return upgrades;
    }

    public int getHealthUpgrade() {
        return upgrades.get("Health");
    }

    public int getFuelUpgrade() {
        return upgrades.get("Fuel");
    }

    public void increaseAmmo(String ammoName, int amount) {

        Ammunition ammo = Store.getAmmunition(ammoName);

        if (!ammunitions.contains(ammo)) {
            ammunitions.add(ammo);
            ammoAmount.add(amount);
        }
        else {
            int ammoIndex = ammunitions.indexOf(ammo);
            int currentAmount = ammoAmount.get(ammoIndex);
            ammoAmount.set(ammoIndex, currentAmount + amount);
        }
    }

    public boolean decreaseAmmo(String ammoName, int amount) {

        // enables infinite ammo of the YummyGrenade-type
        if (ammoName.equals("YummyGrenade")) {
            return true;
        }

        Ammunition ammo = Store.getAmmunition(ammoName);

        if (!ammunitions.contains(ammo)) {
            return false;
        }

        int ammoIndex = ammunitions.indexOf(ammo);
        int currentAmount = ammoAmount.get(ammoIndex);
        if (currentAmount - amount <= 0) {
            // if ammo is 0, remove from inventory
            ammunitions.remove(ammoIndex);
            ammoAmount.remove(ammoIndex);
            return false;
        }

        ammoAmount.set(ammoIndex, currentAmount - amount);
        return true;
    }

    public void increaseUpgrade(String upgrade, int amount) {
        int currentAmount = upgrades.get(upgrade);
        upgrades.put(upgrade, currentAmount + amount);
    }

    public boolean decreaseUpgrade(String upgrade, int amount) {
        int currentAmount = upgrades.get(upgrade);
        if (currentAmount - amount <= 0) {
            return false;
        }
        upgrades.put(upgrade, currentAmount - amount);
        return true;
    }

    public boolean buyAmmunition(String ammoName, Integer amount) {
        int cost = Store.getAmmunitionPrice(ammoName) * amount;

        if (cost > score) {
            System.out.println("Player does not have the score to purchase this item!");
            return false;
        }

        score -= cost;
        increaseAmmo(ammoName, amount);
        return true;
    }

    public boolean buyUpgrade(String upgrade, Integer amount) {
        int cost = Store.getUpgradePrice(upgrade) * amount;

        if (cost > score) {
            System.out.println("Player does not have the score to purchase this item!");
            return false;
        }

        score -= cost;
        increaseUpgrade(upgrade, amount);
        return true;
    }

    public Ammunition getNextAmmo(Ammunition currentAmmo) {
        // if we have reached the end of the list, get the first element
        if (ammunitions.indexOf(currentAmmo) + 1 >= ammunitions.size()) {
            return ammunitions.get(0);
        }
        // else, get next in list
        return ammunitions.get(ammunitions.indexOf(currentAmmo) + 1);
    }

    public int getAmmoLeft(String ammoName) {

        for (int i = 0 ; i < ammunitions.size(); i++) {
            if (ammoName.equals(ammunitions.get(i).getName())) {
                return ammoAmount.get(i);
            }
        }
        return 0;
    }

}
