package tetris.utils;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import tetris.game.Constants;
import tetris.models.shapes.abstracts.Shape;
import tetris.utils.interfaces.Drawer;

public class DrawerImpl implements Drawer {
    private GraphicsContext graphicsContext;

    public DrawerImpl(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void clean(Shape shape) {
        shape.clean(graphicsContext);
    }

    @Override
    public synchronized void drawDown(Shape shape) {
        shape.drawDown(graphicsContext);
    }

    @Override
    public synchronized void drawLeft(Shape shape) {
        shape.drawLeft(graphicsContext);
    }

    @Override
    public synchronized void drawRight(Shape shape) {
        shape.drawRight(graphicsContext);
    }

    @Override
    public void drawRotateUp(Shape shape) {
        shape.drawRotateUp(graphicsContext);
    }

    @Override
    public void drawRotateDown(Shape shape) {
        shape.drawRotateDown(graphicsContext);
    }

    @Override
    public synchronized void rearrange(boolean[][] cells) {
        graphicsContext.clearRect(0, 0, Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
        Color color = Color.color(Math.random(), Math.random(), Math.random());

        for (int i = 0; i < Constants.rowLength(); i++) {
            for (int j = 0; j < Constants.cellLength(); j++) {
                if (cells[i][j]) {
                    graphicsContext.setFill(color);
                    graphicsContext.fillRect(j * Constants.HORIZONTAL_STEP, i * Constants.VERTICAL_STEP,
                            Constants.HORIZONTAL_STEP, Constants.VERTICAL_STEP);
                }
            }
        }
    }
}
