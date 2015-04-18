package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Jonathan on 18.04.2015.
 */
public class SoundManager {
    public Sound tankFire = Gdx.audio.newSound(Gdx.files.internal("sounds/tankFire.mp3"));
    public Sound impact1 = Gdx.audio.newSound(Gdx.files.internal("sounds/grenade.mp3"));
    public Sound impact2 = Gdx.audio.newSound(Gdx.files.internal("sounds/bigBomb.mp3"));
}
