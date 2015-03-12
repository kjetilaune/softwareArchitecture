package com.mygdx.game.model;

/**
 * Created by annieaa on 10/03/15.
 */
public class GameSettings {

    private static GameSettings settingsInstance;

    private int maxPlayers, roundTime, numberOfRounds;

    private GameSettings() {
        this.maxPlayers = 2;
    }

    public static GameSettings getInstance() {
        if (settingsInstance == null) {
            settingsInstance = new GameSettings();
        }
        return settingsInstance;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getRoundTime() {
        return roundTime;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public void setRoundTime(int roundTime) {
        this.roundTime = roundTime;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }
}
