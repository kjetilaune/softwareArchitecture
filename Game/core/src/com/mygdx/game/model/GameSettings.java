package com.mygdx.game.model;

import com.mygdx.game.model.Enums.Team;

import java.util.ArrayList;

/**
 * Created by annieaa on 10/03/15.
 */
public class GameSettings {


    private int nofPlayers, numberOfRounds, numberOfTurns;

    private ArrayList<Team> teams;

    public GameSettings() {

    }


    public int getNofPlayers() {
        return nofPlayers;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNofPlayers(int nofPlayers) {
        this.nofPlayers = nofPlayers;
    }

    public void setNumberOfTurns(int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }
}
