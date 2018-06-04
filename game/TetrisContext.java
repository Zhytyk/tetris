package tetris.game;

import tetris.game.interfaces.GameContext;
import tetris.game.interfaces.GameControlMove;
import tetris.game.interfaces.GameControlRotate;
import tetris.game.interfaces.GameField;
import tetris.models.errors.ExceptionHandler;
import tetris.models.errors.TetrisException;
import tetris.models.shapes.abstracts.Shape;
import tetris.utils.DrawerImpl;
import tetris.utils.interfaces.Drawer;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class TetrisContext implements GameContext, Runnable  {
    private Shape fallingShape;

    private GameControlMove controlMove;
    private GameControlRotate controlRotate;

    private GameField field;
    private Drawer drawer;

    private static GameContext instance;

    /**
     * Signleton design pattern
     */
    public static GameContext getInstance() {
        if (Objects.isNull(instance)) {
            instance = new TetrisContext();
        }

        return instance;
    }

    private TetrisContext() {
        this.drawer = DrawerImpl.getInstance();
        this.controlMove = TetrisControlMove.getInstance();
        this.controlRotate = TetrisControlRotate.getInstance();
        this.field = TetrisField.getInstance();
    }

    @Override
    public void run() {
        try {
            if (Objects.isNull(fallingShape) || field.isVerticalEdge(fallingShape)) {
                if (field.rearrange()) {
                    drawer.rearrange(field.getCells());
                }

                fallingShape = getRandomShape();
                controlMove.setFallingShape(fallingShape);
                controlRotate.setFallingShape(fallingShape);
            }

            field.cleanCells(fallingShape);
            drawer.clean(fallingShape);

            drawer.drawDown(fallingShape);
            field.occupyCells(fallingShape);
        } catch (TetrisException e) {
            ExceptionHandler.handleExcpetion(e);
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Shape getRandomShape() throws TetrisException {
        return Shapes.values()[Math.abs(ThreadLocalRandom.current().nextInt(0, Shapes.values().length))].newInstance();
    }
}
