package com.mygdx.game.model;

import java.util.HashMap;

/**
 * Created by annieaa on 10/03/15.
 */
public class Store {

    private HashMap<Ammunition, Integer> ammunitionPrices;
    private HashMap<String, Integer> upgradePrices;

    public HashMap<Ammunition, Integer> getAmmunitionPrices() {
        return ammunitionPrices;
    }

    public void setAmmunitionPrices(HashMap<Ammunition, Integer> ammunitionPrices) {
        this.ammunitionPrices = ammunitionPrices;
    }

    public HashMap<String, Integer> getUpgradePrices() {
        return upgradePrices;
    }

    public void setUpgradePrices(HashMap<String, Integer> upgradePrices) {
        this.upgradePrices = upgradePrices;
    }
    

}
