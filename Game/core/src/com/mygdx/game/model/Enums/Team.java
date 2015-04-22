package com.mygdx.game.model.Enums;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.model.AudioVisualManagers.TextureManager;

/**
 * Created by Jonathan on 12.03.2015.
 */
public enum Team {
    SWEETS("Team Sweets", TextureManager.vehicleSweets, TextureManager.barrelSweets, TextureManager.yummyGrenadeSweets, TextureManager.flavourRocketSweets, TextureManager.deliBombSweets, TextureManager.tastyMissileSweets, TextureManager.instastuffedSweets, TextureManager.storeYummyGrenadeSweets, TextureManager.storeFlavourRocketSweets, TextureManager.storeDeliBombSweets, TextureManager.storeTastyMissileSweets, TextureManager.storeInstastuffedSweets, TextureManager.largeVehicleSweets),
    FAST_FOOD("Team Fast Food", TextureManager.vehicleFastfood, TextureManager.barrelFastfood, TextureManager.yummyGrenadeFastfood, TextureManager.flavourRocketFastfood, TextureManager.deliBombFastfood, TextureManager.tastyMissileFastfood, TextureManager.instastuffedFastfood, TextureManager.storeYummyGrenadeFastfood, TextureManager.storeFlavourRocketFastfood, TextureManager.storeDeliBombFastfood, TextureManager.storeTastyMissileFastfood, TextureManager.storeInstastuffedFastfood, TextureManager.largeVehicleFastfood),
    VEGAN("Team Vegan", TextureManager.vehicleVegan, TextureManager.barrelVegan, TextureManager.yummyGrenadeVegan, TextureManager.flavourRocketVegan, TextureManager.deliBombVegan, TextureManager.tastyMissileVegan, TextureManager.instastuffedVegan, TextureManager.storeYummyGrenadeVegan, TextureManager.storeFlavourRocketVegan, TextureManager.storeDeliBombVegan, TextureManager.storeTastyMissileVegan, TextureManager.storeInstastuffedVegan, TextureManager.largeVehicleVegan),
    SEAFOOD("Team Seafood", TextureManager.vehicleSeafood, TextureManager.barrelSeafood, TextureManager.yummyGrenadeSeafood, TextureManager.flavourRocketSeafood, TextureManager.deliBombSeafood, TextureManager.tastyMissileSeafood, TextureManager.instastuffedSeafood, TextureManager.storeYummyGrenadeSeafood, TextureManager.storeFlavourRocketSeafood, TextureManager.storeDeliBombSeafood, TextureManager.storeTastyMissileSeafood, TextureManager.storeInstastuffedSeafood, TextureManager.largeVehicleSeafood);

    private Texture vehicleTexture;
    private Texture barrelTexture;
    private Texture yummyGrenadeTexture;
    private Texture flavourRocketTexture;
    private Texture deliBombTexture;
    private Texture tastyMissileTexture;
    private Texture instastuffedTexture;
    private Sprite yummyGrenadeSprite;
    private Sprite flavourRocketSprite;
    private Sprite deliBombSprite;
    private Sprite tastyMissileSprite;
    private Sprite instastuffedSprite;
    private Sprite vehicleSprite;
    private String name;

    private Team(String name, Texture vehicleTexture, Texture barrelTexture, Texture yummyGrenadeTexture, Texture flavourRocketTexture, Texture deliBombTexture, Texture tastyMissileTexture, Texture instastuffedTexture, Texture yummyGrenadeStoreTexture, Texture flavourRocketStoreTexture, Texture deliBombStoreTexture, Texture tastyMissileStoreTexture, Texture instastuffedStoreTexture, Texture largeTankTexture) {
        this.name = name;
        this.vehicleTexture = vehicleTexture;
        this.barrelTexture = barrelTexture;
        this.yummyGrenadeTexture = yummyGrenadeTexture;
        this.flavourRocketTexture = flavourRocketTexture;
        this.deliBombTexture = deliBombTexture;
        this.tastyMissileTexture = tastyMissileTexture;
        this.instastuffedTexture = instastuffedTexture;
        this.yummyGrenadeSprite = new Sprite(yummyGrenadeStoreTexture);
        this.flavourRocketSprite = new Sprite(flavourRocketStoreTexture);
        this.deliBombSprite = new Sprite(deliBombStoreTexture);
        this.tastyMissileSprite = new Sprite(tastyMissileStoreTexture);
        this.instastuffedSprite = new Sprite(instastuffedStoreTexture);
        this.vehicleSprite = new Sprite(largeTankTexture);
    }

    public Texture getAmmunitionTexture(String ammoName) {

        if (ammoName.equals("YummyGrenade")) {
            return yummyGrenadeTexture;
        }
        else if (ammoName.equals("FlavourRocket")) {
            return flavourRocketTexture;
        }
        else if (ammoName.equals("DeliBomb")) {
            return deliBombTexture;
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
        else if (ammoName.equals("FlavourRocket")) {
            return flavourRocketSprite;
        }
        else if (ammoName.equals("DeliBomb")) {
            return deliBombSprite;
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

    public Texture getVehicleTexture() {
        return vehicleTexture;
    }

    public Texture getBarrelTexture() {
        return barrelTexture;
    }

    public String getName() {
        return name;
    }
}
