package at.my.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import at.my.game.game.game;
import at.my.game.store.mainStore;


/**
 * Created by Joseph on 23.12.2016.
 */

public class menue extends  Game implements Screen{

    at.my.game.main main;
    Button startbtn, btnchange;
    Button.ButtonStyle buttonStyle;
    Skin skin;
    Stage stage;
    Preferences pref = Gdx.app.getPreferences("PreferencesDown");
    Texture playertex;


    public menue(main main) {

        this.main = main;

        skin = new Skin();
        skin.add("btn", main.assets.manager.get("btn.png"));

        buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = skin.getDrawable("btn");

        startbtn = new Button(buttonStyle);
        startbtn.setPosition(0, 800 - startbtn.getHeight()/2);

        btnchange = new Button(buttonStyle);
        btnchange.setPosition(0, 0);

        stage = new Stage(new FitViewport(960, 1600));
        stage.addActor(startbtn);
        stage.addActor(btnchange);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.input.setInputProcessor(stage);
        stage.draw();

        if (startbtn.isPressed() && Gdx.input.justTouched()){
            Gdx.input.setInputProcessor(null);
            playertex = new Texture("player" + pref.getInteger("player") +".png");
            main.setScreen(new game(main, playertex, pref.getInteger("player")));
        }
        if (btnchange.isPressed() && Gdx.input.justTouched()){
            main.setScreen(new mainStore(main));
        }
    }

    @Override
    public void hide() {

    }

    @Override
    public void create() {

    }

    @Override
    public void dispose() {

    }
}
