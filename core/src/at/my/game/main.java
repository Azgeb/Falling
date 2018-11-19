package at.my.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class main extends Game {

    public assets assets;

    public void create() {

        assets = new assets();
        assets.load();
        System.out.println(assets.manager.getProgress());
        assets.manager.finishLoading();
        setScreen(new menue(this));
    }


}
