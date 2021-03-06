package com.mygdx.game.model.AudioVisualManagers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Jonathan on 18.04.2015.
 */
public class SoundManager {

    public static Sound foodFrenzy = Gdx.audio.newSound(Gdx.files.internal("sounds/Foodfrenzy.m4a"));
    public static Sound instaYummy = Gdx.audio.newSound(Gdx.files.internal("sounds/Kill.m4a"));

    public static Sound tankFire = Gdx.audio.newSound(Gdx.files.internal("sounds/tankFire.mp3"));
    public static Sound impact1 = Gdx.audio.newSound(Gdx.files.internal("sounds/grenade.mp3"));
    public static Sound impact2 = Gdx.audio.newSound(Gdx.files.internal("sounds/bigBomb.mp3"));

}
