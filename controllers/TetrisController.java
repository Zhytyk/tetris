package tetris.controllers;


import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import tetris.game.TetrisContext;
import tetris.game.TetrisControlMove;
import tetris.game.TetrisControlRotate;
import tetris.game.TetrisField;
import tetris.game.interfaces.GameContext;
import tetris.game.interfaces.GameControlMove;
import tetris.game.interfaces.GameControlRotate;
import tetris.game.interfaces.GameField;
import tetris.utils.DrawerImpl;
import tetris.utils.interfaces.Drawer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Facade design pattern
 */
public class TetrisController {
    @FXML
    private Canvas tetrisCanvas;

    private ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    private GameControlMove controlMove;

    private GameControlRotate controlRotate;

    @FXML
    public void initialize() {
        DrawerImpl.init(tetrisCanvas.getGraphicsContext2D());

        controlMove = TetrisControlMove.getInstance();
        controlRotate = TetrisControlRotate.getInstance();

        scheduledExecutorService.scheduleAtFixedRate((Runnable) TetrisContext.getInstance(), 0, 100, TimeUnit.MILLISECONDS);
        scheduledExecutorService.scheduleAtFixedRate((Runnable) controlMove, 0, 70, TimeUnit.MILLISECONDS);
    }

    @FXML
    public void onKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case A:
                controlMove.goLeft();
                break;
            case D:
                controlMove.goRight();
                break;
            case W:
                controlRotate.goUp();
                break;
            case S:
                controlRotate.goDown();
        }
    }

    @FXML
    public void onKeyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case A:
                controlMove.stopLeft();
                break;
            case D:
                controlMove.stopRight();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        scheduledExecutorService.shutdown();
    }
}
