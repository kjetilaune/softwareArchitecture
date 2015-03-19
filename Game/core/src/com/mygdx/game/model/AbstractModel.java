package com.mygdx.game.model;

/**
 * Created by Jonathan on 11.03.2015.
 */

import com.badlogic.gdx.utils.Json; //don't know if we should use this or the java.io

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Observable;

public abstract class AbstractModel extends Observable {

    protected PropertyChangeSupport propChangeSupport;

    public AbstractModel(){
        propChangeSupport = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        propChangeSupport.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener){
        propChangeSupport.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue){
        propChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }


}
