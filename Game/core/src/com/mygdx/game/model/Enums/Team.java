package com.mygdx.game.model.Enums;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.model.TextureManager;

/**
 * Created by Jonathan on 12.03.2015.
 */
public enum Team {
    SWEETS(TextureManager.tankSweets, TextureManager.barrelSweets, TextureManager.muffinBullet, "Team Sweets"),
    FAST_FOOD(TextureManager.tankFastfood, TextureManager.barrelFastfood, TextureManager.burgerBullet, "Team Fast Food"),
    VEGAN(TextureManager.tankVegan, TextureManager.barrelVegan, TextureManager.burgerBullet, "Team Vegan"),
    SEAFOOD(TextureManager.tankSeafood, TextureManager.barrelSeafood, TextureManager.burgerBullet, "Team Vegan");

    private Texture tankTexture;
    private Texture barrelTexture;
    private Texture bulletTexture;
    private String name;

    private Team(Texture tankTexture, Texture barrelTexture, Texture bulletTexture, String name) {
        this.tankTexture = tankTexture;
        this.barrelTexture = barrelTexture;
        this.bulletTexture = bulletTexture;
        this.name = name;
    }

    public Texture getAmmunitionTexture(String ammoName) {

        if (ammoName.equals("Bullet")) {
            return bulletTexture;
        }

       return null;
    }

    public Texture getTankTexture() {
        return tankTexture;
    }

    public Texture getBarrelTexture() {
        return barrelTexture;
    }

    public String getName() {
        return name;
    }
}
