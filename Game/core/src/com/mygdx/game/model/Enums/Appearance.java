package com.mygdx.game.model.enums;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.model.audioVisualManagers.TextureManager;

/**
 * Created by annieaa on 19/03/15.
 */
public enum Appearance {
    GRASS(TextureManager.grass, new float[]{(float)(198.0 / 255.0), (float)(226.0 / 255.0), 1, 1}),
    SAND(TextureManager.sand, new float[]{(float)(170.0 / 255.0), (float)(209.0 / 255.0), (float)(238.0 /255.0), 1}),
    ICE(TextureManager.ice, new float[]{(float)(84.0 / 255.0), (float)(144.0 / 255.0), (float)(204.0 /255.0), 1}),
    ROCK(TextureManager.rock, new float[]{(float)(68.0 / 255.0), (float)(186.0 / 255.0), (float)(252.0 /255.0), 1});


    private Texture texture;
    private float[] background;

    private Appearance(Texture texture, float[] background) {
        this.texture = texture;
        this.background = background;
    }

    public Texture getTexture() {
        return texture;
    }

    public float[] getBackground() {
        return background;
    }

}
