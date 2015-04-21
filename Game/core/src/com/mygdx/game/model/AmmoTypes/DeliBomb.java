package com.mygdx.game.model.AmmoTypes;

import com.mygdx.game.model.Ammunition;
import com.mygdx.game.model.AudioVisualManagers.SoundManager;

/**
 * Created by Mikal on 20.04.2015.
 */
public class DeliBomb extends Ammunition {

    public DeliBomb() {
        super("DeliBomb", 15, 150, 1.1f, SoundManager.impact2);
    }

}
