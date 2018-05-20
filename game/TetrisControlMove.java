package tetris.game;

import tetris.game.interfaces.GameControlMove;
import tetris.game.interfaces.GameField;
import tetris.models.shapes.abstracts.Shape;
import tetris.utils.interfaces.Drawer;

import java.util.Objects;

public class TetrisControlMove implements GameControlMove, Runnable {
    private boolean left = false;
    private boolean right = false;

    private Shape fallingShape;

    private Drawer drawer;
    private GameField field;

    public TetrisControlMove(Drawer drawer, GameField field) {
        this.drawer = drawer;
        this.field = field;
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
