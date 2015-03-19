package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Nixa on 3/12/2015.
 */
public class TextureManager {
    public static Texture barrel = new Texture(Gdx.files.internal("object/barrel.png"));
    public static Texture tank = new Texture(Gdx.files.internal("object/tank.png"));

    public static Texture grass = new Texture(Gdx.files.internal("textures/grass.png"));
    public static Texture sand = new Texture(Gdx.files.internal("textures/sand.png"));
    public static Texture ice = new Texture(Gdx.files.internal("textures/ice.png"));
    public static Texture rock = new Texture(Gdx.files.internal("textures/rock.png"));
}
