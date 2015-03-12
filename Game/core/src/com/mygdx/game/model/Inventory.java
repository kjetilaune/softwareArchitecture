package com.mygdx.game.model;

import java.util.HashMap;

/**
 * Created by annieaa on 10/03/15.
 */
public class Inventory {

    // Maps name of the Ammunition to the inventory amount
    private HashMap<String, Integer> ammunitions;

    // Maps name of the Ammunition to the inventory amount
    private HashMap<String, Integer> upgrades;
    private int score;

    public Inventory() {
        score = 0;

        // set default upgrades
        upgrades.put("Armor", 0);
        upgrades.put("Health", 0);
        upgrades.put("Fuel", 0);

        // set default ammunition
        ammunitions.put("Bullet", 50);
        ammunitions.put("F-Bomb", 5);
        ammunitions.put("Instakill", 1);

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public HashMap<String, Integer> getAmmunitions() {
        return ammunitions;
    }

    public HashMap<String, Integer> getUpgrades() {
        return upgrades;
    }

    public void increaseAmmo(String ammo, int amount) {
        if (!ammunitions.containsKey(ammo)) {
            ammunitions.put(ammo, amount);
        }
        else {
            int currentAmount = ammunitions.get(ammo);
            ammunitions.put(ammo, currentAmount + amount);
        }
    }

    public boolean decreaseAmmo(String ammo, int amount) {
        if (!ammunitions.containsKey(ammo)) {
            return false;
        }

        int currentAmount = ammunitions.get(ammo);
        if (currentAmount - amount <= 0) {
            return false;
        }
        ammunitions.put(ammo, currentAmount - amount);
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

    public boolean buyAmmunition(String ammo, Integer amount) {
        int cost = Store.getAmmunitionPrice(ammo) * amount;

        if (cost > score) {
            System.out.println("Player does not have the score to purchase this item!");
            return false;
        }

        score -= cost;
        increaseAmmo(ammo, amount);
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

}
