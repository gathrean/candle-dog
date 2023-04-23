package com.candledog.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileManager {
  public static final int TILE_SIZE = 64;

  private int[][] map;
  private Texture[] textures;

  public TileManager(String mapPath) {
    // Read the map file
    String mapString = Gdx.files.internal(mapPath).readString();

    // Split the map string into rows
    String[] rows = mapString.split("\n");

    // Create a 2D array with the same dimensions as the map
    map = new int[rows.length][rows[0].split(" ").length];

    // Loop through the rows and split each row by spaces
    for (int y = 0; y < rows.length; y++) {
      String[] row = rows[y].split(" ");
      for (int x = 0; x < row.length; x++) {
        // Convert the tile type to an integer and set it in the 2D array
        map[y][x] = Integer.parseInt(row[x].trim());
      }
    }

    // Load the textures for each tile type
    textures = new Texture[6];
    textures[0] = new Texture(Gdx.files.internal("data/tiles/grass.png"));
    textures[1] = new Texture(Gdx.files.internal("data/tiles/wall.png"));
    textures[2] = new Texture(Gdx.files.internal("data/tiles/water.png"));
    textures[3] = new Texture(Gdx.files.internal("data/tiles/earth.png"));
    textures[4] = new Texture(Gdx.files.internal("data/tiles/tree.png"));
    textures[5] = new Texture(Gdx.files.internal("data/tiles/sand.png"));
  }

  public int getWidth() {
    return map[0].length;
  }

  public int getHeight() {
    return map.length;
  }

  public TextureRegion getTile(int x, int y) {
    int tileIndex = map[y][x];
    return new TextureRegion(textures[tileIndex]);
  }

  public void render(SpriteBatch batch) {
    // Loop through the 2D array of tile types and draw the corresponding texture
    for (int y = 0; y < map.length; y++) {
      for (int x = 0; x < map[y].length; x++) {
        Texture texture = textures[map[y][x]];
        batch.draw(texture, x * TILE_SIZE, y * TILE_SIZE);
      }
    }
  }
}
