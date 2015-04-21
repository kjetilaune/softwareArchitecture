package com.mygdx.game.model.ammoTypes;

import com.mygdx.game.model.Ammunition;
import com.mygdx.game.model.audioVisualManagers.SoundManager;

/**
 * Created by Mikal on 12.03.2015.
 */
public class Instastuffed extends Ammunition {

    public Instastuffed() {
        super("Instastuffed", 100, 20, 1.4f, SoundManager.instaYummy);
    }

}