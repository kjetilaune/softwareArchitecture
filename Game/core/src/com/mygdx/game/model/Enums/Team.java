package com.mygdx.game.model.Enums;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.model.TextureManager;

/**
 * Created by Jonathan on 12.03.2015.
 */
public enum Team {
    SWEETS("Team Sweets", TextureManager.tankSweets, TextureManager.barrelSweets, TextureManager.bulletSweets, TextureManager.fbombSweets, TextureManager.instakillSweets, TextureManager.storeBulletSweets, TextureManager.storeFbombSweets, TextureManager.storeInstakillSweets, TextureManager.largeTankSweets),
    FAST_FOOD("Team Fast Food", TextureManager.tankFastfood, TextureManager.barrelFastfood, TextureManager.bulletFastfood, TextureManager.fbombFastfood, TextureManager.instakillFastfood, TextureManager.storeBulletFastfood, TextureManager.storeFbombFastfood, TextureManager.storeInstakillFastfood, TextureManager.largeTankFastfood),
    VEGAN("Team Vegan", TextureManager.tankVegan, TextureManager.barrelVegan, TextureManager.bulletVegan, TextureManager.fbombVegan, TextureManager.instakillVegan, TextureManager.storeBulletVegan, TextureManager.storeFbombVegan, TextureManager.storeInstakillVegan, TextureManager.largeTankVegan),
    SEAFOOD("Team Seafood", TextureManager.tankSeafood, TextureManager.barrelSeafood, TextureManager.bulletSeafood, TextureManager.fbombSeafood, TextureManager.instakillSeafood, TextureManager.storeBulletSeafood, TextureManager.storeFbombSeafood, TextureManager.storeInstakillSeafood, TextureManager.largeTankSeafood);

    private Texture vehicleTexture;
    private Texture barrelTexture;
    private Texture bulletTexture;
    private Texture fbombTexture;
    private Texture instakillTexture;
    private Sprite bulletSprite;
    private Sprite fbombSprite;
    private Sprite instakillSprite;
    private Sprite vehicleSprite;
    private String name;

    private Team(String name, Texture tankTexture, Texture barrelTexture, Texture bulletTexture, Texture fbombTexture, Texture instakillTexture, Texture bulletStoreTexture, Texture fbombStoreTexture, Texture instakillStoreTexture, Texture largeTankTexture) {
        this.name = name;
        this.vehicleTexture = tankTexture;
        this.barrelTexture = barrelTexture;
        this.bulletTexture = bulletTexture;
        this.fbombTexture = fbombTexture;
        this.instakillTexture = instakillTexture;
        this.bulletSprite = new Sprite(bulletStoreTexture);
        this.fbombSprite = new Sprite(fbombStoreTexture);
        this.instakillSprite = new Sprite(instakillStoreTexture);
        this.vehicleSprite = new Sprite(largeTankTexture);
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

    public Sprite getVehicleSprite() {
        return vehicleSprite;
    }

    public Texture getTankTexture() {
        return vehicleTexture;
    }

    public Texture getBarrelTexture() {
        return barrelTexture;
    }

    public String getName() {
        return name;
    }
}
