package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.mygdx.game.gui.AbstractView;

import com.mygdx.game.gui.StoreView;
import com.mygdx.game.model.Ammunition;


import java.beans.PropertyChangeEvent;


/**
 * Created by Eplemaskin on 15/04/15.
 */
public class StoreController extends AbstractController implements EventListener {

    private StoreView view;

    public StoreController(AbstractView view){
        super(view);
        this.view = (StoreView)view;
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

    private void newRound() {
        gameView.gameInstance.changeRound();
        view.game.setScreen(gameView);
    }

    public boolean handle (Event event){
        System.out.println();
        if (event.toString().equals("touchDown")){
            buy(view.currentAmmo);
        }
<<<<<<< HEAD
        else if (event.getListenerActor().getName().equals("NewRound")){
            System.out.println("new round");
            newRound();
        }
=======
>>>>>>> 8bcc0eff60e21c15a4bef3087b67c3ce8f6c94d9

        return false;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
