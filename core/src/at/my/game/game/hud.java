package at.my.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class hud implements Screen {

    public Stage stage;
    private Viewport viewport;
    Skin skin;

    ImageButton jumpbtn, backbtn, pausebtn;

    Image background = new Image(new Texture(Gdx.files.internal("background.png")));
    TextButton leftbtn;
    TextButton.TextButtonStyle style;
    Vector3 touch;

    SpriteBatch sb;
    ShapeRenderer sr;

    int money;
    int applys_left = 5;
    BitmapFont font;

    OrthographicCamera camera;


    Preferences pref = Gdx.app.getPreferences("PreferencesDown");

    Table table, tableBackgrund, tablefont;
    public boolean canjump = true;
    public boolean special_apply = false;
    public boolean pause_current = false;
    public boolean restart_current = false;
    boolean pausehelp = false;

    public hud(SpriteBatch sb) {

        this.sb = sb;
        sr = new ShapeRenderer();

        font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));

        skin = new Skin();
        skin.add("font", font);
        skin.add("jump", new Texture("jump.png"));
        skin.add("back", new Texture("restart.png"));
        skin.add("pause", new Texture("pause.png"));
        skin.add("left", new Texture("left.png"));

        style = new TextButton.TextButtonStyle();
        style.up = skin.getDrawable("left");
        style.font = skin.getFont("font");
        style.fontColor = Color.RED;

        jumpbtn = new ImageButton(skin.getDrawable("jump"));
        backbtn = new ImageButton(skin.getDrawable("back"));
        pausebtn = new ImageButton(skin.getDrawable("pause"));
        leftbtn = new TextButton("" + applys_left, style);

        table = new Table();
        tableBackgrund = new Table(skin);
        tablefont = new Table(skin);

        camera = new OrthographicCamera(960.0f, 1600.0f);

        viewport = new FitViewport(980, 1600, camera);
        stage = new Stage(viewport, sb);

        tableBackgrund.top().left();
        tableBackgrund.setFillParent(true);
        tableBackgrund.add(background).top().left();

        table.top().left();
        table.setFillParent(true);
        table.add(jumpbtn).padTop(2).expandX();
        table.add(pausebtn).top().left().padTop(10).padLeft(10);
        table.add(backbtn).top().left().padTop(10).padLeft(150);

        stage.addActor(tableBackgrund);
        stage.addActor(table);


    }

    public void render() {

        money = pref.getInteger("money");

        leftbtn.setText("" + applys_left);

        tablefont.top().left();
        tablefont.setFillParent(true);
        tablefont.add("" + money, "font", Color.WHITE).padLeft(10).padTop(10);
        tablefont.add(leftbtn).padLeft(210).padTop(-20);

        stage.addActor(tablefont);

        touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        viewport.getCamera().unproject(touch);
        stage.draw();

        tablefont.clear();


        canjump = true;
        special_apply = false;


        if (canjump) {
            if (jumpbtn.isPressed() && applys_left > 0 && Gdx.input.justTouched()) {
                applys_left = applys_left -1;
                canjump = false;
                special_apply = true;
            }
        }
        if (Gdx.input.justTouched() && pausebtn.isPressed()) {
            if (!pause_current) {
                pause_current = true;
            } else if (pause_current) {
                pause_current = false;
            }
        }
        if (backbtn.isPressed()) {
            restart_current = true;
            pausehelp = true;
        }

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {


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
