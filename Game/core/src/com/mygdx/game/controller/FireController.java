package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.mygdx.game.controller.Threads.FireThread;
import com.mygdx.game.controller.Threads.PowerThread;
import com.mygdx.game.gui.GameView;
import com.mygdx.game.model.Environment;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.Vehicle;
import java.util.ArrayList;

/**
 * Created by Jonathan on 10.03.2015.
 */
public class FireController implements EventListener{

    // view the controller listens to
    private GameView view;

    // models the controller wants to change/access
    private Player playerModel;
    private Vehicle vehicleModel;
    private Environment environmentModel;

    // list of players
    private ArrayList<Player> players;

    // the thread which handles the actual firing
    private FireThread fireThread;

    // the thread used for continuous power fluctuation
    private PowerThread powerThread;

    public FireController(GameView view, Player playerModel, Environment environmentModel, ArrayList<Player> players){
        this.view = view;
        this.playerModel = playerModel;
        this.vehicleModel = playerModel.getVehicle();
        this.environmentModel = environmentModel;
        this.players = players;
    }

    public boolean handle (Event event){

        // first time firebutton is touched, create a power-thread
        if (event.toString().equals("touchDown")) {
            powerThread = new PowerThread();
            return true;
        }

        else if (event.toString().equals("touchUp")) {

            // kills the power-fluctuation-thread
            powerThread.killThread();

            // if touchUp is registered outside firebutton-area
            if (view.outsideFireButton(((InputEvent)event).getStageX(), ((InputEvent)event).getStageY())) {
                // reset power and do not fire
                vehicleModel.setPower(0.0f);
                return true;
            }

            // create a fire-thread which handles firing
            fireThread = new FireThread(view, playerModel, environmentModel, players);
            return true;
        }

        // when finger enters button-area
        else if (event.toString().equals("enter")) {
            // start/continue power-fluctuation
            powerThread.initiateFluctuation(vehicleModel);
            return true;
        }

        // when finger exits button-area
        else if (event.toString().equals("exit")) {
            // end/halt power-fluctuation
            powerThread.endFluctuation();
            return true;
        }

        return false;
    }

}
