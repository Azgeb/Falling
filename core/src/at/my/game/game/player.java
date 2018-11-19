package at.my.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Joseph on 19.12.2016.
 */

public class player extends Sprite implements InputProcessor {

    Vector2 velocity = new Vector2();
    float speed = 192;
    float gr_normal = 236;
    float gr_doge = 1;
    float gravity = gr_normal;
    float delta;
    float dogeTime;

    int jump_strength = 288;
    int tile = 64;
    public int playerID;

    at.my.game.game.Collision collisionDetection;

    boolean gameover, special_apply, pause_current, stop, doTimer;

    OrthographicCamera camera;


    public player(Sprite player, TiledMapTileLayer collisionLayer, TiledMapTileLayer objectLayer, OrthographicCamera camera, int playerID) {
        super(player);
        this.playerID = playerID;
        collisionDetection = new at.my.game.game.Collision(player, objectLayer, collisionLayer);

        gameover = false;
        stop = false;
        doTimer = false;

        this.camera = camera;
        setPosition((collisionLayer.getTileWidth() * 60) - getWidth() / 2, (collisionLayer.getHeight() * collisionLayer.getTileHeight()) - collisionLayer.getTileHeight() * 18);
    }


    public void render(boolean special_apply, boolean pause_current, Batch batch) {


        this.special_apply = special_apply;
        this.pause_current = pause_current;
        collisionDetection.update(new Vector2(getX(), getY()));

        if (pause_current) {

        } else if (!collisionDetection.gameOver){
                update(Gdx.graphics.getDeltaTime());
        } else if (collisionDetection.gameOver){
            gameover = true;
        }

        super.draw(batch);
        getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

    }

    public void update(float delta) {

        this.delta = delta;

        if (!collisionDetection.isgCollision()) {
            velocity.y = velocity.y - gravity * 0.03f;
        }

        if (velocity.y < -gravity && !stop) {
            velocity.y = -gravity;
        } else if (stop) {
            stop = false;
            doTimer = false;
            gravity = gr_normal;
        }


        float oldX = getX(), oldY = getY();
        boolean collisionX = false, collisionY = false;


        setX(oldX + velocity.x * delta);

        // x collision
        // left
        if (velocity.x < 0) {
            collisionX = collisionDetection.colissionLeft();
            collisionDetection.isMoneyLeft();

        } else
            // rigth
            if (velocity.x > 0) {
                collisionX = collisionDetection.colissionRigth();
                collisionDetection.isMoneyRigth();
            }

        if (!collisionDetection.isgCollision()) {
            if (collisionX) {
                setX((int) oldX);
                velocity.x = 0;
            }
        }

        // Y collision
        if (velocity.y < 0) {
            collisionY = collisionDetection.colissionBottem();
            collisionDetection.isMoneyBottem();

        } else if (velocity.y > 0) {
            collisionY = collisionDetection.colissionTop();
            collisionDetection.isMoneyTop();
        }

        if (collisionDetection.isJump()) {
            velocity.y = 192;
        }

        if (special_apply && playerID == 0) {
            System.out.println("f");
            velocity.y = jump_strength;
        } else if (special_apply && playerID == 1) {
            gravity = gr_doge;
            dogeTime = 1.8f;
            doTimer = true;
        }

        if (doTimer) {
            timer();
        }

        setY(oldY + velocity.y * delta);
        if (collisionY) {
            setY((int) oldY);
            velocity.y = 0;
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
                velocity.x = -128;
                break;
            case Input.Keys.D:
                velocity.x = 128;
                break;
            case Input.Keys.W:
                velocity.y = 128;
                break;
            case Input.Keys.S:
                velocity.y = -128;
                break;
        }
        return false;
    }

    public void timer() {
        if (dogeTime >= 0) {
            dogeTime = dogeTime - delta;
        } else if (dogeTime <= 0) {
            stop = true;
        }
    }

    @Override
    public boolean keyUp(int keycode) {
        velocity.x = 0;
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (screenX <= Gdx.graphics.getWidth() / 2 && screenY > 100) {
            velocity.x = -speed;
        } else if (screenX >= Gdx.graphics.getWidth() / 2 && screenY > 100) {
            velocity.x = speed;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        velocity.x = 0;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
