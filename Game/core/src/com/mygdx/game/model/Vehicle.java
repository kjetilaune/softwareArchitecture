package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

/**
 * Created by annieaa on 10/03/15.
 */
public abstract class Vehicle extends GameObject {

    private int health;
    private int fuel;
    private float power;


    public Vehicle(Texture texture) {
        super(texture);
        health = 100;
        fuel = 100;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
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

    public void hit(Ammunition ammo) {
        setHealth(health - ammo.getInitialDamage());
    }

    public void decreaseFuel() {
        setFuel(fuel -= 1);
    }

    public boolean isColliding(Vector2 point) {

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

    private float getRelativeHeight() {

        float leftX = getPosition().x;
        float leftY = getPosition().y;
        float heightX = leftX;
        float heightY = leftY + getTexture().getHeight();

        return rotatePoint(new Vector2(leftX, leftY), new Vector2(heightX, heightY), getRotation()).y - leftY;
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

    private float getRelativeWidth() {
        return 0;
    }
}
