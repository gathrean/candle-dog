package com.candledog.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class KeyHandler extends InputAdapter {
  private boolean[] keys = new boolean[256];
  private float deltaX;
  private float deltaY;

  public KeyHandler() {
    deltaX = 0;
    deltaY = 0;
  }

  @Override
  public boolean keyDown(int keycode) {
    keys[keycode] = true;
    updateDelta();
    return true;
  }

  @Override
  public boolean keyUp(int keycode) {
    keys[keycode] = false;
    updateDelta();
    return true;
  }

  private void updateDelta() {
    deltaX = 0;
    deltaY = 0;

    if (keys[Input.Keys.LEFT] || keys[Input.Keys.A]) {
      deltaX -= 1;
    }
    if (keys[Input.Keys.RIGHT] || keys[Input.Keys.D]) {
      deltaX += 1;
    }
    if (keys[Input.Keys.UP] || keys[Input.Keys.W]) {
      deltaY += 1;
    }
    if (keys[Input.Keys.DOWN] || keys[Input.Keys.S]) {
      deltaY -= 1;
    }
  }

  public float getDeltaX() {
    return deltaX;
  }

  public float getDeltaY() {
    return deltaY;
  }
}
