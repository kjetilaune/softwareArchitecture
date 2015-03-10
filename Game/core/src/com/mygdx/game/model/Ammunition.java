package com.mygdx.game.model;

/**
 * Created by annieaa on 10/03/15.
 */
public abstract class Ammunition {

    private int initialDamage;
    private int cluserCount;
    private int blastRadious;
    private int weight;

    public int getInitialDamage() {
        return initialDamage;
    }

    public void setInitialDamage(int initialDamage) {
        this.initialDamage = initialDamage;
    }

    public int getCluserCount() {
        return cluserCount;
    }

    public void setCluserCount(int cluserCount) {
        this.cluserCount = cluserCount;
    }

    public int getBlastRadious() {
        return blastRadious;
    }

    public void setBlastRadious(int blastRadious) {
        this.blastRadious = blastRadious;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
