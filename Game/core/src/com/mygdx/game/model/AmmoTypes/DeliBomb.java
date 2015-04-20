package com.mygdx.game.model.AmmoTypes;

import com.mygdx.game.model.Ammunition;
import com.mygdx.game.model.SoundManager;

/**
 * Created by Mikal on 20.04.2015.
 */
public class DeliBomb extends Ammunition {

    public DeliBomb() {
        super("DeliBomb", 10, 150, 1.4f, SoundManager.impact2);
    }

}
