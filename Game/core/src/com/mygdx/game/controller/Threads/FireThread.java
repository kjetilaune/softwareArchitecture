package com.mygdx.game.controller.Threads;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.view.GameView;
import com.mygdx.game.model.Ammunition;
import com.mygdx.game.model.BulletPhysics;
import com.mygdx.game.model.Environment;
import com.mygdx.game.model.Game;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.AudioVisualManagers.SoundManager;
import com.mygdx.game.model.Vehicle;

import java.util.ArrayList;

/**
 * Created by annieaa on 07/04/15.
 */
public class FireThread extends Thread {

    // use blinker to safely replace Thread.stop()
    private boolean blinker;

    // view the Fire-controller listens to
    private GameView view;

    // model the Fire-controller wants to change/access
    private Player playerModel;
    private Vehicle vehicleModel;
    private Ammunition ammoModel;
    private Environment environmentModel; // holds the environment to move according to

    // enables sound when firing bullets
    private Sound fire;

    //enables sound when the bullet hits
    private Sound impact;

    // step in animation
    private float step;

    // the laws of physics to determine the trajectory of a bullet
    private BulletPhysics physics;

    // list of players
    private ArrayList<Player> players;


    public FireThread(GameView view, Game gameModel) {
        blinker = true;
        fire = SoundManager.tankFire;

        this.view = view;
        this.playerModel = gameModel.getCurrentPlayer();
        this.players = gameModel.getPlayersAlive();
        this.vehicleModel = playerModel.getVehicle();
        this.ammoModel = playerModel.getChosenAmmo();
        this.environmentModel = gameModel.getEnvironment();
        this.players = gameModel.getPlayersAlive();

        playerModel.getChosenAmmo().setPosition(vehicleModel.getBarrel().getTipOfBarrel());

        physics = new BulletPhysics(vehicleModel.getBarrel().getAngle() + vehicleModel.getRotation(), vehicleModel.getPower(), ammoModel.getWeight(), ammoModel.getPosition());

        step = 0;

        view.setIsFiring(true);

        start();
    }

    public void run() {
        //System.out.println("FireThread started.");

        fire.play(1f);
        playerModel.getInventory().decreaseAmmo(ammoModel.getName(), 1);

        // should run until killThread() is called
        while (blinker) {

            try {

                // checks if the ammunition has collided with the ground
                if (!hasStopped()) {

                    // unsure about the necessity of synchronized
                    // enables only this thread to have access to the ammo during fireThread
                    synchronized (ammoModel) {

                        ammoModel.setPosition(physics.getPosition(step));
                        step += 1;

                    }

                    // cause the thread to halt for 50 ms to prevent the bullet from moving to fast
                    sleep(50);
                }

                else {
                    impact = ammoModel.getSound();
                    impact.play(1f);

                    ammoModel.setPosition(null);
                    if (playerModel.getChosenAmmoAmount() == 0) {
                        playerModel.changeAmmo();
                    }
                    view.setIsFiring(false);
                    vehicleModel.setPower(0.0f);
                    view.changePlayer();

                    for (Player p : players) {
                        p.getVehicle().fallDown();
                    }

                    killThread();
                }
            }
            catch (InterruptedException e) {
                System.err.println("Error in FireThread: " + e.getMessage());
            }
        }

        //System.out.println("FireThread died.");
    }

    public boolean hasStopped() {

        Player hitPlayer = getHitPlayer();

        if (ammoModel.getPosition() == null) {
            return true;
        }

        if (environmentModel.isColliding(ammoModel.getPosition())) {
            environmentModel.collide(ammoModel.getPosition(), ammoModel.getBlastRadius());
            return true;
        }

        else if (hitPlayer != null) {
            //System.out.println("Vehicle has been hit!");

            hitPlayer.getVehicle().takeDamage(ammoModel);

            // award player for hit
            if (!hitPlayer.equals(playerModel)) {
                playerModel.setScore(playerModel.getScore() + ammoModel.getInitialDamage()*50);
            }

            return true;
        }

        else if (isOutOfScreen()) {
            return true;
        }

        return false;
    }

    // returns the player whose vehicle is hit by the bullet, null then no vehicle is hit
    private Player getHitPlayer() {

        Player hit = null;

        for (Player p : players) {
            if (p.getVehicle().isColliding(ammoModel.getPosition())) {
                hit = p;
            }
        }

        return hit;
    }

    private boolean isOutOfScreen() {
        // ammo is to the left of the OR ammo to the right of the screen OR ammo beneath screen
        return ammoModel.getPosition().x < 0 || ammoModel.getPosition().x > Gdx.graphics.getWidth() || ammoModel.getPosition().y < 0;

    }

    // calling this ends the while-loop in run(), stopping the thread from doing anything
    public void killThread() {
        blinker = false;
    }

}
