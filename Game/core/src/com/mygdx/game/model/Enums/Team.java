package com.mygdx.game.model.Enums;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.model.TextureManager;

/**
 * Created by Jonathan on 12.03.2015.
 */
public enum Team {
    SWEETS("Team Sweets", TextureManager.tankSweets, TextureManager.barrelSweets, TextureManager.bulletSweets, TextureManager.fbombSweets, TextureManager.instakillSweets ),
    FAST_FOOD("Team Fast Food", TextureManager.tankFastfood, TextureManager.barrelFastfood, TextureManager.bulletFastfood, TextureManager.fbombFastfood, TextureManager.instakillFastfood),
    VEGAN("Team Vegan", TextureManager.tankVegan, TextureManager.barrelVegan, TextureManager.bulletVegan, TextureManager.fbombVegan, TextureManager.instakillVegan),
    SEAFOOD("Team Seafood", TextureManager.tankSeafood, TextureManager.barrelSeafood, TextureManager.bulletSeafood, TextureManager.fbombSeafood, TextureManager.instakillSeafood);

    private Texture tankTexture;
    private Texture barrelTexture;
    private Texture bulletTexture;
    private Texture fbombTexture;
    private Texture instakillTexture;
    private String name;

    private Team(String name, Texture tankTexture, Texture barrelTexture, Texture bulletTexture, Texture fbombTexture, Texture instakillTexture) {
        this.name = name;
        this.tankTexture = tankTexture;
        this.barrelTexture = barrelTexture;
        this.bulletTexture = bulletTexture;
        this.fbombTexture = fbombTexture;
        this.instakillTexture = instakillTexture;
    }

    public Texture getAmmunitionTexture(String ammoName) {

        if (ammoName.equals("Bullet")) {
            return bulletTexture;
        }
        else if (ammoName.equals("F-Bomb")) {
            return fbombTexture;
        }
        else if (ammoName.equals("Instakill")) {
            return instakillTexture;
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
