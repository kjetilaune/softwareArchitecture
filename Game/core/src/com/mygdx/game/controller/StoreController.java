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

    private StoreView view;
    private GameView gameView;

    public StoreController(AbstractView view, AbstractView gameView){
        super(view);
        this.view = (StoreView)view;
        this.gameView = (GameView)gameView;
    }


    // NOTE TO KJETIL: the inventory already has buyAmmunition- and buyUpgrade-method
    // note that these uses the score instead of money, although their function is the same and one should be removed
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
/*
    private void nextPlayer() {
        if (view.currentPlayer == view.players.get(view.players.size()-2)){
            view.currentPlayer = view.players.get(view.players.size()-1);
            view.currentAmmo = view.ammos.get(0);
            view.txtMoney.setText("$" + view.currentPlayer.getMoney());
            view.txtCurrentPlayer.setText("Player " + (view.players.indexOf(view.currentPlayer) + 1));
            view.back.setText("New round");
        }
        else if (view.currentPlayer == view.players.get(view.players.size()-1)){
            gameView.gameInstance.changeRound();
            view.game.setScreen(gameView);
        }
        else{
            view.currentPlayer = view.players.get(view.players.indexOf(view.currentPlayer));
            view.currentAmmo = view.ammos.get(0);
            view.txtMoney.setText("$" + view.currentPlayer.getMoney());
            view.txtCurrentPlayer.setText("Player " + (view.players.indexOf(view.currentPlayer) + 1));
        }
    }*/

    private void newRound() {
        gameView.gameInstance.changeRound();
        view.game.setScreen(gameView);
    }

    public boolean handle (Event event){
        //System.out.println();
        if (event.getListenerActor().getName().equals("Buy")) {
            System.out.println("buy");
            buy(view.currentAmmo);
        }
        else if (event.getListenerActor().getName().equals("NewRound")){
            System.out.println("new round");
            newRound();
        }

        return false;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
