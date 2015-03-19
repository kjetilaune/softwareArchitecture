package com.mygdx.game.model;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.controller.FireController;

/**
 * Created by Jonathan on 12.03.2015.
 */
public class BulletPhysics {
    //Draw should be done in ammunition
    public int weight;
    public Vector2 startVelocity;
    public Vector2 startDirection;
    public Vector2 startPosition;
    public int power;
    public float speed;
    public float gravity = -0.9f;
    public Vector2 position = new Vector2();

    public float speed(){
        return speed = power*weight;
    }
    public Vector2 setStartDirection(double angle){
        startDirection = new Vector2((float)Math.cos(angle), (float)Math.sin(angle)); //can't cast it to float
        return startDirection;
    }
    public Vector2 initialSpeed(){
        startVelocity = new Vector2(startDirection.x*speed, startDirection.y*speed);
        return startVelocity;
    }
    public float getX(float t){
        return startVelocity.x*t + startPosition.x;
    }
    public float getY(float t){
        return 0.5f * gravity * t * t + startVelocity.y*t + startPosition.y;
    }
    public Vector2 getPosition(float t){
        position.add(startPosition).add(startVelocity.scl(t)).add(0, gravity*t*t/2);
        return position;
    }



}
