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

    // array containing all the players
    private ArrayList<Player> players;

    public StoreController(StoreView storeView, Store storeModel, ArrayList<Player> players){
        this.storeView = storeView;
        this.storeModel = storeModel;
        this.players = players;
    }

    private void initiatePurchase() {
        storeModel.getBuyingPlayer().buy(storeModel.getShownAmmo(), 1);
        storeView.setMoneyText("$" + storeModel.getBuyingPlayer().getScore() + "\n " + storeModel.getNumberOfCurrentAmmo());
    }


    // needs a more specific name
    private void back() {

        if (storeModel.getBuyingPlayer().equals(players.get(players.size()-1))){
            storeModel.setBuyingPlayer(players.get(0));
            storeView.newRound();
        }
        else if (storeModel.getBuyingPlayer().equals(players.get(players.size()-2))){
            storeModel.setBuyingPlayer(players.get(players.size() - 1));
            cyclePlayersInStore();
            storeView.setBackText("New round");
        }
        else{
            storeModel.setBuyingPlayer(players.get(players.indexOf(storeModel.getBuyingPlayer()) + 1));
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
        storeModel.setShownAmmo(storeModel.getInitialAmmo());
        storeView.setMoneyText("$" + storeModel.getBuyingPlayer().getScore() + "\n " + storeModel.getNumberOfCurrentAmmo());
        storeView.setCurrentPlayerText("Player " + (storeModel.getBuyingPlayer().getPlayerNumber()));
    }
}
