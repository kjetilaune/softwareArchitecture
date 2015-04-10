package com.mygdx.game.model.Enums;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.model.TextureManager;

/**
 * Created by Jonathan on 12.03.2015.
 */
public enum Team {
    VEGAN(TextureManager.muffinBullet, "Team Vegan"),
    FAST_FOOD(TextureManager.burgerBullet, "Team Fast Food");
    //TAKEOUT,
    //CANDY,
    //FISH;

    private Texture bulletTexture;
    private String name;

    private Team(Texture bulletTexture, String name) {
        this.bulletTexture = bulletTexture;
        this.name = name;
    }

    public Texture getAmmunitionTexture(String ammoName) {

        if (ammoName.equals("Bullet")) {
            return bulletTexture;
        }

       return null;
    }

    public String getName() {
        return name;
    }
}