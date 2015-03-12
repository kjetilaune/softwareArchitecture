package com.mygdx.game.model;

import java.util.HashMap;

/**
 * Created by annieaa on 10/03/15.
 */
public class Store {

    //need to add something to the hashmaps.

    // Maps name of the Ammunition to the store prices
    private static HashMap<String, Integer> ammunitionPrices;

    // Maps the name of the upgrade to the store prices
    private static HashMap<String, Integer> upgradePrices;


    public Store() {
        ammunitionPrices = new HashMap<>();
        upgradePrices = new HashMap<>();

        // adds all ammunition available for purchase
        ammunitionPrices.put("F-Bomb", 1000);

        // adds all upgrades available for purchase
        upgradePrices.put("Armor", 750);
        upgradePrices.put("Health", 1500);
        upgradePrices.put("Fuel", 500);

    }

    public HashMap<String, Integer> getAmmunitionPrices() {
        return ammunitionPrices;
    }
    public HashMap<String, Integer> getUpgradePrices() {
        return upgradePrices;
    }

    public static Integer getAmmunitionPrice(String ammoName) {
        return ammunitionPrices.get(ammoName);
    }

    public static void setAmmunitionPrice(String ammoName, Integer price) {
        ammunitionPrices.put(ammoName, price);
    }

    public static Integer getUpgradePrice(String upgrade) {
        return upgradePrices.get(upgrade);
    }

    public static void setUpgradePrice(String upgrade, Integer price) {
        upgradePrices.put(upgrade, price);
    }

}
