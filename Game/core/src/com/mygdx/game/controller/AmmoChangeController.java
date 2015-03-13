package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import java.beans.PropertyChangeEvent;

/**
 * Created by Mikal on 10.03.2015.
 */
public class AmmoChangeController extends AbstractController{

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName() == "Next") {

        }
        System.out.println("HEIHEI");
    }
}
