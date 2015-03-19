package com.mygdx.game.model.Enums;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.model.TextureManager;

/**
 * Created by Jonathan on 12.03.2015.
 */
public enum Team {
    VEGAN(TextureManager.muffinBullet),
    FAST_FOOD(TextureManager.burgerBullet);
    //TAKEOUT,
    //CANDY,
    //FISH;

    private Texture bulletTexture;

    private Team(Texture bulletTexture) {
        this.bulletTexture = bulletTexture;
    }

    public Texture getAmmunitionTexture(String ammoName) {

        if (ammoName.equals("Bullet")) {
            return bulletTexture;
        }

       return null;
    }
}
