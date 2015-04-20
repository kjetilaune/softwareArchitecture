package com.mygdx.game.model.AmmoTypes;

import com.mygdx.game.model.Ammunition;
import com.mygdx.game.model.SoundManager;

/**
 * Created by Mikal on 12.03.2015.
 */
public class TastyMissile extends Ammunition {

    public TastyMissile() {
        super("TastyMissile", 33, 50, 1.6f, SoundManager.impact2);
    }

}
