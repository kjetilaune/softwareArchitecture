package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.mygdx.game.gui.AbstractView;
import com.mygdx.game.gui.GameView;
import com.mygdx.game.gui.SettingsView;
import com.mygdx.game.gui.TeamView;
import com.mygdx.game.model.Environment;
import com.mygdx.game.model.Game;
import com.mygdx.game.model.GameSettings;
import com.mygdx.game.model.Player;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

/**
 * Created by Jonathan on 10.03.2015.
 */
public class SettingsController extends AbstractController implements EventListener {

    // the view the controller listens to
    private SettingsView view;

    public GameSettings settings;
    private TeamView teamView;

    public SettingsController(AbstractView view){
        super(view);
        this.view = (SettingsView)view;

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    private void next() {

        settings = new GameSettings();
        //settings.setNofPlayers(view.numberOfPlayers);
        settings.setNumberOfRounds(view.numberOfRounds);

        if (view.numberOfTurns.equals("Unlimited")) {
            settings.setNumberOfTurns(-1);
        }
        else {
            settings.setNumberOfTurns(Integer.parseInt(view.numberOfTurns));
        }

        settings.setDifficulty(view.difficulty);
        //settings.setTeams(view.teams);

        view.game.setScreen(new TeamView(view.game, this));
    }

    private void newGame() {

        settings.setNofPlayers(teamView.numberOfPlayers);
        teamView.teamsChosen.add(teamView.currentTeam);
        teamView.currentPlayerNumber ++;
        System.out.println("Current player " + teamView.currentPlayerNumber);

        if (teamView.currentPlayerNumber == teamView.numberOfPlayers) {
            settings.setTeams(teamView.teamsChosen);
            view.game.setScreen(new GameView(view.game, new Game(settings)));
        }

    }

    public boolean handle (Event event){

        if (event.toString().equals("touchDown")){
            if (event.getListenerActor().getName().equals("Next")) {
                next();
                return true;
            }
            else if(event.getListenerActor().getName().equals("NewGame")) {
                newGame();
                return true;
            }
        }

        return false;
    }

    public void setTeamView (TeamView teamView) {
        this.teamView = teamView;
    }

}
