package com.mygdx.game.model.Enums;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.model.TextureManager;

/**
 * Created by Jonathan on 12.03.2015.
 */
public enum Team {
    SWEETS("Team Sweets", TextureManager.tankSweets, TextureManager.barrelSweets, TextureManager.bulletSweets, TextureManager.fbombSweets, TextureManager.instakillSweets, TextureManager.bulletSweets, TextureManager.fbombSweets, TextureManager.instakillSweets),
    FAST_FOOD("Team Fast Food", TextureManager.tankFastfood, TextureManager.barrelFastfood, TextureManager.bulletFastfood, TextureManager.fbombFastfood, TextureManager.instakillFastfood, TextureManager.bulletFastfood, TextureManager.fbombFastfood, TextureManager.instakillFastfood),
    VEGAN("Team Vegan", TextureManager.tankVegan, TextureManager.barrelVegan, TextureManager.bulletVegan, TextureManager.fbombVegan, TextureManager.instakillVegan, TextureManager.bulletVegan, TextureManager.fbombVegan, TextureManager.instakillVegan),
    SEAFOOD("Team Seafood", TextureManager.tankSeafood, TextureManager.barrelSeafood, TextureManager.bulletSeafood, TextureManager.fbombSeafood, TextureManager.instakillSeafood, TextureManager.bulletSeafood, TextureManager.fbombSeafood, TextureManager.instakillSeafood);

    private Texture tankTexture;
    private Texture barrelTexture;
    private Texture bulletTexture;
    private Texture fbombTexture;
    private Texture instakillTexture;
    private Sprite bulletSprite;
    private Sprite fbombSprite;
    private Sprite instakillSprite;
    private String name;

    private Team(String name, Texture tankTexture, Texture barrelTexture, Texture bulletTexture, Texture fbombTexture, Texture instakillTexture, Texture bulletStoreTexture, Texture fbombStoreTexture, Texture instakillStoreTexture) {
        this.name = name;
        this.tankTexture = tankTexture;
        this.barrelTexture = barrelTexture;
        this.bulletTexture = bulletTexture;
        this.fbombTexture = fbombTexture;
        this.instakillTexture = instakillTexture;
        this.bulletSprite = new Sprite(bulletStoreTexture);
        this.fbombSprite = new Sprite(fbombStoreTexture);
        this.instakillSprite = new Sprite(instakillStoreTexture);
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

    public Sprite getAmmunitionSprite(String ammoName) {

        if (ammoName.equals("Bullet")) {
            return bulletSprite;
        }
        else if (ammoName.equals("F-Bomb")) {
            return fbombSprite;
        }
        else if (ammoName.equals("Instakill")) {
            return instakillSprite;
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
