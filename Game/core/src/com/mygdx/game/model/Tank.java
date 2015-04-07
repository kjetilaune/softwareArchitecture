package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by annieaa on 10/03/15.
 */
public class Tank extends Vehicle {

    private Environment environment;
    private Barrel barrel;

    public Tank(Environment environment, Vector2 position) {
        super(TextureManager.tank);
        this.environment = environment;
        super.setPosition(position);
        setRotation(environment.getAngle(getPosition().x, getPosition().x + TextureManager.tank.getWidth()));
        barrel = new Barrel(getBarrelPosition(), 45);
        barrel.setRotation(getRotation());
    }

    @Override
    public void setPosition(Vector2 position) {
        super.setPosition(position);
        barrel.setPosition(getBarrelPosition());
        barrel.setRotation(getRotation());
    }



    public Vector2 getBarrelPosition() {

        float tankLeftX = getPosition().x;
        float tankLeftY = getPosition().y;
        float barrelX = tankLeftX + TextureManager.tank.getWidth()/2;
        float barrelY = tankLeftY;

        float s = (float)Math.sin(getRotation() * Math.PI / 180);
        float c = (float)Math.cos(getRotation() * Math.PI / 180);

        // translate point back to origin
        barrelX -= tankLeftX;
        barrelY -= tankLeftY;

        // rotate point
        float xNewBarrel = barrelX * c - barrelY * s;
        float yNewBarrel = barrelX * s + barrelY * c;

        // translate point back
        barrelX = xNewBarrel + tankLeftX;
        barrelY = yNewBarrel + tankLeftY;

        return new Vector2(barrelX, barrelY);
    }


    public Barrel getBarrel() {
        return barrel;
    }

}
