package at.my.game.store;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;


/**
 * Created by Joseph on 19.02.2017.
 */

public class Store {

    Stage stage;
    Table table;
    ImageButton back, player0, player1;
    Skin skin;
    at.my.game.main main;


    Preferences pref = Gdx.app.getPreferences("PreferencesDown");


    public Store(at.my.game.main main, Viewport viewport) {

        this.main = main;

        skin = new Skin();
        skin.add("back", new Texture("back.png"));
        skin.add("player0", new Texture("player0.png"));
        skin.add("player1", new Texture("player1.png"));

        back = new ImageButton(skin.getDrawable("back"));
        player0 = new ImageButton(skin.getDrawable("player0"));
        player1 = new ImageButton(skin.getDrawable("player1"));

        table = new Table();
        table.setWidth(viewport.getWorldWidth());
        table.setHeight(viewport.getWorldHeight());
        table.top().left();
        table.add(back).padTop(5).padLeft(5);
        table.row();
        table.add(player0).padTop(10).padLeft(50);
        table.add(player1).padTop(10).padLeft(50);
        table.debugTable();

        stage = new Stage(viewport);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    public void render (){

        stage.draw();

        if(back.isPressed()){
           main.setScreen(new at.my.game.menue(main));
        }
        if(player0.isPressed()){
            pref.putInteger("player",0 );
        } else
        if(player1.isPressed()){
            pref.putInteger("player",1 );
        }

        pref.flush();
    }
}
