package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Nixa on 3/12/2015.
 */
public class TextureManager {
    public static Texture barrelOld = new Texture(Gdx.files.internal("object/barrel.png"));
    public static Texture tankOld = new Texture(Gdx.files.internal("object/tank.png"));

    public static Texture barrel = new Texture(Gdx.files.internal("New design/Barrel_Vegan.png"));
    public static Texture tank = new Texture(Gdx.files.internal("New design/Tank_Vegan.png"));

    public static Texture burgerBullet = new Texture(Gdx.files.internal("object/burgerBullet.png"));
    public static Texture muffinBullet = new Texture(Gdx.files.internal("object/muffinBullet.png"));

    public static Texture grass = new Texture(Gdx.files.internal("textures/grass.png"));
    public static Texture sand = new Texture(Gdx.files.internal("textures/sand.png"));
    public static Texture ice = new Texture(Gdx.files.internal("textures/ice.png"));
    public static Texture rock = new Texture(Gdx.files.internal("textures/rock.png"));

    public static Texture menu = new Texture(Gdx.files.internal("textures/menu.png"));

    public static Texture titleImage = new Texture(Gdx.files.internal("New design/Food-Frenzy-Logo.png"));
    public static Texture menuImage = new Texture(Gdx.files.internal("New design/Food-Frenzy-Menu.png"));

}
