
package com.candledog.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * This is the main class for the game. It extends ApplicationAdapter,
 * which is a class provided by LibGDX that implements the ApplicationListener interface.
 *
 * @author Gathrean Dela Cruz
 * @version 1.0
 */
public class Game extends ApplicationAdapter {
  private TiledMap tiledMap;
  private OrthogonalTiledMapRenderer tiledMapRenderer;
  private OrthographicCamera camera;
  private ShapeRenderer shapeRenderer;
  private Player player;

  @Override
  public void create() {
    // Load the tiled map
    tiledMap = new TmxMapLoader().load("Maps/GameBoy.tmx");

    player = new Player(0, 0, 10); // x, y, radius

    // Create a FitViewport with the desired size
    FitViewport viewport = new FitViewport(960 / 2f, 640 / 2f);

    // Create an OrthographicCamera and set its viewport to the FitViewport
    camera = new OrthographicCamera();
    camera.setToOrtho(false, viewport.getWorldWidth(), viewport.getWorldHeight());
    camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
    camera.update();

    // Create a ShapeRenderer object and set its projection matrix to the camera's combined matrix
    shapeRenderer = new ShapeRenderer();
    shapeRenderer.setProjectionMatrix(camera.combined);

    // Create an OrthogonalTiledMapRenderer and pass in the camera
    tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    tiledMapRenderer.setView(camera.combined, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
  }

  public void render() {
    // Update camera position
    camera.position.set(player.getCircle().x, player.getCircle().y, 0);
    camera.update();

    // Clear the screen and render the map
    ScreenUtils.clear(0, 0, 0, 1);
    tiledMapRenderer.setView(camera);
    tiledMapRenderer.render();

    // Render the player using the ShapeRenderer instance created in the create() method
    player.render(shapeRenderer);
    player.update(Gdx.graphics.getDeltaTime());
  }

  @Override
  public void dispose() {
    tiledMap.dispose();
    tiledMapRenderer.dispose();
    shapeRenderer.dispose();
  }
}
