package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.mygdx.game.model.Player;

/**
 * Created by Mikal on 10.03.2015.
 */
public class AmmoChangeController implements EventListener{

    // model the controller wants to change/access
    private Player playerModel;

    public AmmoChangeController(Player playerModel){
        this.playerModel = playerModel;
    }

    public boolean handle (Event event){

        // change ammo if ammo-button is pressed
        if (event.toString().equals("touchDown")){
            playerModel.changeAmmo();
            return true;
        }

        return false;
    }

}