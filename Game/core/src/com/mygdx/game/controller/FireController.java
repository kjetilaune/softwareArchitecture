package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.mygdx.game.controller.Threads.FireThread;
import com.mygdx.game.controller.Threads.PowerThread;
import com.mygdx.game.view.GameView;
import com.mygdx.game.model.Game;
import com.mygdx.game.model.Player;
import java.util.ArrayList;

/**
 * Created by Jonathan on 10.03.2015.
 */
public class FireController implements EventListener{

    // view the controller listens to
    private GameView view;

    // model the controller wants to change/access
    private Game gameModel;

    // the thread which handles the actual firing
    private FireThread fireThread;

    // the thread used for continuous power fluctuation
    private PowerThread powerThread;

    public FireController(GameView view, Game gameModel){
        this.view = view;
        this.gameModel = gameModel;
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
                gameModel.getCurrentPlayer().getVehicle().setPower(0.0f);
                return true;
            }

            // create a fire-thread which handles firing
            fireThread = new FireThread(view, gameModel);
            return true;
        }

        // when finger enters button-area
        else if (event.toString().equals("enter")) {
            // start/continue power-fluctuation
            powerThread.initiateFluctuation(gameModel.getCurrentPlayer().getVehicle());
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
