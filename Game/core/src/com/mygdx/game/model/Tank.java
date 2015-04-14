package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Enums.Team;

/**
 * Created by annieaa on 10/03/15.
 */
public class Tank extends Vehicle {

    private Environment environment;
    private Barrel barrel;

    public Tank(Team team, Environment environment, Vector2 position) {
        super(team.getTankTexture());
        this.environment = environment;
        super.setPosition(position);
        setRotation(environment.getAngle(getPosition().x, getPosition().x + TextureManager.tank.getWidth()));
        barrel = new Barrel(team, getBarrelPosition(), 45);
        barrel.setRotation(getRotation());
    }

    @Override
    public void setPosition(Vector2 position) {

        Vector2 oldPosition = getPosition();

        // Makes sure that the tank does not escape the screen. Could use minor adjustments on the right side.
        if (position.x < 0) {
           super.setPosition(new Vector2(0, oldPosition.y));
        }
        else if (position.x + TextureManager.tank.getWidth() > Gdx.graphics.getWidth()) {
            super.setPosition(new Vector2(Gdx.graphics.getWidth() - TextureManager.tank.getWidth(), oldPosition.y));
        }
        else {
            super.setPosition(position);
        }

        barrel.setPosition(getBarrelPosition());
        barrel.setRotation(getRotation());

    }



    public Vector2 getBarrelPosition() {

        float tankLeftX = getPosition().x;
        float tankLeftY = getPosition().y;
        float barrelX = tankLeftX + TextureManager.tank.getWidth()/2;
        float barrelY = tankLeftY; //should fix this..

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
