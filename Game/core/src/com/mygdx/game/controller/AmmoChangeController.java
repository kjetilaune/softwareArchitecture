package com.mygdx.game.controller;



import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.gui.AbstractView;
import com.mygdx.game.gui.GameView;

import java.beans.PropertyChangeEvent;

/**
 * Created by Mikal on 10.03.2015.
 */
public class AmmoChangeController extends AbstractController implements EventListener{

    private GameView view;

    public AmmoChangeController(AbstractView view){
        super(view);
        this.view = (GameView)view;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public boolean handle (Event event){

        if (event.toString().equals("touchDown")){
            view.currentPlayer.changeAmmo();
            return true;
        }

        return false;
    }


}
