package com.mygdx.game.model.ammoTypes;

import com.mygdx.game.model.Ammunition;
import com.mygdx.game.model.audioVisualManagers.SoundManager;

/**
 * Created by Mikal on 12.03.2015.
 */
public class TastyMissile extends Ammunition {

    public TastyMissile() {
        super("TastyMissile", 40, 50, 1.3f, SoundManager.impact2);
    }

}
