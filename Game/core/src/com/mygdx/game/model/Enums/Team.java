package com.mygdx.game.model.enums;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.model.audioVisualManagers.TextureManager;

/**
 * Created by Jonathan on 12.03.2015.
 */
public enum Team {
    SWEETS("Team Sweets", TextureManager.tankSweets, TextureManager.barrelSweets, TextureManager.yummyGrenadeSweets, TextureManager.tastyMissileSweets, TextureManager.instastuffedSweets, TextureManager.storeYummyGrenadeSweets, TextureManager.storeTastyMissileSweets, TextureManager.storeInstastuffedSweets, TextureManager.largeTankSweets),
    FAST_FOOD("Team Fast Food", TextureManager.tankFastfood, TextureManager.barrelFastfood, TextureManager.yummyGrenadeFastfood, TextureManager.tastyMissileFastfood, TextureManager.instastuffedFastfood, TextureManager.storeYummyGrenadeFastfood, TextureManager.storeTastyMissileFastfood, TextureManager.storeInstastuffedFastfood, TextureManager.largeTankFastfood),
    VEGAN("Team Vegan", TextureManager.tankVegan, TextureManager.barrelVegan, TextureManager.yummyGrenadeVegan, TextureManager.tastyMissileVegan, TextureManager.instastuffedVegan, TextureManager.storeYummyGrenadeVegan, TextureManager.storeTastyMissileVegan, TextureManager.storeInstastuffedVegan, TextureManager.largeTankVegan),
    SEAFOOD("Team Seafood", TextureManager.tankSeafood, TextureManager.barrelSeafood, TextureManager.yummyGrenadeSeafood, TextureManager.tastyMissileSeafood, TextureManager.instastuffedSeafood, TextureManager.storeYummyGrenadeSeafood, TextureManager.storeTastyMissileSeafood, TextureManager.storeInstastuffedSeafood, TextureManager.largeTankSeafood);

    private Texture vehicleTexture;
    private Texture barrelTexture;
    private Texture yummyGrenadeTexture;
    private Texture tastyMissileTexture;
    private Texture instastuffedTexture;
    private Sprite yummyGrenadeSprite;
    private Sprite tastyMissileSprite;
    private Sprite instastuffedSprite;
    private Sprite vehicleSprite;
    private String name;

    private Team(String name, Texture tankTexture, Texture barrelTexture, Texture yummyGrenadeTexture, Texture TastyMissileTexture, Texture instastuffedTexture, Texture yummyGrenadeStoreTexture, Texture tastyMissileStoreTexture, Texture instastuffedStoreTexture, Texture largeTankTexture) {
        this.name = name;
        this.vehicleTexture = tankTexture;
        this.barrelTexture = barrelTexture;
        this.yummyGrenadeTexture = yummyGrenadeTexture;
        this.tastyMissileTexture = TastyMissileTexture;
        this.instastuffedTexture = instastuffedTexture;
        this.yummyGrenadeSprite = new Sprite(yummyGrenadeStoreTexture);
        this.tastyMissileSprite = new Sprite(tastyMissileStoreTexture);
        this.instastuffedSprite = new Sprite(instastuffedStoreTexture);
        this.vehicleSprite = new Sprite(largeTankTexture);
    }

    public Texture getAmmunitionTexture(String ammoName) {

        if (ammoName.equals("YummyGrenade")) {
            return yummyGrenadeTexture;
        }
        else if (ammoName.equals("TastyMissile")) {
            return tastyMissileTexture;
        }
        else if (ammoName.equals("Instastuffed")) {
            return instastuffedTexture;
        }

       return null;
    }

    public Sprite getAmmunitionSprite(String ammoName) {

        if (ammoName.equals("YummyGrenade")) {
            return yummyGrenadeSprite;
        }
        else if (ammoName.equals("TastyMissile")) {
            return tastyMissileSprite;
        }
        else if (ammoName.equals("Instastuffed")) {
            return instastuffedSprite;
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
