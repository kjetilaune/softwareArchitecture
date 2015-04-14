package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Nixa on 3/12/2015.
 */
public class TextureManager {
    public static Texture barrel = new Texture(Gdx.files.internal("object/barrel.png"));
    public static Texture tank = new Texture(Gdx.files.internal("object/tank.png"));

    public static Texture barrelSweets = new Texture(Gdx.files.internal("design/Barrel_Sweets.png"));
    public static Texture tankSweets = new Texture(Gdx.files.internal("design/Tank_Sweets.png"));
    public static Texture barrelFastfood = new Texture(Gdx.files.internal("design/Barrel_Fastfood.png"));
    public static Texture tankFastfood = new Texture(Gdx.files.internal("design/Tank_Fastfood.png"));
    public static Texture barrelVegan = new Texture(Gdx.files.internal("design/Barrel_Vegan.png"));
    public static Texture tankVegan = new Texture(Gdx.files.internal("design/Tank_Vegan.png"));
    public static Texture barrelSeafood = new Texture(Gdx.files.internal("design/Barrel_Seafood.png"));
    public static Texture tankSeafood = new Texture(Gdx.files.internal("design/Tank_Seafood.png"));

    public static Texture burgerBullet = new Texture(Gdx.files.internal("object/burgerBullet.png"));
    public static Texture muffinBullet = new Texture(Gdx.files.internal("object/muffinBullet.png"));

    public static Texture grass = new Texture(Gdx.files.internal("textures/grass.png"));
    public static Texture sand = new Texture(Gdx.files.internal("textures/sand.png"));
    public static Texture ice = new Texture(Gdx.files.internal("textures/ice.png"));
    public static Texture rock = new Texture(Gdx.files.internal("textures/rock.png"));

    public static Texture titleBackground = new Texture(Gdx.files.internal("design/Food-Frenzy-Logo.png"));
    public static Texture menuBackground = new Texture(Gdx.files.internal("design/Food-Frenzy-Menu.png"));
    public static Texture settingsBackground = new Texture(Gdx.files.internal("design/Food-Frenzy-Settings.png"));
    public static Texture aboutBackground = new Texture(Gdx.files.internal("design/Food-Frenzy-About.png"));
    public static Texture storeBackground = new Texture(Gdx.files.internal("design/Food-Frenzy-Store.png"));

}
