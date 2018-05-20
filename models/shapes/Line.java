package tetris.models.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import tetris.game.Constants;
import tetris.models.shapes.abstracts.Shape;

import java.util.concurrent.ThreadLocalRandom;

public class Line extends Shape {

    public Line() {
        setW(Constants.HORIZONTAL_STEP);
        setH(4 * Constants.VERTICAL_STEP);
        setColor(Color.color(Math.random(), Math.random(), Math.random()));
        setX(ThreadLocalRandom.current().nextInt(0, Constants.cellLength()) * Constants.HORIZONTAL_STEP);
    }

    @Override
    public void drawDown(GraphicsContext gc) {
        gc.setFill(getColor());
        setY(getY() + Constants.VERTICAL_STEP);

        gc.fillRect(getX(), getY(), getW(), getH());
    }

    @Override
    public void drawLeft(GraphicsContext gc) {
        gc.setFill(getColor());
        setX(getX() - Constants.HORIZONTAL_STEP);

        gc.fillRect(getX(), getY(), getW(), getH());
    }

    @Override
    public void drawRight(GraphicsContext gc) {
        gc.setFill(getColor());
        setX(getX() + Constants.HORIZONTAL_STEP);

        gc.fillRect(getX(), getY(), getW(), getH());
    }

    @Override
    public void drawRotateUp(GraphicsContext gc) {
        drawRotate(gc);
    }

    @Override
    public void drawRotateDown(GraphicsContext gc) {
        drawRotate(gc);
    }

    @Override
    public void occupyCells(boolean[][] cells) {
        setCells(cells, true);
    }

    @Override
    public void cleanCells(boolean[][] cells) {
        setCells(cells, false);
    }

    @Override
    public boolean isVerticalEdge(boolean[][] cells) {
        int x = getX() / Constants.HORIZONTAL_STEP;
        int y = getY() / Constants.VERTICAL_STEP;

        if ((getW() / Constants.HORIZONTAL_STEP) == 1) {
            if ((y + 4) >= Constants.rowLength()) {
                return true;
            }

            return cells[y + 4][x];
        } else {
            if ((y + 1) >= Constants.rowLength()) {
                return true;
            }

            return cells[y + 1][x] || cells[y + 1][x + 1] || cells[y + 1][x + 2] || cells[y + 1][x + 3];
        }
    }

    @Override
    public boolean isHorizontalRightEdge(boolean[][] cells) {
        int x = getX() / Constants.HORIZONTAL_STEP;
        int y = getY() / Constants.VERTICAL_STEP;

        if ((getW() / Constants.HORIZONTAL_STEP) == 1) {
            if ((x + 1) >= Constants.cellLength()) {
                return true;
            }

            return cells[y][x + 1] || cells[y + 1][x + 1] ||
                    cells[y + 2][x + 1] || cells[y + 3][x + 1];
        } else {
            if ((x + 4) >= Constants.cellLength()) {
                return true;
            }

            return cells[y][x + 4];
        }
    }

    @Override
    public boolean isHorizontalLeftEdge(boolean[][] cells) {
        int x = getX() / Constants.HORIZONTAL_STEP;
        int y = getY() / Constants.VERTICAL_STEP;

        if ((getW() / Constants.HORIZONTAL_STEP) == 1) {
            if ((x - 1) < 0) {
                return true;
            }

            return cells[y][x - 1] || cells[y + 1][x - 1] ||
                    cells[y + 2][x - 1] || cells[y + 3][x - 1];
        } else {
            if ((x - 1) < 0) {
                return true;
            }

            return cells[y][x - 1];
        }
    }

    @Override
    public boolean isRotateUpEdge(boolean[][] cells) {
        return isRotateEdge(cells);
    }

    @Override
    public boolean isRotateDownEdge(boolean[][] cells) {
        return isRotateEdge(cells);
    }

    private boolean isRotateEdge(boolean[][] cells) {
        int x = getX() / Constants.HORIZONTAL_STEP;
        int y = getY() / Constants.VERTICAL_STEP;

        if ((getW() / Constants.HORIZONTAL_STEP) == 1) {
            return (x + 3) >= Constants.cellLength() || cells[y][x + 1] || cells[y][x + 2] ||
                    cells[y][x + 3];
        } else {
            return (y + 3) >= Constants.rowLength() || cells[y + 1][x] || cells[y + 2][x] ||
                    cells[y + 3][x];
        }
    }

    private void drawRotate(GraphicsContext gc) {
        gc.setFill(getColor());

        if ((getW() / Constants.HORIZONTAL_STEP) == 1) {
            setW(4 * Constants.HORIZONTAL_STEP);
            setH(Constants.VERTICAL_STEP);
        } else {
            setW(Constants.HORIZONTAL_STEP);
            setH(4 * Constants.VERTICAL_STEP);
        }

        gc.fillRect(getX(), getY(), getW(), getH());
    }

    private void setCells(boolean[][] cells, boolean val) {
        int x = getX() / Constants.HORIZONTAL_STEP;
        int y = getY() / Constants.VERTICAL_STEP;

        if ((getW() / Constants.HORIZONTAL_STEP) == 1) {
            cells[y][x] = val;
            cells[y + 1][x] = val;
            cells[y + 2][x] = val;
            cells[y + 3][x] = val;
        } else {
            cells[y][x] = val;
            cells[y][x + 1] = val;
            cells[y][x + 2] = val;
            cells[y][x + 3] = val;
        }

    }
}
