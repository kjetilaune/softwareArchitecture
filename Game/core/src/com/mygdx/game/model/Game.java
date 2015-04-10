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

        Vector2 position1 = new Vector2(Gdx.graphics.getWidth()/6, environment.getGroundHeight(Gdx.graphics.getWidth()/6));
        Vector2 position2 = new Vector2(5 * Gdx.graphics.getWidth()/6 , environment.getGroundHeight(5 * Gdx.graphics.getWidth()/6));

        player1 = new Player(Team.FAST_FOOD, environment, position1);
        player2 = new Player(Team.VEGAN, environment, position2);

        players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);

        currentPlayer = player1;
        
    }

    public void runGame() {

        while (hasWinner() == null) {
            // run game




        }

    }


    public void changePlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        }
        else {
            this.currentPlayer = player1;
        }
        this.setChanged();
        this.notifyObservers(currentPlayer);
        this.firePropertyChange("Player changed", null, getCurrentPlayer());
    }

    public Player hasWinner() {

        /*for (Player p : players) {
            if (p.getVehicle().getHealth() <= 0) {

            }
        }*/

        if (player1.getVehicle().getHealth() <= 0) {
            return player2;
        }
        else if (player2.getVehicle().getHealth() <= 0) {
            return player1;
        }

        return null;
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
