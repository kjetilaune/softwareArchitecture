package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Enums.Team;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by annieaa on 10/03/15.
 */
public class Game extends AbstractModel {

    private Player currentPlayer, roundWinningPlayer, gameWinningPlayer;

    private ArrayList<Player> players;
    private ArrayList<Player> playersAlive;
    private ArrayList<Player> playersDead;

    private Environment environment;
    private int numberOfRounds, currentRound;
    private float roundTime;
    private long startTime, endTime;
    private float elapsedTime;

    private Random random;

    public Game(GameSettings settings) {

        environment = new Environment(4, 10);
        random = new Random();

        numberOfRounds = settings.getNumberOfRounds();
        roundTime = settings.getRoundTime();

        players = new ArrayList<Player>();
        playersAlive = new ArrayList<Player>();
        playersDead = new ArrayList<Player>();

        for (int i = 0 ; i < settings.getTeams().size() ; i ++) {
            players.add(new Player(settings.getTeams().get(i), environment, getStartPosition(i), i+1));
            playersAlive.add(players.get(i));
        }

        currentPlayer = playersAlive.get(0);
        currentRound = 1;
        roundWinningPlayer = null;
        gameWinningPlayer = null;
        
    }



    public void changePlayer() {

        // if any player has been killed in this turn, add them to dead players
        for (Player p : playersAlive) {
            if (!p.isAlive()) {
                playersDead.add(p);
            }
        }

        // update players alive
        for (Player p : playersDead) {
            if (playersAlive.contains(p)) {
                playersAlive.remove(p);
            }
        }

        // update the winning player, is null if there is not yet a winner
        roundWinningPlayer = getRoundWinner();

        // change to next player
        int nextPlayer = -1;
        for (int i = 0 ; i < playersAlive.size() ; i ++) {
            if (playersAlive.get(i) == currentPlayer) {
                nextPlayer = i+1;
                if (i == players.size()-1) {
                    nextPlayer = 0;
                }
            }
        }
        currentPlayer = players.get(nextPlayer);



    }


    // change the round to the next round.
    // If there are no more rounds, the game should be finished.
    public void changeRound() {

        if (currentRound < numberOfRounds) {
            currentRound ++;

            environment = new Environment(4, 10);

            playersAlive.clear();
            playersDead.clear();

            for (int i = 0 ; i < players.size() ; i++) {
                playersAlive.add(players.get(i));
                players.get(i).reset(environment, getStartPosition(i));
            }

        }

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

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }


    // returns the winner of the current round if there is a winner, and null if there is not a winner
    public Player getRoundWinner() {

        if (playersAlive.size() == 1 && playersDead.size() > 0) {
            return playersAlive.get(0);
        }

        return null;
    }

    // calculates and returns the winner of the whole game
    public Player getGameWinner() {

        int maxWon = -1;
        Player winningPlayer = null;

        for (Player p : players) {

            if (p.getRoundsWon() > maxWon) {
                maxWon = p.getRoundsWon();
                winningPlayer = p;
            }

        }
        return winningPlayer;
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


    public void setEnvironment(Environment environment) {this.environment = environment;}

    public Environment getEnvironment() {
        return environment;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public float getRoundTime() {
        return roundTime;
    }

    public void setRoundTime(float roundTime) {
        this.roundTime = roundTime;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

}
