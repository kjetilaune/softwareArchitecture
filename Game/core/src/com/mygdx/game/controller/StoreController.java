package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.mygdx.game.gui.AbstractView;

import com.mygdx.game.gui.GameView;
import com.mygdx.game.gui.StoreView;
import com.mygdx.game.model.Ammunition;


import java.beans.PropertyChangeEvent;


/**
 * Created by Eplemaskin on 15/04/15.
 */
public class StoreController extends AbstractController implements EventListener {

    private StoreView storeView;
    private GameView gameView;

    public StoreController(AbstractView storeView, AbstractView gameView){
        super(storeView);
        this.storeView = (StoreView)storeView;
        this.gameView = (GameView)gameView;
    }

/* DEPRECATED
    private void buy(Ammunition ammunition){
        if (view.currentPlayer.getMoney() < ammunition.getCost()){
            System.out.println("Insuficcient funds!");
        }
        else{
            view.currentPlayer.getInventory().increaseAmmo(ammunition, 1);
            view.currentPlayer.setMoney(view.currentPlayer.getMoney() - ammunition.getCost());
            view.setMoneyText("$" + view.currentPlayer.getMoney());
            //view.currentPlayer.getInventory().getAmmoAmount().get(view.currentPlayer.getInventory().getAmmunitions().indexOf(ammunition));
        }
    }
*/
    private void buy() {
        storeView.currentPlayer.buy(storeView.getCurrentAmmo(), 1);
        storeView.setMoneyText("$" + storeView.currentPlayer.getScore());
    }

    private void back() {

        if (storeView.currentPlayer == storeView.players.get(storeView.players.size()-2)){
            storeView.currentPlayer = storeView.players.get(storeView.players.size()-1);
            storeView.currentAmmo = storeView.ammoForPurchase.get(0);
            storeView.txtMoney.setText("$" + storeView.currentPlayer.getScore());
            storeView.txtCurrentPlayer.setText("Player " + (storeView.players.indexOf(storeView.currentPlayer) + 1));
            storeView.back.setText("New round");
        }
        else if (storeView.currentPlayer == storeView.players.get(storeView.players.size()-1)){
            gameView.gameInstance.getRoundWinner();
            gameView.gameInstance.changeRound();
            gameView.dispose();
            storeView.game.setScreen(new GameView(storeView.game, storeView.gameInstance));
        }
        else{
            storeView.currentPlayer = storeView.players.get(storeView.players.indexOf(storeView.currentPlayer));
            storeView.currentAmmo = storeView.ammoForPurchase.get(0);
            storeView.txtMoney.setText("$" + storeView.currentPlayer.getScore());
            storeView.txtCurrentPlayer.setText("Player " + (storeView.players.indexOf(storeView.currentPlayer) + 1));
        }

    }

    public boolean handle (Event event){

        System.out.println(event.toString());

        if (event.toString().equals("touchDown") && event.getListenerActor().getName().equals("Buy")){
            buy();
        }
        else if (event.toString().equals("touchDown") && event.getListenerActor().getName().equals("Back")){
            back();
        }

        return false;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
