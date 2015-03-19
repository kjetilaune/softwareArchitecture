package com.mygdx.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.mygdx.game.gui.GameView;


import java.beans.PropertyChangeEvent;

/**
 * Created by Jonathan on 19.03.2015.
 */
public class AngleController extends AbstractController implements EventListener{
    GameView view;
    Vector2 lastVector = new Vector2();
    public boolean touchdown;

    public AngleController(GameView view){
        super(view);
        this.view = view;
        touchdown = false;
    }

    public void propertyChange(PropertyChangeEvent evt) {

    }
    public void touchUp(Event event, float x, float y, int pointer, int button){
        boolean touchdown = true;
    }
    public boolean touchDown(Event event, float x, float y, int pointer, int button){
        boolean touchdown = false;
        return true;
    }
    public boolean touchDragged(Event event, float x, float y, int pointer, int button){
        Vector2 newVector = new Vector2(x, y);
        Vector2 delta = newVector.cpy().sub(lastVector);
        lastVector = newVector;
        return true;

    }
    public boolean handle(Event event){
        if (event.toString() == "touchUp"){
            touchUp(event, event.getTarget().getX(), event.getTarget().getY(), 0, 0);
        }
        else if (event.toString() == "touchDown") {
            touchDown(event, event.getTarget().getX(), event.getTarget().getY(), 0, 0);
        }
        else if (event.toString() == "touchDragged"){

        }
        return true;
    }
}
