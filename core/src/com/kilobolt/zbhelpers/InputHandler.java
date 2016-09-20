package com.kilobolt.zbhelpers;

import com.badlogic.gdx.InputProcessor;
import com.kilobolt.com.kilobolt.gameobjects.Bird;
import com.kilobolt.gameworld.GameWorld;

/**
 * Created by rayblandford on 8/28/16.
 */
public class InputHandler implements InputProcessor {
    private Bird myBird;
    private GameWorld myWorld;

    public InputHandler(GameWorld myWorld) {
        this.myWorld = myWorld;
        myBird = myWorld.getBird();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (myWorld.isReady()) {
            myWorld.start();
        }

        myBird.onClick();

        if (myWorld.isGameOver()) {
            myWorld.restart();
        }

        return true;
    }

    /*
     * Methods below have not been implemented yet.
     */

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
