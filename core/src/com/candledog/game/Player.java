package com.candledog.game;

public class Player {
  private float x;
  private float y;
  private final float speed;
  private final float width = 16;
  private final float height = 16;

  public Player(float x, float y, float speed) {
    this.x = x;
    this.y = y;
    this.speed = speed;
  }

  public void move(float deltaX, float deltaY) {
    // Round the player's position to the nearest pixel
    float newX = Math.round(x + deltaX * speed);
    float newY = Math.round(y + deltaY * speed);

    // Only update the player's position if it has changed
    if (newX != x || newY != y) {
      x = newX;
      y = newY;
    }
  }

  public float getX() {
    return x + width / 2;
  }

  public float getY() {
    return y + height / 2;
  }

  public float getWidth() {
    return width;
  }

  public float getHeight() {
    return height;
  }
}
