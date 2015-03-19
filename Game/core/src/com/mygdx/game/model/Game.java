package com.mygdx.game.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by annieaa on 10/03/15.
 */
public class Game extends AbstractModel {

    private Player player1, player2, currentPlayer;
    private ArrayList<Player> players;
    private Environment environment;
    private Store store;
    private int numberOfRounds, roundsLeft;
    private float roundTime;
    private long startTime, endTime;
    private float elapsedTime;
    private Random random;

    public Game() {

        store = Store.getInstance();
        environment = new Environment(2, 10);
        random = new Random();

        player1 = new Player(environment);
        player2 = new Player(environment);

        players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);

        currentPlayer = player1;
        
    }

    // må gjøre en hel masse sjekker her
    public void alternatePlayers(Player currentPlayer) {
        if (currentPlayer == player1) {
            this.currentPlayer = player2;
        }
        else {
            this.currentPlayer = player1;
        }
    }

    public void changePlayer() {
        if (currentPlayer == player1) {
            this.currentPlayer = player2;
        }
        else {
            this.currentPlayer = player1;
        }
    }

    public Environment getEnvironment() {
        return environment;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

}
