package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Observable;

/**
 * Created by annieaa on 11/03/15.
 */
public abstract class GameObject extends Observable{

    public Vector2 position;
    public Vector2 dimension;
    public Vector2 origin;
    public Vector2 scale;
    public float rotation;

    private Texture texture;

    public GameObject() {
        //position = new Vector2();
        position = null;
        dimension = new Vector2(1, 1);
        origin = new Vector2();
        scale = new Vector2(1, 1);
        rotation = 0;
        texture = null;
    }

    public GameObject(Texture texture) {
        position = null;
        dimension = new Vector2(1, 1);
        origin = new Vector2();
        scale = new Vector2(1, 1);
        rotation = 0;
        this.texture = texture;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        this.setChanged();
        this.notifyObservers(position);
    }

    public void setRotation(float rotation) {

        while (rotation > 360) {
            rotation -= 360;
        }

        while (rotation < 0) {
            rotation += 360;
        }

        this.rotation = rotation;
    }

    public float getRotation() {
        return rotation;
    }

    public Vector2 getPosition() {
        return position;
    }


    public Texture getTexture() {
        return texture;
    }



}
