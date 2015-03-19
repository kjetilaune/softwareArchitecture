package com.mygdx.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.mygdx.game.gui.GameView;
import com.mygdx.game.model.TextureManager;

/**
 * Created by Mikal on 17.03.2015.
 */
public class MoveThread extends Thread {

    private String direction;
    private float x, y;
    private GameView view;
    private boolean heldDown;

    public MoveThread(String direction, float x, float y, GameView view) {
        this.heldDown = true;
        this.direction  = direction;
        this.x = x;
        this.y = y;
        this.view = view;

    }

    public void run() {
        System.out.println("MoveThread started.");
        while (heldDown) {

            this.view.currentTank.setRotation(view.environment.getAngle(view.currentTank.getPosition().x, view.currentTank.getPosition().x + TextureManager.tank.getWidth()));
            if (direction.equals("arrowLeft")){
                this.view.currentTank.setPosition(new Vector2(view.currentTank.getPosition().x - 10,view.environment.getGroundHeight(view.currentTank.getPosition().x - 10)));
            }
            else if (direction.equals("arrowRight")){
                this.view.currentTank.setPosition(new Vector2(view.currentTank.getPosition().x + 10,view.environment.getGroundHeight(view.currentTank.getPosition().x + 10)));
            }

            try { Thread.sleep(100); }
            catch (InterruptedException e) {}

        }
    }

    public static void main(String args[]) {

    }

    public void end() {
        heldDown = false;
    }


}
