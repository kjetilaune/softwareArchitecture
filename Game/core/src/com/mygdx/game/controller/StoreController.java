package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.mygdx.game.model.Store;
import com.mygdx.game.view.StoreView;
import com.mygdx.game.model.Player;
import java.util.ArrayList;


/**
 * Created by Eplemaskin on 15/04/15.
 */
public class StoreController implements EventListener {

    // view the controller listens to
    private StoreView storeView;

    // model the controller wants to change/access
    private Store storeModel;
    private Player playerModel;

    // array containing all the players
    private ArrayList<Player> players;

    public StoreController(StoreView storeView, Store storeModel, ArrayList<Player> players){
        this.storeView = storeView;
        this.storeModel = storeModel;
        this.players = players;
    }

    private void initiatePurchase() {
        playerModel = storeModel.getBuyingPlayer();
        playerModel.buy(storeModel.getShownAmmo(), 1);
        storeView.setMoneyText("$" + playerModel.getScore() + "\n " + storeModel.getNumberOfCurrentAmmo());
    }


    // needs a more specific name
    private void back() {

        playerModel = storeModel.getBuyingPlayer();


        if (playerModel.equals(players.get(players.size()-1))){
            storeView.newRound();
        }
        else if (playerModel.equals(players.get(players.size()-2))){
            storeModel.setBuyingPlayer(players.get(players.size() - 1));
            cyclePlayersInStore();
            storeView.back.setText("New round");
        }
        else{
            storeModel.setBuyingPlayer(players.get(players.indexOf(playerModel) + 1));
            cyclePlayersInStore();
        }

    }

    public boolean handle (Event event){

        if (event.toString().equals("touchDown")) {
            if (event.getListenerActor().getName().equals("Buy")){
                initiatePurchase();
                return true;
            }
            else if (event.getListenerActor().getName().equals("Back")){
                back();
                return true;
            }
        }

        return false;
    }

    public void cyclePlayersInStore() {
        storeModel.setShownAmmo(storeView.getInitialAmmo());
        storeView.setMoneyText("$" + playerModel.getScore() + "\n " + storeModel.getNumberOfCurrentAmmo());
        storeView.txtCurrentPlayer.setText("Player " + (playerModel.getPlayerNumber()));
    }
}
