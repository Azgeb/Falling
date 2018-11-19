package at.my.game.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import at.my.game.gameover.*;


/**
 * Created by Joseph on 19.12.2016.
 */

public class game extends Game implements Screen {

    OrthographicCamera camera;
    TiledMap objectLayer64, collisionLayer64;
    OrthogonalTiledMapRenderer renderer, renderer2;
    at.my.game.game.player player;
    Viewport viewport;
    SpriteBatch sp;
    at.my.game.game.hud hud;
    Texture playerTex;
    at.my.game.main main;

    int playerID;
    InputMultiplexer inputs;

    public game(at.my.game.main main, Texture playerTex, int playerID) {

        this.main = main;
        this.playerTex = playerTex;
        this.playerID = playerID;
    }

    @Override
    public void show() {
        TmxMapLoader.Parameters params = new TmxMapLoader.Parameters();
        params.textureMinFilter = Texture.TextureFilter.Linear;
        params.textureMagFilter = Texture.TextureFilter.Nearest;

        objectLayer64 = main.assets.manager.get("maps/objectlayer64.tmx");
        collisionLayer64 = main.assets.manager.get("maps/collisionlayer64.tmx");

        renderer = new OrthogonalTiledMapRenderer(collisionLayer64);
        renderer2 = new OrthogonalTiledMapRenderer(objectLayer64);
        camera = new OrthographicCamera(960, 1600);
        viewport = new FitViewport(960 * 0.5f, 1600 * 0.5f, camera);

        player = new player(new Sprite(playerTex), (TiledMapTileLayer) collisionLayer64.getLayers().get(3), (TiledMapTileLayer) objectLayer64.getLayers().get(2), camera, playerID);
        sp = new SpriteBatch();
        hud = new hud(sp);


        inputs = new InputMultiplexer();
        inputs.addProcessor(player);
        inputs.addProcessor(hud.stage);

        Gdx.input.setInputProcessor(inputs);

        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    @Override
    public synchronized void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (player.gameover) {
            main.setScreen(new mainGameover(main));
        }

        if (hud.restart_current) {
            main.setScreen(new at.my.game.menue(main));
        }

        camera.position.set(new Vector3(player.getX() + player.getWidth() / 2, player.getY(), 0));
        camera.update();

        renderer2.setView(camera);
        renderer2.render();

        renderer.getBatch().begin();
        player.render(hud.special_apply, hud.pause_current, renderer.getBatch());
        renderer.getBatch().end();

        renderer.setView(camera);
        renderer.render();

        sp.setProjectionMatrix(camera.combined);

        hud.render();

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
