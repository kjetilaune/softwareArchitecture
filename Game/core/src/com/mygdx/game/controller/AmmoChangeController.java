package com.mygdx.game.controller;



import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.gui.AbstractView;

import java.beans.PropertyChangeEvent;

/**
 * Created by Mikal on 10.03.2015.
 */
public class AmmoChangeController extends AbstractController implements EventListener{


    public AmmoChangeController(AbstractView view){
        super(view);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void touchUp(InputEvent event, float x, float y,
                        int pointer, int button) {
        boolean touchdown=true;

        //do your stuff
        //it will work when finger is released..
        System.out.println("Change ammunition");

    }

    public boolean touchDown(InputEvent event, float x, float y,
                             int pointer, int button) {
        boolean touchdown=false;
        //do your stuff it will work when u touched your actor
        return true;
    }
    public boolean handle (Event event){

        if (event.toString() == "touchUp"){
            touchUp((InputEvent)event, event.getTarget().getX(), event.getTarget().getY(), 0, 0);
        }
        return true;
    }


}
