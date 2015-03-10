package com.mygdx.game.model;

import java.util.HashMap;

/**
 * Created by annieaa on 10/03/15.
 */
public class Inventory {

    private HashMap<Ammunition, Integer> ammunitions;
    private HashMap<String, Integer> upgrades;

    public HashMap<Ammunition, Integer> getAmmunitions() {
        return ammunitions;
    }

    public void setAmmunitions(HashMap<Ammunition, Integer> ammunitions) {
        this.ammunitions = ammunitions;
    }

    public HashMap<String, Integer> getUpgrades() {
        return upgrades;
    }

    public void setUpgrades(HashMap<String, Integer> upgrades) {
        this.upgrades = upgrades;
    }

    public boolean increaseAmmo(Ammunition ammo, int amount) {
        int n = ammunitions.get(ammo);
        if (n <= 0) {
            return false;
        }
        ammunitions.put(ammo, n + amount);
        return true;
    }

    public boolean decreaseAmmo(Ammunition ammo, int amount) {
        int n = ammunitions.get(ammo);
        if (n - amount <= 0) {
            return false;
        }
        ammunitions.put(ammo, n - amount);
        return true;
    }

    public boolean increaseUpgrade(String upgrade, int amount) {
        int n = upgrades.get(upgrade);
        if (n <= 0) {
            return false;
        }
        upgrades.put(upgrade, n + amount);
        return true;
    }

    public boolean decreaseUpgrade(String upgrade, int amount) {
        int n = upgrades.get(upgrade);
        if (n - amount <= 0) {
            return false;
        }
        upgrades.put(upgrade, n - amount);
        return true;
    }


}
