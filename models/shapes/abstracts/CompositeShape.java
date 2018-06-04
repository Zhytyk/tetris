package tetris.models.shapes.abstracts;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import tetris.game.Constants;
import tetris.models.shapes.Cell;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Composite design pattern
 */
public abstract class CompositeShape extends Shape {
    protected Cell[] cells;

    public CompositeShape(int[][] pattern) {
        this.cells = new Cell[pattern.length];
        setY(
                Math.abs(Arrays.stream(pattern)
                        .min(Comparator.comparingInt(e -> e[1]))
                        .get()[1])
        );

        setW(
                Arrays.stream(pattern)
                        .max(Comparator.comparingInt(e -> Math.abs(e[0])))
                        .get()[1]
        );

        int wCells = Math.abs(getW() / Constants.HORIZONTAL_STEP);

        setColor(Color.color(Math.random(), Math.random(), Math.random()));
        setX(ThreadLocalRandom.current().nextInt(wCells, Constants.cellLength() - 1 - wCells) * Constants.HORIZONTAL_STEP);

        for (int i = 0; i < cells.length; i++) {
            cells[i] = new Cell(getX() + pattern[i][0], getY() + pattern[i][1], getColor());
        }
    }

    @Override
    public void drawDown(GraphicsContext gc) {
        for (Cell cell : cells) {
            cell.drawDown(gc);
        }
    }

    @Override
    public void drawLeft(GraphicsContext gc) {
        for (Cell cell : cells) {
            cell.drawLeft(gc);
        }
    }

    @Override
    public void drawRight(GraphicsContext gc) {
        for (Cell cell : cells) {
            cell.drawRight(gc);
        }
    }

    @Override
    public void clean(GraphicsContext gc) {
        for (Cell cell: cells) {
            cell.clean(gc);
        }
    }

    @Override
    public void occupyCells(boolean[][] cells) {
        setCells(cells, true);
    }

    @Override
    public void cleanCells(boolean[][] cells) {
        setCells(cells, false);
    }

    protected boolean isVerticalEdge(boolean[][] cells, int result) {
        int count = 0;

        for (Cell cell : this.cells) {
            if (cell.isVerticalEdge(cells)) {
                count++;
            }
        }

        return count > result;
    }

    protected boolean isHorizontalRightEdge(boolean[][] cells, int result) {
        int count = 0;

        for (Cell cell : this.cells) {
            if (cell.isHorizontalRightEdge(cells)) {
                count++;
            }
        }

        return count > result;
    }

    protected boolean isHorizontalLeftEdge(boolean[][] cells, int result) {
        int count = 0;

        for (Cell cell : this.cells) {
            if (cell.isHorizontalLeftEdge(cells)) {
                count++;
            }
        }

        return count > result;
    }

    protected boolean isRotateEdge(boolean[][] cells, int[][] pattern) {
        int y = this.cells[0].getY();
        int x = this.cells[0].getX();

        for (int i = 1; i < pattern.length; i++) {
            int curY = (y + pattern[i][1]) / Constants.VERTICAL_STEP;
            int curX = (x + pattern[i][0]) / Constants.HORIZONTAL_STEP;

            if (curX < 0 || curY < 0 || curY >= Constants.rowLength() || curX >= Constants.cellLength()) {
                return true;
            }

            if (cells[curY][curX]) {
                return true;
            }
        }

        return false;
    }

    public Cell[] getCells() {
        return cells;
    }

    public void setCells(Cell[] cells) {
        this.cells = cells;
    }

    private void setCells(boolean[][] cells, boolean val) {
        for (Cell cell : this.cells) {
            int x = cell.getX() / Constants.HORIZONTAL_STEP;
            int y = cell.getY() / Constants.VERTICAL_STEP;

            cells[y][x] = val;
        }
    }
}
