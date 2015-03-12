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
    private GameSettings settings;
    private Store store;
    private int roundsLeft;
    private long startTime, endTime;
    private float elapsedTime;
    private Random random;

    public Game() {

        settings = GameSettings.getInstance();
        store = Store.getInstance();
        random = new Random();

        player1 = new Player(settings.getRoundTime());
        player2 = new Player(settings.getRoundTime());

        players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);

        //environment = new Environment();


        roundsLeft = settings.getNumberOfRounds();
        while (roundsLeft > 0) {
            roundsLeft--;

            currentPlayer = players.get(random.nextInt(2));
            while (currentPlayer.getTimeLeft() > 0) {
                alternatePlayers(currentPlayer);
                startTime = System.nanoTime();


                // THE GAME HAPPENS


                endTime = System.nanoTime();
                elapsedTime = (float)((endTime - startTime)/1000000000);
                currentPlayer.reduceTimeLeft(elapsedTime);
            }

        }
        
    }

    public void alternatePlayers(Player currentPlayer) {
        if (currentPlayer == player1) {
            this.currentPlayer = player2;
        }
        else {
            this.currentPlayer = player1;
        }
    }

}
