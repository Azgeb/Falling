package at.my.game.gameover;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import at.my.game.store.Store;


/**
 * Created by Joseph on 19.02.2017.
 */


public class mainGameover extends Game implements Screen {

    OrthographicCamera camera;
    Viewport viewport;
    gameover gameover;
    at.my.game.main main;

    public mainGameover(at.my.game.main main) {
        this.main = main;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(960, 1600);
        viewport = new FitViewport(960, 1600, camera);

        gameover = new gameover(main, viewport);
    }

    @Override
    public synchronized void render(float delta) {
        Gdx.gl.glClearColor(0.8f, 05f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameover.render();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void create() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
