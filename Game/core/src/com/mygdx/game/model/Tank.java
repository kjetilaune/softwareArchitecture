package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Enums.Team;

/**
 * Created by annieaa on 10/03/15.
 */
public class Tank extends Vehicle {

    // THIS CLASS SHOULD BE RECONSIDERED

    public Tank(Team team, Environment environment, Vector2 position) {
        super(team, environment, position);
    }

}