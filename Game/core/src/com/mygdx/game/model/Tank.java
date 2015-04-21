package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.enums.Team;

/**
 * Created by annieaa on 10/03/15.
 */
public class Tank extends Vehicle {


    // ***THIS CLASS SHOULD BE RESTRUCTURED. MOST OF ITS CONTENT SHOULD BE MOVED TO VEHICLE***



    private Environment environment;
    private Barrel barrel;

    public Tank(Team team, Environment environment, Vector2 position) {
        super(team, environment, position);
    }



    /*public void reset(Environment newEnvironment, Vector2 newStartPosition) {

        super.reset();
        this.environment = newEnvironment;
        position = newStartPosition;
        super.setRotation(newEnvironment.getAngle(getPosition().x, getPosition().x + getTexture().getWidth()));
        barrel.setPosition(getBarrelPosition());
        barrel.setAngle(45);
        barrel.setRotation(getRotation());

    }*/








}
