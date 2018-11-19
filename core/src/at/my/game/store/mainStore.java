package at.my.game.store;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


/**
 * Created by Joseph on 19.02.2017.
 */


public class mainStore extends Game implements Screen {

    OrthographicCamera camera;
    Viewport viewport;
    Store store;
    at.my.game.main main;

    public mainStore(at.my.game.main main) {
        this.main = main;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(960, 1600);
        viewport = new FitViewport(960 / 3f, 1600 / 3f, camera);

        store = new Store(main, viewport);
    }

    @Override
    public synchronized void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        store.render();

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
