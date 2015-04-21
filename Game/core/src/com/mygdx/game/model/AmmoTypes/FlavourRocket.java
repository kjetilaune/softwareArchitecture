package com.mygdx.game.model.AmmoTypes;

import com.mygdx.game.model.Ammunition;
import com.mygdx.game.model.SoundManager;

/**
 * Created by Mikal on 20.04.2015.
 */
public class FlavourRocket extends Ammunition {

    public FlavourRocket () {
        super("FlavourRocket", 20, 30, 1.2f, SoundManager.impact1);
    }

}
