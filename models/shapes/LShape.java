package tetris.models.shapes;

import javafx.scene.canvas.GraphicsContext;
import tetris.models.shapes.abstracts.CompositeShape;
import tetris.models.shapes.interfaces.LShapeRotatable;

public class LShape extends CompositeShape implements LShapeRotatable {
    private static int[][] pattern = ROTATE_PATTERN[0];
    private static int rotateIterator = 0;

    public LShape() {
        super(pattern);
    }

    @Override
    public void drawRotateUp(GraphicsContext gc) {
        rotateIterator = ++rotateIterator % ROTATE_PATTERN.length;
        pattern = ROTATE_PATTERN[rotateIterator];

        for (int i = 0; i < cells.length; i++) {
            cells[i] = new Cell(cells[0].getX() + pattern[i][0], cells[0].getY() + pattern[i][1], getColor());
        }
    }

    @Override
    public void drawRotateDown(GraphicsContext gc) {
        rotateIterator = Math.abs(--rotateIterator % ROTATE_PATTERN.length);
        pattern = ROTATE_PATTERN[rotateIterator];

        for (int i = 0; i < cells.length; i++) {
            cells[i] = new Cell(cells[0].getX() + pattern[i][0], cells[0].getY() + pattern[i][1], getColor());
        }
    }

    @Override
    public boolean isVerticalEdge(boolean[][] cells) {
        return isVerticalEdge(cells, rotateIterator % 2 == 0 ? 2 : 1);
    }

    @Override
    public boolean isHorizontalRightEdge(boolean[][] cells) {
        return isHorizontalRightEdge(cells, rotateIterator % 2 == 0 ? 1 : 2);
    }

    @Override
    public boolean isHorizontalLeftEdge(boolean[][] cells) {
        return isHorizontalLeftEdge(cells, rotateIterator % 2 == 0 ? 1 : 2);
    }

    @Override
    public boolean isRotateUpEdge(boolean[][] cells) {
        return isRotateEdge(cells, ROTATE_PATTERN[Math.abs((rotateIterator + 1) % ROTATE_PATTERN.length)]);
    }

    @Override
    public boolean isRotateDownEdge(boolean[][] cells) {
        return isRotateEdge(cells, ROTATE_PATTERN[Math.abs((rotateIterator - 1) % ROTATE_PATTERN.length)]);
    }
}
