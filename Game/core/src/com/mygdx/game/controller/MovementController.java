package com.mygdx.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.gui.AbstractView;
import com.mygdx.game.gui.GameView;
import com.mygdx.game.model.TextureManager;

import java.beans.PropertyChangeEvent;

/**
 * Created by Jonathan on 10.03.2015.
 */
public class MovementController extends AbstractController implements EventListener{
    GameView view;
    boolean touchdown;
    MoveThread m;
    public MovementController(GameView view){
        super(view);
        this.view = view;
        touchdown = false;
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void touchUp(Event event, float x, float y,
                        int pointer, int button) {
        touchdown=false;

    }

    public boolean touchDown(Event event, float x, float y,
                             int pointer, int button) {
        touchdown=true;



        m = new MoveThread(event.getTarget().toString(), x, y, view);
        m.start();
        //System.out.println("TOUCH DOWN");
        //do your stuff it will work when u touched your actor
        return true;
    }
    public boolean handle (Event event){


        if (event.toString() == "touchUp"){
            touchUp(event, event.getTarget().getX(), event.getTarget().getY(), 0, 0);
        }
        else if (event.toString() == "touchDown"){
            touchDown(event, event.getTarget().getX(), event.getTarget().getY(), 0, 0);
        }
        else if (event.toString() == "exit") {
            m.end();
        }

        return true;
    }

}
