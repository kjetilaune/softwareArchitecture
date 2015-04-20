package com.mygdx.game.model;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by annieaa on 10/03/15.
 */
public abstract class Ammunition extends GameObject {

    private String name;
    private int initialDamage, blastRadius;
    private float weight;
    private Sound sound;
    private Sprite image;

    public Ammunition(String name, Integer initialDamage, Integer blastRadius, float weight, Sound sound) {
        this.name = name;
        this.initialDamage = initialDamage;
        this.blastRadius = blastRadius;
        this.weight = weight;
        this.sound = sound;
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

    public int getBlastRadius() {
        return blastRadius;
    }

    public void setBlastRadius(int blastRadius) {
        this.blastRadius = blastRadius;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Sprite getImage() {
        return image;
    }

    public void setImage(Sprite image) {
        this.image = image;
    }

    public Sound getSound(){
        return sound;
    }
    public void setSound(Sound sound) {this.sound = sound;}



}
