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

    private Player currentPlayer;


    private ArrayList<Player> players;
    private ArrayList<Player> playersAlive;
    private ArrayList<Player> playersDead;

    private Environment environment;
    private Store store;
    private int numberOfRounds, currentRound;
    private float roundTime;
    private long startTime, endTime;
    private float elapsedTime;
    private Random random;

    public Game(GameSettings settings) {

        store = Store.getInstance();
        environment = new Environment(4, 10);
        random = new Random();

        numberOfRounds = settings.getNumberOfRounds();
        roundTime = settings.getRoundTime();

        players = new ArrayList<Player>();
        playersAlive = new ArrayList<Player>();
        playersDead = new ArrayList<Player>();

        for (int i = 0 ; i < settings.getTeams().size() ; i ++) {
            players.add(new Player(settings.getTeams().get(i), environment, getPosition(i, settings.getTeams().size()), i+1));
            playersAlive.add(players.get(i));
        }

        currentPlayer = playersAlive.get(0);
        currentRound = 1;
        
    }


    // should randomize this, so players are placed "randomly" on the environment
    private Vector2 getPosition(int no, int totalNofPlayers) {

        if (no == 0) {
            return new Vector2(Gdx.graphics.getWidth()/6, environment.getGroundHeight(Gdx.graphics.getWidth()/6));
        }
        else if (no == 1) {
            return new Vector2(5 * Gdx.graphics.getWidth()/6 , environment.getGroundHeight(5 * Gdx.graphics.getWidth()/6));
        }
        return null;
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

        // check if there is a winner
        if (hasWinner() != null) {
            declareWinner(hasWinner());
        }
        else { // if not, change to next player

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

    }


    public void changeRound() {

        if (currentRound < numberOfRounds) {
            currentRound ++;

            environment = new Environment(4, 10);

        }

    }


    public Player hasWinner() {


        if (playersAlive.size() == 1 && playersDead.size() > 0) {
            return playersAlive.get(0);
        }

        return null;
    }

    public void declareWinner(Player winner) {
        System.out.println(String.format("Player %d has won!", winner.getPlayerNumber()));
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
