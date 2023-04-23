package com.candledog.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * The player class represents the player in the game.
 * It contains the player's position and size,
 * and methods to update and render the player.
 *
 * @author Gathrean Dela Cruz
 * @version 1.0
 */
public class Player {
  private Circle circle;
  private float speed = 100f; // Player speed in pixels per second

  public Player(float x, float y, float radius) {
    circle = new Circle(x, y, radius);
  }

  public void update(float delta) {
    // Calculate movement based on input
    float dx = 0, dy = 0;
    if (Gdx.input.isKeyPressed(Input.Keys.A)) {
      dx = -1;
    }
    if (Gdx.input.isKeyPressed(Input.Keys.D)) {
      dx = 1;
    }
    if (Gdx.input.isKeyPressed(Input.Keys.W)) {
      dy = 1;
    }
    if (Gdx.input.isKeyPressed(Input.Keys.S)) {
      dy = -1;
    }

    // Normalize movement vector
    Vector2 movement = new Vector2(dx, dy).nor();

    // Apply movement to player position
    float distance = speed * delta;
    circle.x += movement.x * distance;
    circle.y += movement.y * distance;
  }

  public void render(ShapeRenderer renderer) {
    // Begin rendering with the ShapeRenderer
    renderer.begin(ShapeRenderer.ShapeType.Filled);

    // Set the color and draw a white circle at the player's position
    renderer.setColor(Color.WHITE);
    renderer.circle(circle.x, circle.y, circle.radius);

    // End rendering with the ShapeRenderer
    renderer.end();
  }

  public Circle getCircle() {
    return circle;
  }
}
