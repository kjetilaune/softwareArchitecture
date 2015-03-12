package com.mygdx.game.model;

import java.util.ArrayList;

/**
 * Created by annieaa on 10/03/15.
 */
public class Game extends AbstractModel {

    private Player player1, player2;
    private ArrayList<Player> players;
    private Environment environment;
    private GameSettings settings;
    private Store store;

    public Game() {

        settings = GameSettings.getInstance();
        store = Store.getInstance();

        player1 = new Player();
        player2 = new Player();

        //environment = new Environment();


        
    }

}
