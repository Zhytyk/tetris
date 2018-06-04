package tetris.game;

import tetris.game.interfaces.GameControlMove;
import tetris.game.interfaces.GameField;
import tetris.models.shapes.abstracts.Shape;
import tetris.utils.DrawerImpl;
import tetris.utils.interfaces.Drawer;

import java.util.Objects;

public class TetrisControlMove implements GameControlMove, Runnable {
    private boolean left = false;
    private boolean right = false;

    private Shape fallingShape;

    private Drawer drawer;
    private GameField field;

    private static GameControlMove instance;

    public static GameControlMove getInstance() {
        if (Objects.isNull(instance)) {
            instance = new TetrisControlMove();
        }

        return instance;
    }

    private TetrisControlMove() {
        this.drawer = DrawerImpl.getInstance();
        this.field = TetrisField.getInstance();
    }

    @Override
    public void run() {
        if (Objects.isNull(fallingShape) || isNotMoving()) {
            return;
        }

        if (left && !field.isHorizontalLeftEdge(fallingShape)) {
            field.cleanCells(fallingShape);
            drawer.clean(fallingShape);

            drawer.drawLeft(fallingShape);
            field.occupyCells(fallingShape);
        } else if (right && !field.isHorizontalRightEdge(fallingShape)) {
            field.cleanCells(fallingShape);
            drawer.clean(fallingShape);

            drawer.drawRight(fallingShape);
            field.occupyCells(fallingShape);
        }
    }

    @Override
    public void setFallingShape(Shape shape) {
        fallingShape = shape;
    }

    @Override
    public void goRight() {
        if (!right) {
            right = true;
        }
    }

    @Override
    public void goLeft() {
        if (!left) {
            left = true;
        }
    }

    @Override
    public void stopLeft() {
        left = false;
    }

    @Override
    public void stopRight() {
        right = false;
    }

    private boolean isNotMoving() {
        return !left && !right;
    }
}
