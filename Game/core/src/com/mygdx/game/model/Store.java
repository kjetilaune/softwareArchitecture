package com.mygdx.game.model;

import java.util.HashMap;

/**
 * Created by annieaa on 10/03/15.
 */
public class Store {

    //need to add something to the hashmaps.

    // Maps name of the Ammunition to the actual object
    private static  HashMap<String, Ammunition> allAmmunition;

    // Maps name of the Ammunition to the store prices
    private static HashMap<String, Integer> ammunitionPrices;

    // Maps the name of the upgrade to the store prices
    private static HashMap<String, Integer> upgradePrices;


    public Store() {
        allAmmunition = new HashMap<String, Ammunition>();
        ammunitionPrices = new HashMap<String, Integer>();
        upgradePrices = new HashMap<String, Integer>();

        // instantiates and adds all existing ammunition types
        allAmmunition.put("Bullet", new Bullet(1, 1, 10, 1));
        allAmmunition.put("F-Bomb", new F_Bomb(10, 1, 25, 3));
        allAmmunition.put("Instakill", new Instakill(100, 1, 100, 10));

        // adds all ammunition available for purchase
        ammunitionPrices.put("F-Bomb", 1000);

        // adds all upgrades available for purchase
        upgradePrices.put("Armor", 750);
        upgradePrices.put("Health", 1500);
        upgradePrices.put("Fuel", 500);

    }

    public HashMap<String, Ammunition> getAllAmmunition() {
        return allAmmunition;
    }
    public HashMap<String, Integer> getAmmunitionPrices() {
        return ammunitionPrices;
    }
    public HashMap<String, Integer> getUpgradePrices() {
        return upgradePrices;
    }

    public static Ammunition getAmmunition(String name) {
        return allAmmunition.get(name);
    }

    public static Integer getAmmunitionPrice(String ammoName) {
        return ammunitionPrices.get(ammoName);
    }

    public static Integer getUpgradePrice(String upgrade) {
        return upgradePrices.get(upgrade);
    }


}
