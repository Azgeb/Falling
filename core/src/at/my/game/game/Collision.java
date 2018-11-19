package at.my.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Joseph on 14.02.2017.
 */

public class Collision extends Sprite {

    TiledMapTileLayer collisionLayer, objectLayer;
    public TiledMapTileLayer.Cell currentCell;
    Preferences pref = Gdx.app.getPreferences("PreferencesDown");
    int numberOfChecks = 15;
    public boolean gameOver;
    public boolean gCollision;

    public Collision(Sprite player, TiledMapTileLayer objectLayer, TiledMapTileLayer collisionLayer) {
        super(player);
        this.objectLayer = objectLayer;
        this.collisionLayer = collisionLayer;
        currentCell =  collisionLayer.getCell((int) (getX() / collisionLayer.getTileWidth()), (int) (getY() / collisionLayer.getTileHeight()));
        gameOver = false;
    }

    public void update(Vector2 playerPosition){
        setPosition(playerPosition.x, playerPosition.y);
    }

    public boolean colissionLeft() {
        for (int step = 1; step < getWidth()-1; step += collisionLayer.getWidth() / numberOfChecks) {
            if (isCellBlocked(getX(), getY() + step, "x", collisionLayer)) {
                return true;
            } else if (isCellBlocked(getX() + step, getY() - collisionLayer.getTileHeight() / getHeight(), "g", collisionLayer)) {
                return true;
            }
        }
        return false;
    }

    public boolean colissionRigth() {
        for (int step = 1; step < getWidth()-1; step += collisionLayer.getWidth() / numberOfChecks) {
            if (isCellBlocked(getX() + getWidth(), getY() + step, "x", collisionLayer)) {
                return true;
            } else if (isCellBlocked(getX() + step, getY() - collisionLayer.getTileHeight() / getHeight(), "g", collisionLayer)) {
                return true;
            }
        }
        return false;
    }

    public boolean colissionBottem() {
        for (int step = 3; step < getWidth()-1 ; step += collisionLayer.getWidth() / numberOfChecks) {
            if (isCellBlocked(getX() + step, getY() - collisionLayer.getTileHeight() / getHeight(), "x", collisionLayer)) {
                gameOver = true;
                System.out.println("go");
                return true;
            } else  if (isCellBlocked(getX() + step, getY() - collisionLayer.getTileHeight() / getHeight(), "g", collisionLayer)){
                return true;
            }
        }
        return false;
    }

    public boolean colissionTop() {
        for (int step = 1; step < getWidth()-1; step += collisionLayer.getWidth() / numberOfChecks) {
            if (isCellBlocked(getX() + step, getY() + getHeight(), "x", collisionLayer)) {
                return true;
            }
        }
        return false;
    }

    public boolean isJump() {
        for (int step = 0; step < getWidth(); step += collisionLayer.getWidth() / numberOfChecks) {
            if (isCellBlocked(getX() + step, getY() - collisionLayer.getTileHeight() / getHeight(), "f", collisionLayer)) {
                return true;
            }
        }
        return false;
    }

    public boolean isMoneyBottem() {
        for (int step = 0; step < getWidth(); step += objectLayer.getWidth() / numberOfChecks) {
            if (isCellBlocked(getX() + step, getY() - objectLayer.getTileHeight() / getHeight(), "m", objectLayer)) {
                pref.putInteger("money", pref.getInteger("money")+1);
                pref.flush();
                currentCell.setTile(null);
                return true;
            }
        }
        return false;
    }

    public boolean isMoneyTop() {
        for (int step = 0; step < getWidth(); step += objectLayer.getWidth() / numberOfChecks) {
            if (isCellBlocked(getX() + step, getY() + getHeight(), "m", objectLayer)) {
                pref.putInteger("money", pref.getInteger("money")+1);
                pref.flush();
                currentCell.setTile(null);
                return true;
            }
        }
        return false;
    }

    public boolean isMoneyLeft() {
        for (int step = 0; step < getWidth(); step += objectLayer.getWidth() / numberOfChecks) {
            if (isCellBlocked(getX(), getY() + step, "m", objectLayer)) {
                pref.putInteger("money", pref.getInteger("money")+1);
                pref.flush();
                currentCell.setTile(null);
                return true;
            }
        }
        return false;
    }

    public boolean isMoneyRigth() {
        for (int step = 0; step < getWidth(); step += objectLayer.getWidth() / numberOfChecks) {

            if (isCellBlocked(getX() + getWidth(), getY() + step, "m",objectLayer)){
                pref.putInteger("money", pref.getInteger("money")+1);
                pref.flush();
                currentCell.setTile(null);
                return true;
            }
        }
        return false;
    }

    public boolean isgCollision() {
        for (int step = 1; step < getWidth(); step += collisionLayer.getWidth() / numberOfChecks) {
            if (isCellBlocked(getX() + step, getY() - collisionLayer.getTileHeight() / getHeight(), "g", collisionLayer)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCellBlocked(float x, float y, String actionKey, TiledMapTileLayer layer) {

        TiledMapTileLayer.Cell cell = layer.getCell((int) (x / layer.getTileWidth()), (int) (y / layer.getTileHeight()));

        currentCell = cell;

        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(actionKey);
    }


}
