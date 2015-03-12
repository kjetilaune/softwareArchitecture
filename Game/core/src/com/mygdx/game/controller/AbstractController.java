package com.mygdx.game.controller;

import com.mygdx.game.model.AbstractModel;

import org.w3c.dom.views.AbstractView;

import java.beans.PropertyChangeListener;


/**
 * Created by Jonathan on 10.03.2015.
 */
public abstract class AbstractController implements PropertyChangeListener{

    private AbstractView registeredView;
    private AbstractModel registeredModel;

    public AbstractController(){

    }

    public void addModel(AbstractModel model){
        registeredModel = model;
        model.addPropertyChangeListener(this);
    }

    public void removeModel(AbstractModel model){
        registeredModel = null;
        model.removePropertyChangeListener(this);
    }

    public void addView(AbstractView view){
        registeredView = view;
    }
    public void removeView(AbstractView view){
        registeredView = null;
    }
    public AbstractModel getRegisteredModel(){
        return registeredModel;
    }
    public AbstractView getRegisteredView(){
        return registeredView;
    }
    //methods for propertyChangeEvents and setModelPropertiy
}
