package com.mygdx.game.model;

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

    public GameObject() {
        position = new Vector2();
        dimension = new Vector2(1, 1);
        origin = new Vector2();
        scale = new Vector2(1, 1);
        rotation = 0;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        this.setChanged();
        this.notifyObservers(position);
    }

    public Vector2 getPosition() {
        return position;
    }


    public void update(float deltaTime) {

    }



}
