package com.mygdx.game.model;

import com.mygdx.game.model.AmmoTypes.Bullet;
import com.mygdx.game.model.AmmoTypes.F_Bomb;
import com.mygdx.game.model.AmmoTypes.Instakill;

import java.util.HashMap;

/**
 * Created by annieaa on 10/03/15.
 */
public class Store {

    //need to add something to the hashmaps.

    private static Store storeInstance;

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
        allAmmunition.put("Bullet", new Bullet());
        allAmmunition.put("F-Bomb", new F_Bomb());
        allAmmunition.put("Instakill", new Instakill());

        // adds all ammunition available for purchase
        ammunitionPrices.put("F-Bomb", 500);
        ammunitionPrices.put("Instakill", 1000);

        // adds all upgrades available for purchase
        upgradePrices.put("Health", 1500);
        upgradePrices.put("Fuel", 500);

    }

    public static Store getInstance() {
        if (storeInstance == null) {
            storeInstance = new Store();
        }
        return storeInstance;
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
        if (allAmmunition.containsKey(name)) {
            return allAmmunition.get(name);
        }
        return null;
    }

    public static Integer getAmmunitionPrice(String ammoName) {
        if (ammunitionPrices.containsKey(ammoName)) {
            return ammunitionPrices.get(ammoName);
        }
        return -1;
    }

    public static Integer getUpgradePrice(String upgrade) {
        if (upgradePrices.containsKey(upgrade)) {
            return upgradePrices.get(upgrade);
        }
        return -1;
    }


}
