package com.mygdx.game.model;

import com.mygdx.game.model.ammoTypes.DeliBomb;
import com.mygdx.game.model.ammoTypes.FlavourRocket;
import com.mygdx.game.model.ammoTypes.YummyGrenade;
import com.mygdx.game.model.ammoTypes.Instastuffed;
import com.mygdx.game.model.ammoTypes.TastyMissile;

import java.util.HashMap;

/**
 * Created by annieaa on 10/03/15.
 */
public class Store {

    private static Store storeInstance;

    // Maps name of the Ammunition to the actual object
    private  HashMap<String, Ammunition> allAmmunition;

    // Maps name of the Ammunition to the store prices
    private HashMap<String, Integer> ammunitionPrices;

    // Maps the name of the upgrade to the store prices
    private HashMap<String, Integer> upgradePrices;

    private Player buyingPlayer;
    private String shownAmmo;

    public Store() {
        allAmmunition = new HashMap<String, Ammunition>();
        ammunitionPrices = new HashMap<String, Integer>();
        upgradePrices = new HashMap<String, Integer>();

        // instantiates and adds all existing ammunition types
        allAmmunition.put("YummyGrenade", new YummyGrenade());
        allAmmunition.put("FlavourRocket", new FlavourRocket());
        allAmmunition.put("DeliBomb", new DeliBomb());
        allAmmunition.put("TastyMissile", new TastyMissile());
        allAmmunition.put("Instastuffed", new Instastuffed());

        // adds all ammunition available for purchase
        /*ammunitionPrices.put("FlavourRocket", 100);
        ammunitionPrices.put("DeliBomb", 250);*/
        ammunitionPrices.put("TastyMissile", 500);
        ammunitionPrices.put("Instastuffed", 1000);

        // adds all upgrades available for purchase
        upgradePrices.put("Health", 1500);
        upgradePrices.put("Fuel", 500);

        shownAmmo = "TastyMissile";

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

    public Ammunition getAmmunition(String name) {
        if (allAmmunition.containsKey(name)) {
            return allAmmunition.get(name);
        }
        return null;
    }

    public Integer getAmmunitionPrice(String ammoName) {
        if (ammunitionPrices.containsKey(ammoName)) {
            return ammunitionPrices.get(ammoName);
        }
        return -1;
    }

    public Integer getUpgradePrice(String upgrade) {
        if (upgradePrices.containsKey(upgrade)) {
            return upgradePrices.get(upgrade);
        }
        return -1;
    }

    public String getNumberOfCurrentAmmo(){
        return "" + buyingPlayer.getInventory().getAmmoLeft(shownAmmo);
    }

    public String getShownAmmo() {
        return shownAmmo;
    }

    public void setShownAmmo(String shownAmmo) {
        this.shownAmmo = shownAmmo;
    }

    public Player getBuyingPlayer() {
        return buyingPlayer;
    }

    public void setBuyingPlayer(Player buyingPlayer) {
        this.buyingPlayer = buyingPlayer;
    }

}
