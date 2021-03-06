package tetris.game;

import tetris.game.interfaces.GameControlRotate;
import tetris.game.interfaces.GameField;
import tetris.models.shapes.abstracts.Shape;
import tetris.utils.DrawerImpl;
import tetris.utils.interfaces.Drawer;

import java.util.Objects;

public class TetrisControlRotate implements GameControlRotate {
    private Drawer drawer;

    private GameField field;

    private Shape fallingShape;

    private static GameControlRotate instance;

    public static GameControlRotate getInstance() {
        if (Objects.isNull(instance)) {
            instance = new TetrisControlRotate();
        }

        return instance;
    }

    private TetrisControlRotate() {
        this.drawer = DrawerImpl.getInstance();
        this.field = TetrisField.getInstance();
    }

    @Override
    public void goUp() {
        if (Objects.isNull(fallingShape) || field.isRotateUpEdge(fallingShape)) {
            return;
        }

        field.cleanCells(fallingShape);
        drawer.clean(fallingShape);

        drawer.drawRotateUp(fallingShape);
        field.occupyCells(fallingShape);
    }

    @Override
    public void goDown() {
        if (Objects.isNull(fallingShape) || field.isRotateDownEdge(fallingShape)) {
            return;
        }

        Shape shape = fallingShape;

        field.cleanCells(shape);
        drawer.clean(shape);

        drawer.drawRotateDown(shape);
        field.occupyCells(shape);
    }

    @Override
    public void setFallingShape(Shape fallingShape) {
        this.fallingShape = fallingShape;
    }
}
