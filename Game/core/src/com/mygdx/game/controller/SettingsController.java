package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.mygdx.game.view.GameView;
import com.mygdx.game.view.SettingsView;
import com.mygdx.game.view.TeamView;
import com.mygdx.game.model.Game;
import com.mygdx.game.model.GameSettings;

/**
 * Created by Jonathan on 10.03.2015.
 */
public class SettingsController implements EventListener {

    // the view the controller listens to
    private SettingsView view;

    public GameSettings settings;
    private TeamView teamView;

    public SettingsController(SettingsView view){
        this.view = view;

    }

    private void next() {

        settings = new GameSettings();
        settings.setNumberOfRounds(view.numberOfRounds);

        if (view.numberOfTurns.equals("Unlimited")) {
            settings.setNumberOfTurns(-1);
        }
        else {
            settings.setNumberOfTurns(Integer.parseInt(view.numberOfTurns));
        }

        settings.setDifficulty(view.difficulty);

        view.game.setScreen(new TeamView(view.game, this));
    }

    private void newPlayer() {
        teamView.teamsChosen.add(teamView.currentTeam);
        teamView.setNextTeamSprite();
        teamView.numberOfPlayers += 1;
        settings.setNofPlayers(teamView.numberOfPlayers);
    }

    private void newGame() {

        settings.setNofPlayers(teamView.numberOfPlayers);
        teamView.teamsChosen.add(teamView.currentTeam);
        teamView.setNextTeamSprite();
        settings.setTeams(teamView.teamsChosen);
        view.game.setScreen(new GameView(view.game, new Game(settings)));

    }

    public boolean handle (Event event){

        if (event.toString().equals("touchDown")){
            if (event.getListenerActor().getName().equals("Next")) {
                next();
                return true;
            }
            else if(event.getListenerActor().getName().equals("StartGame")) {
                newGame();
                return true;
            }
            else if(event.getListenerActor().getName().equals("NewPlayer")) {
                newPlayer();
                return true;
            }
        }

        return false;
    }

    public void setTeamView (TeamView teamView) {
        this.teamView = teamView;
    }

}
