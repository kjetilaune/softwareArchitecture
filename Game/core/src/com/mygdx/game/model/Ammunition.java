package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by annieaa on 10/03/15.
 */
public abstract class Ammunition extends GameObject {

    private String name;
    private int initialDamage;
    private int clusterCount;
    private int blastRadius;
    private int weight;
    private int cost;

    private Sprite image;

    public Ammunition(String name, Integer initialDamage, Integer clusterCount, Integer blastRadius, Integer weight, Integer cost) {
        this.name = name;
        this.initialDamage = initialDamage;
        this.clusterCount = clusterCount;
        this.blastRadius = blastRadius;
        this.weight = weight;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getInitialDamage() {
        return initialDamage;
    }

    public void setInitialDamage(int initialDamage) {
        this.initialDamage = initialDamage;
    }

    public int getClusterCount() {
        return clusterCount;
    }

    public void setClusterCount(int clusterCount) {
        this.clusterCount = clusterCount;
    }

    public int getBlastRadius() {
        return blastRadius;
    }

    public void setBlastRadius(int blastRadius) {
        this.blastRadius = blastRadius;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Sprite getImage() {
        return image;
    }

    public void setImage(Sprite image) {
        this.image = image;
    }

    public int getCost(){ return this.cost; }



}
