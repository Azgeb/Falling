package at.my.game.gameover;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.utils.viewport.Viewport;


/**
 * Created by Joseph on 19.02.2017.
 */

public class gameover {

    Stage stage;
    Table table;
    ImageButton back, player0, player1;
    Skin skin;
    at.my.game.main main;
    SpriteBatch batch;


    Preferences pref = Gdx.app.getPreferences("PreferencesDown");

    public gameover(at.my.game.main main, Viewport viewport) {

        this.main = main;

        batch = new SpriteBatch();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        skin = new Skin();
        skin.add("restart", new Texture("restart.png"));
        skin.add("player0", new Texture("player0.png"));
        skin.add("player1", new Texture("player1.png"));

        back = new ImageButton(skin.getDrawable("restart"));
        player0 = new ImageButton(skin.getDrawable("player0"));
        player1 = new ImageButton(skin.getDrawable("player1"));

        table = new Table();
        table.setWidth(viewport.getWorldWidth());
        table.setHeight(viewport.getWorldHeight());
        table.top().left();
        table.add(back).padTop(950).padLeft(480);
        table.row();
        table.debugTable();

        stage = new Stage(viewport);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    public void render() {

        stage.draw();

        batch.begin();
        batch.draw(new Texture("gameover.png"), -480, 500);
        batch.end();

        if (back.isPressed()) {
            main.setScreen(new at.my.game.menue(main));
        }

        pref.flush();
    }
}
