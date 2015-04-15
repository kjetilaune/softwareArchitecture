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

    //Draw should be done in gameview
    public int weight = 1;
    public Vector2 startVelocity;
    public Vector2 startDirection;
    public Vector2 startPosition;
    public int power;
    public float speed;
    public float gravity = -0.9f;
    public Vector2 position = new Vector2();

    public BulletPhysics(double angle) {
        setStartDirection(angle);
        speed();
        initialSpeed();
    }

    public float speed(){
        speed = power * weight;
        return speed;
    }


    public Vector2 setStartDirection(double angle){
        startDirection = new Vector2((float)Math.cos(angle * Math.PI / 180), (float)Math.sin(angle * Math.PI / 180));
        return startDirection;
    }


    public Vector2 initialSpeed(){
        startVelocity = new Vector2(startDirection.x*speed, startDirection.y*speed);
        return startVelocity;
    }


    public float getX(float t){
        return startVelocity.x * t + startPosition.x;
    }


    public float getY(float t){
        return 0.5f * gravity * t * t + startVelocity.y * t + startPosition.y;
    }


    public Vector2 getPosition(float t){
        /*System.out.println("Start position: " + startPosition.x + ", " + startPosition.y);
        System.out.println("Start velocity; " + startVelocity.x + ", " + startVelocity.y);
        System.out.println("Gravity: " + gravity);
        position.add(startPosition).add(startVelocity.scl(t)).add(0, gravity*t*t/2);
        return position;*/

        return new Vector2(getX(t), getY(t));
    }


    public float getTforGivenX(float x){
        return (x - startPosition.x) / (startVelocity.x);
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
    }

}
