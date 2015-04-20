package com.mygdx.game.model.AmmoTypes;

import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.model.Ammunition;
import com.mygdx.game.model.SoundManager;

/**
 * Created by Mikal on 12.03.2015.
 */
public class Instastuffed extends Ammunition {

    public Instastuffed() {
        super("Instastuffed", 100, 15, 2.0f, SoundManager.instaYummy);
    }

}