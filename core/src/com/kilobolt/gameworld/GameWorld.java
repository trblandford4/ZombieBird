package com.kilobolt.gameworld;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.kilobolt.com.kilobolt.gameobjects.Bird;
import com.kilobolt.com.kilobolt.gameobjects.ScrollHandler;
import com.kilobolt.zbhelpers.AssetLoader;

/**
 * Created by rayblandford on 8/27/16.
 */
public class GameWorld {
    private Bird bird;
    private ScrollHandler scroller;
    private Rectangle ground;
    private int score = 0;

    private int midpointY;

    private GameState currentState;

    public enum GameState {
        READY, RUNNING, GAMEOVER
    }

    public GameWorld(int midPointY) {
        currentState = GameState.READY;
        this.midpointY = midPointY;
        bird = new Bird(33, midPointY - 5, 17, 12);
        scroller = new ScrollHandler(this, midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 136, 11);
    }

    public void update(float delta) {
        switch (currentState) {
            case READY:
                updateReady(delta);
                break;
            case RUNNING:
            default:
                updateRunning(delta);
                break;
        }
    }

    public void updateReady(float delta) {
        // Do nothing for now
    }

    public void updateRunning(float delta) {
        if (delta > .15f) {
            delta = .15f;
        }

        bird.update(delta);
        scroller.update(delta);

        if (scroller.collides(bird) && bird.isAlive()) {
            //Clean up on Game over
            scroller.stop();
            bird.die();
            AssetLoader.dead.play();
        }

        if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
            scroller.stop();
            bird.die();
            bird.decelerate();
            currentState = GameState.GAMEOVER;
        }
    }

    public void addScore(int increment) {
        score += increment;
    }

    public Bird getBird() {
        return bird;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }

    public int getScore() {
        return score;
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        bird.onRestart(midpointY - 5);
        scroller.onRestart();
        currentState = GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }
}
