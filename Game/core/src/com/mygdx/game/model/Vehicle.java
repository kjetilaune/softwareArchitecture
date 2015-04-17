package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Enums.Team;

import java.util.Vector;

/**
 * Created by annieaa on 10/03/15.
 */
public abstract class Vehicle extends GameObject {

    private int health;
    private int fuel;
    private float power;

    private Environment environment;
    private Barrel barrel;

    public Vehicle(Team team, Environment environment, Vector2 position) {
        super(team.getTankTexture());
        super.setPosition(position);
        super.setRotation(environment.getAngle(getPosition().x, getPosition().x + getRelativeWidth(), getRelativeWidth()));

        this.environment = environment;

        barrel = new Barrel(team, getBarrelPosition(), 45);
        barrel.setRotation(getRotation());

        health = 100;
        fuel = 100;
    }


    public Vehicle(Texture texture) {
        super(texture);
        health = 100;
        fuel = 100;
    }

    @Override
    public void setPosition(Vector2 position) {

        Vector2 oldPosition = getPosition();

        // Makes sure that the tank does not escape the screen. Could use minor adjustments on the right side.
        if (position.x < 0) {
            super.setPosition(new Vector2(0, oldPosition.y));
        }
        else if (position.x + getRelativeWidth() > Gdx.graphics.getWidth()) {
            super.setPosition(new Vector2(Gdx.graphics.getWidth() - getRelativeWidth(), oldPosition.y));
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
        float barrelX = tankLeftX + getTexture().getWidth()/2;
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

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health <= 0) {
            this.health = 0;
        }
        else {
            this.health = health;
        }
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        if (fuel <= 0) {
            this.fuel = 0;
        }
        else{
            this.fuel = fuel;
        }
    }

    public void setPower(float power) {
        if (power > 100.0f) {
            this.power = 100.0f;
        }
        else if (power < 0.0f) {
            this.power = 0.0f;
        }
        else {
            this.power = power;
        }
    }

    public float getPower() {
        return power;
    }

    public void reset() {
        health = 100;
        fuel = 100;

    }

    public void takeDamage(Ammunition ammo) {
        setHealth(health - ammo.getInitialDamage());
    }

    public void decreaseFuel() {
        setFuel(fuel - 1);
    }

    public boolean isColliding(Vector2 point) {

        // dead vehicles can't get hit
        if (health <= 0) {
            return false;
        }

        Polygon boundingBox;

        float[] vertices = new float[8];

        vertices[0] = getPosition().x;
        vertices[1] = getPosition().y;
        vertices[2] = rotatePoint(getPosition(), new Vector2(getPosition().x, getPosition().y + getTexture().getHeight()), getRotation()).x;
        vertices[3] = rotatePoint(getPosition(), new Vector2(getPosition().x, getPosition().y + getTexture().getHeight()), getRotation()).y;
        vertices[4] = rotatePoint(getPosition(), new Vector2(getPosition().x + getTexture().getWidth(), getPosition().y + getTexture().getHeight()), getRotation()).x;
        vertices[5] = rotatePoint(getPosition(), new Vector2(getPosition().x + getTexture().getWidth(), getPosition().y + getTexture().getHeight()), getRotation()).y;
        vertices[6] = rotatePoint(getPosition(), new Vector2(getPosition().x + getTexture().getWidth(), getPosition().y), getRotation()).x;
        vertices[7] = rotatePoint(getPosition(), new Vector2(getPosition().x + getTexture().getWidth(), getPosition().y), getRotation()).y;

        boundingBox = new Polygon(vertices);

        // check if ammo hits tank.
        return boundingBox.contains(point.x, point.y);
    }

    public float getRelativeHeight() {

        float leftX = getPosition().x;
        float leftY = getPosition().y;
        float heightX = leftX;
        float heightY = leftY + getTexture().getHeight();

        return Math.abs(rotatePoint(new Vector2(leftX, leftY), new Vector2(heightX, heightY), getRotation()).y - leftY);
    }

    public float getRelativeWidth() {

        float leftX = getPosition().x;
        float leftY = getPosition().y;
        float widthX = leftX + getTexture().getWidth();
        float widthY = getPosition().y;

        return Math.abs(rotatePoint(new Vector2(leftX, leftY), new Vector2(widthX, widthY), getRotation()).x - leftX);
    }


    // rotate a point 'p' around the point 'pivot' by an angle of 'rotation'
    public Vector2 rotatePoint(Vector2 pivot, Vector2 p, float rotation) {
        float pivotX = pivot.x;
        float pivotY = pivot.y;
        float pX = p.x;
        float pY = p.y;

        float s = (float)Math.sin(rotation * Math.PI / 180);
        float c = (float)Math.cos(rotation * Math.PI / 180);

        // translate point back to origin
        pX -= pivotX;
        pY -= pivotY;

        // rotate point
        float newPX = pX * c - pY * s;
        float newPY = pX * s + pY * c;

        // translate point back
        pX = newPX + pivotX;
        pY = newPY + pivotY;

        return new Vector2(pX, pY);
    }


    public void reset(Environment newEnvironment, Vector2 newStartPosition) {

        this.environment = newEnvironment;
        position = newStartPosition;
        super.setRotation(newEnvironment.getAngle(getPosition().x, getPosition().x + getRelativeWidth(), getRelativeWidth()));
        //super.setRotation(newEnvironment.getAngle(getPosition().x, getPosition().x + getTexture().getWidth()));
        barrel.setPosition(getBarrelPosition());
        barrel.setAngle(45);
        barrel.setRotation(getRotation());

    }

}
