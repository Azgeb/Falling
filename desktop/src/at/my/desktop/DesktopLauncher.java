package at.my.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 480;
		config.height = 800;
		config.resizable = true;
		new LwjglApplication(new at.my.game.main(), config);
	}
}
