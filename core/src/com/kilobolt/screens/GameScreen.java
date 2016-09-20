package com.kilobolt.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.kilobolt.gameworld.GameRenderer;
import com.kilobolt.gameworld.GameWorld;
import com.kilobolt.zbhelpers.InputHandler;

/**
 * Created by rayblandford on 8/27/16.
 */
public class GameScreen implements Screen {
    private GameWorld world;
    private GameRenderer renderer;
    private float runTime = 0;


    public GameScreen () {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        int midPointY = (int) (gameHeight / 2);

        world = new GameWorld(midPointY);
        renderer = new GameRenderer(world, (int) gameHeight, midPointY);

        Gdx.input.setInputProcessor(new InputHandler(world));
    }

    @Override
    public void show() {
        Gdx.app.log("ZBGame", "show called");
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("ZBGame", "resizing");
    }

    @Override
    public void pause() {
        Gdx.app.log("ZBGame", "paused");
    }

    @Override
    public void resume() {
        Gdx.app.log("ZBGame", "resumed");
    }

    @Override
    public void hide() {
        Gdx.app.log("ZBGame", "hiding");
    }

    @Override
    public void dispose() {
        Gdx.app.log("ZBGame", "disposed");
    }
}
