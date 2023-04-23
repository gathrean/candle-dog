package com.candledog.game;

public class Player {
  private float x;
  private float y;
  private float speed;

  public Player(float x, float y, float speed) {
    this.x = x;
    this.y = y;
    this.speed = speed;
  }

  public void move(float deltaX, float deltaY) {
    x += deltaX * speed;
    y += deltaY * speed;
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }
}
