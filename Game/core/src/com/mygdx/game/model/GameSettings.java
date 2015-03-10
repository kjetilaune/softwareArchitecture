package com.mygdx.game.model;

import java.util.ArrayList;

/**
 * Created by annieaa on 10/03/15.
 */
public class GameSettings {

    private static GameSettings instance;
    public int maxPlayers;
    public ArrayList<Player> players;

    private GameSettings() {

    }

    public static GameSettings getInstance() {
        if (instance == null) {
            instance = new GameSettings();
        }
        return instance;
    }

}
