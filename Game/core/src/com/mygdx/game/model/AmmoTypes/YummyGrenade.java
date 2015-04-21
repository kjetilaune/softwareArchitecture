package com.mygdx.game.model.ammoTypes;

import com.mygdx.game.model.Ammunition;
import com.mygdx.game.model.audioVisualManagers.SoundManager;

/**
 * Created by Mikal on 12.03.2015.
 */
public class YummyGrenade extends Ammunition {

    public YummyGrenade() {
        super("YummyGrenade", 10, 20, 1.0f, SoundManager.impact1);
    }

}
