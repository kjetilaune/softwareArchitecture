package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by annieaa on 10/03/15.
 */
public class Game {

    private Player currentPlayer, startingPlayer;

    private ArrayList<Player> players;
    private ArrayList<Player> playersAlive;
    private ArrayList<Player> playersDead;

    private Environment environment;
    private int numberOfRounds, currentRound;
    private int numberOfTurns;

    private Store store;

    private Random random;
    private GameSettings settings;

    public Game(GameSettings settings) {

        this.store = Store.getInstance();
        this.settings = settings;

        environment = initializeEnvironment();

        random = new Random();

        numberOfRounds = settings.getNumberOfRounds();
        numberOfTurns = settings.getNumberOfTurns();

        players = new ArrayList<Player>();
        playersAlive = new ArrayList<Player>();
        playersDead = new ArrayList<Player>();

        for (int i = 0 ; i < settings.getTeams().size() ; i ++) {
            players.add(new Player(settings.getTeams().get(i), environment, getStartPosition(i), i+1));
            playersAlive.add(players.get(i));
        }

        store.setBuyingPlayer(players.get(0));
        currentPlayer = playersAlive.get(0);
        startingPlayer = currentPlayer;
        currentRound = 1;
        
    }



    public void changePlayer() {

        if (numberOfTurns != -1) {
            // increase the current player's number of turns taken by 1
            currentPlayer.setTurnsTaken(currentPlayer.getTurnsTaken()+1);
        }


        // if any player has been killed in this turn, add them to dead players
        for (Player p : playersAlive) {
            if (!p.isAlive()) {
                playersDead.add(p);
            }
        }

        // update players alive
        for (Player p : playersDead) {
            if (playersAlive.contains(p) && !p.equals(currentPlayer)) {
                playersAlive.remove(p);
            }
        }

        // change to next player
        int oldPlayer = playersAlive.indexOf(currentPlayer);
        int nextPlayer = oldPlayer + 1;
        if (nextPlayer >= playersAlive.size()) {
            nextPlayer = 0;
        }

        currentPlayer = playersAlive.get(nextPlayer);

        // remove the old current player if dead (only triggers on suicide)
        if (playersDead.contains(playersAlive.get(oldPlayer))) {
            playersAlive.remove(oldPlayer);
        }

        // update the winning player, is null if there is not yet a winner
        //roundWinningPlayer = getRoundWinner();

    }


    // change the round to the next round.
    // If there are no more rounds, the game should be finished.
    public void changeRound() {

        for (Player p : getRoundWinners()) {
            p.setRoundsWon(p.getRoundsWon() + 1);
        }

        //getRoundWinner().setRoundsWon(getRoundWinner().getRoundsWon() + 1);

        if (currentRound < numberOfRounds) {
            currentRound ++;

            environment = initializeEnvironment();

            playersAlive.clear();
            playersDead.clear();

            for (int i = 0 ; i < players.size() ; i++) {
                playersAlive.add(players.get(i));
                players.get(i).reset(environment, getStartPosition(i));
            }
            // Starting player switches at each round
            if (playersAlive.indexOf(startingPlayer) == playersAlive.size()-1) {
                currentPlayer = playersAlive.get(0);
            }
            else {
                currentPlayer = playersAlive.get(playersAlive.indexOf(startingPlayer)+1);
            }
            startingPlayer = currentPlayer;

        }

    }


    // returns the winner of the current round if there is a winner, and null if there is not a winner
    public ArrayList<Player> getRoundWinners() {

        if (playersAlive.size() == 1 && playersDead.size() > 0) {
            return new ArrayList<Player>(Arrays.asList(playersAlive.get(0)));
        }

        // check if no player has turns left
        boolean noTurnsLeft = true;
        for (Player p : playersAlive) {
            if (getNumberOfTurns() - p.getTurnsTaken() != 0) {
                noTurnsLeft = false;
            }
        }

        // if so, the player with the most health left should win the round
        if (noTurnsLeft) {
            return getPlayersMaxHealth();
        }

        return null;
    }

    // calculates and returns the winner of the whole game
    public ArrayList<Player> getGameWinners() {

        if (numberOfRounds == 1) {
            if (playersAlive.size() == 1 && playersDead.size() > 0) {
                return new ArrayList<Player>(Arrays.asList(playersAlive.get(0)));
            }

            return getPlayersMaxHealth();

        }
        else {
            return getPlayersMaxRoundsWon();
        }

    }

    // where to place the players in the beginning
    // should randomize this, so players are placed "randomly" on the environment
    private Vector2 getStartPosition(int no) {

        if (no == 0) {
            return new Vector2(Gdx.graphics.getWidth()/6, environment.getGroundHeight(Gdx.graphics.getWidth()/6));
        }
        else if (no == 1) {
            return new Vector2(5 * Gdx.graphics.getWidth()/6 , environment.getGroundHeight(5 * Gdx.graphics.getWidth()/6));
        }
        return null;
    }

    private ArrayList<Player> getPlayersMaxHealth() {

        ArrayList<Player> winners = new ArrayList<Player>();

        int maxHealth = -1;
        for (Player p : players) {
            if (p.getVehicle().getHealth() > maxHealth) {
                maxHealth = p.getVehicle().getHealth();
            }
        }

        for (Player p : players) {
            if (p.getVehicle().getHealth() == maxHealth) {
                winners.add(p);
            }
        }

        return winners;
    }

    private ArrayList<Player> getPlayersMaxRoundsWon() {

        ArrayList<Player> winners = new ArrayList<Player>();

        int maxRoundsWon = -1;
        for (Player p : players) {
            if (p.getRoundsWon() > maxRoundsWon) {
                maxRoundsWon = p.getRoundsWon();
            }
        }

        for (Player p : players) {
            if (p.getRoundsWon() == maxRoundsWon) {
                winners.add(p);
            }
        }

        return winners;
    }

    // Create environment based on difficulty
    private Environment initializeEnvironment() {

        if (settings.getDifficulty().equals("Easy")) {
            return new Environment(2, 10);
        }
        else if(settings.getDifficulty().equals("Medium")) {
            return new Environment(4, 10);
        }
        else if(settings.getDifficulty().equals("Difficult")) {
            return new Environment(6, 10);
        }
        return null;

    }

    public ArrayList<Player> getPlayersAlive() {
        return playersAlive;
    }

    public int getRoundsLeft() {
        return numberOfRounds - currentRound;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Store getStore() { return store; }

}
