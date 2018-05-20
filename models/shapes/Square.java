package tetris.models.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import tetris.game.Constants;
import tetris.models.shapes.abstracts.Shape;

import java.util.concurrent.ThreadLocalRandom;

public class Square extends Shape {

    public Square() {
        setW(2 * Constants.HORIZONTAL_STEP);
        setH(2 * Constants.VERTICAL_STEP);
        setColor(Color.color(Math.random(), Math.random(), Math.random()));
        setX(ThreadLocalRandom.current().nextInt(0, Constants.cellLength() - 3) * Constants.HORIZONTAL_STEP);
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

    }

    @Override
    public void drawRotateDown(GraphicsContext gc) {

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

        if ((y + 2) >= Constants.rowLength()) {
            return true;
        }

        return cells[y + 2][x] || cells[y + 2][x + 1];
    }

    @Override
    public boolean isHorizontalRightEdge(boolean[][] cells) {
        int x = getX() / Constants.HORIZONTAL_STEP;
        int y = getY() / Constants.VERTICAL_STEP;

        if ((x + 2) >= Constants.cellLength()) {
            return true;
        }

        return cells[y][x + 2] || cells[y + 1][x + 2];
    }

    @Override
    public boolean isHorizontalLeftEdge(boolean[][] cells) {
        int x = getX() / Constants.HORIZONTAL_STEP;
        int y = getY() / Constants.VERTICAL_STEP;

        if ((x - 1) < 0) {
            return true;
        }

        return cells[y][x - 1] || cells[y + 1][x - 1];
    }

    @Override
    public boolean isRotateUpEdge(boolean[][] cells) {
        return true;
    }

    @Override
    public boolean isRotateDownEdge(boolean[][] cells) {
        return true;
    }

    private void setCells(boolean[][] cells, boolean val) {
        int x = getX() / Constants.HORIZONTAL_STEP;
        int y = getY() / Constants.VERTICAL_STEP;

        cells[y][x] = val;
        cells[y][x + 1] = val;
        cells[y + 1][x] = val;
        cells[y + 1][x + 1] = val;
    }
}
