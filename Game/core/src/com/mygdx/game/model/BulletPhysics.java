package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Jonathan on 12.03.2015.
 */
public class BulletPhysics {

    private Vector2 startVelocity;
    private Vector2 startDirection;
    private Vector2 startPosition;
    private float speed;
    private final float GRAVITY = -0.9f;

    public BulletPhysics(double angle, float power, float weight, Vector2 startPosition) {
        setStartDirection(angle);
        setInitialSpeed(power, weight);
        decomposeSpeed();
        this.startPosition = startPosition;
    }

    public void setInitialSpeed(float power, float weight){
        // divided power by 2 to match other calculations
        speed = (power/2.0f) / weight;
    }

    public Vector2 setStartDirection(double angle){
        startDirection = new Vector2((float)Math.cos(angle * Math.PI / 180), (float)Math.sin(angle * Math.PI / 180));
        return startDirection;
    }

    public Vector2 decomposeSpeed(){
        startVelocity = new Vector2(startDirection.x*speed, startDirection.y*speed);
        return startVelocity;
    }

    public float getX(float t){
        return startVelocity.x * t + startPosition.x;
    }

    public float getY(float t){
        return 0.5f * GRAVITY * t * t + startVelocity.y * t + startPosition.y;
    }

    public Vector2 getPosition(float t){
        return new Vector2(getX(t), getY(t));
    }

}