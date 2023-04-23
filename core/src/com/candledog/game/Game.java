package com.candledog.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class Game extends ApplicationAdapter {
	private SpriteBatch batch;
	private Player player;
	private OrthographicCamera camera;
	ShapeRenderer shapeRenderer;

	@Override
	public void create () {
		batch = new SpriteBatch();
		player = new Player(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 5f);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render () {
		// Move the player based on keyboard input
		float deltaX = 0;
		float deltaY = 0;
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			deltaX = -1;
		} else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			deltaX = 1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			deltaY = 1;
		} else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			deltaY = -1;
		}
		player.move(deltaX, deltaY);

		// Move the camera to follow the player
		camera.position.set(player.getX(), player.getY(), 0);
		camera.update();

		// Clear the screen and draw the checkered background
		ScreenUtils.clear(0, 0, 0, 1);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		Texture blankTexture = new Texture(Gdx.files.internal("badlogic.jpg"));
		TextureRegion textureRegion = new TextureRegion(blankTexture, 0, 0, 1, 1);

		for (int x = -10; x < 10; x++) {
			for (int y = -10; y < 10; y++) {
				if ((x + y) % 2 == 0) {
					batch.setColor(0.5f, 0.5f, 0.5f, 1f);
				} else {
					batch.setColor(1f, 0f, 0f, 1f);
				}
				batch.draw(textureRegion, x * 32, y * 32, 32, 32);
			}
		}

		// Draw the player as a white circle
		batch.end();
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(1, 1, 1, 1);
		shapeRenderer.circle(player.getX(), player.getY(), 16);
		shapeRenderer.end();
	}


	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
	}
}
