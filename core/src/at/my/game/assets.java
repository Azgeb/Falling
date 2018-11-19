package at.my.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;


/**
 * Created by Joseph on 28.02.2017.
 */

public class assets {

    public AssetManager manager = new AssetManager();


    public void load(){

        manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        manager.load("maps/objectlayer64.tmx", TiledMap.class);
        manager.load("maps/collisionlayer64.tmx", TiledMap.class);

        manager.setLoader(Texture.class, new TextureLoader(new InternalFileHandleResolver()));
        manager.load("back.png", Texture.class);
        manager.load("player0.png", Texture.class);
        manager.load("player1.png", Texture.class);
        manager.load("btn.png", Texture.class);


    }
}
