package com.candledog.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * This is the main class for the game. It is responsible for creating the
 * player, camera, tile manager, and for rendering the game.
 *
 * @author Gathrean Dela Cruz
 * @version 2023-04-23
 */
public class Game extends ApplicationAdapter {
	private SpriteBatch batch;
	private Player player;
	private OrthographicCamera camera;
	ShapeRenderer shapeRenderer;
	private TileManager tileManager;
	private KeyHandler keyHandler;
	float deltaX;
	float deltaY;

	@Override
	public void create () {
		batch = new SpriteBatch();
		player = new Player(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 2f);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);

		shapeRenderer = new ShapeRenderer();
		tileManager = new TileManager("data/maps/map2.txt");

		keyHandler = new KeyHandler();
		Gdx.input.setInputProcessor(keyHandler);
	}

	@Override
	public void render () {
		player.move(keyHandler.getDeltaX(), keyHandler.getDeltaY());

		// Move the camera to follow the player
		camera.position.set(player.getX(), player.getY(), 0);
		camera.update();

		// Prevents the Map out bounds to be rendered
		ScreenUtils.clear(0, 0, 0, 1);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		// Draw the tiles
		for (int x = 0; x < tileManager.getWidth(); x++) {
			for (int y = 0; y < tileManager.getHeight(); y++) {
				TextureRegion textureRegion = tileManager.getTile(x, y);
				batch.draw(textureRegion, x * TileManager.TILE_SIZE, y * TileManager.TILE_SIZE, TileManager.TILE_SIZE, TileManager.TILE_SIZE);
			}
		}

    // Draw the player as a white square
		batch.end();
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(1, 1, 1, 1);
		shapeRenderer.rect(player.getX(), player.getY(), 16, 16);
		shapeRenderer.end();

	}

	// This method is called when the game is closed to clean up resources
	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
	}
}
