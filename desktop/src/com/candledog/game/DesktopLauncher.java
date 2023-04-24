package com.candledog.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.candledog.game.Game;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		config.setWindowedMode(800, 800);
		config.setIdleFPS(0);
		config.setResizable(false);
		config.setWindowSizeLimits(800, 800, 3000, 3000);

		config.setForegroundFPS(60);
		config.setTitle("Candle Dog");


		new Lwjgl3Application(new Game(), config);
	}
}
