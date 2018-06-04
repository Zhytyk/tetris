package tetris.game;

import tetris.game.interfaces.GameField;
import tetris.models.shapes.abstracts.Shape;

public class TetrisField implements GameField {
    private boolean[][] cells;

    private static GameField instance = new TetrisField();

    public static GameField getInstance() {
        return instance;
    }

    public TetrisField() {
        cells = new boolean[Constants.rowLength()][Constants.cellLength()];
    }

    @Override
    public boolean isHorizontalLeftEdge(Shape shape) {
        return shape.isHorizontalLeftEdge(cells);
    }

    @Override
    public boolean isHorizontalRightEdge(Shape shape) {
        return shape.isHorizontalRightEdge(cells);
    }

    @Override
    public boolean isVerticalEdge(Shape shape) {
        return shape.isVerticalEdge(cells);
    }

    @Override
    public boolean isRotateUpEdge(Shape shape) {
        return shape.isRotateUpEdge(cells);
    }

    @Override
    public boolean isRotateDownEdge(Shape shape) {
        return shape.isRotateDownEdge(cells);
    }

    @Override
    public void cleanCells(Shape shape) {
        shape.cleanCells(cells);
    }

    @Override
    public void occupyCells(Shape shape) {
        shape.occupyCells(cells);
    }

    @Override
    public boolean rearrange() {
        int startRow = -1;
        int endRow = -1;

        startRow = findStartRow();
        if (startRow == -1) {
            return false;
        }

        endRow = findEndRow(startRow);

        rearrangeInner(startRow, endRow);

        return true;
    }

    private int findStartRow() {
        int rowLength = Constants.rowLength();
        int cellLength = Constants.cellLength();

        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < cellLength; j++) {
                if (!cells[i][j]) {
                    break;
                }

                if (j == (cellLength - 1)) {
                    return i;
                }
            }
        }

        return -1;
    }

    private int findEndRow(int startRow) {
        int endRow = startRow;

        int rowLength = Constants.rowLength();
        int cellLength = Constants.cellLength();

        for (int i = startRow + 1; i < rowLength; i++) {
            for (int j = 0; j < cellLength; j++) {
                if (!cells[i][j]) {
                    return endRow;
                }

                if (j == (cellLength - 1)) {
                    endRow = i;
                }
            }
        }

        return endRow;
    }

    private void rearrangeInner(int startRow, int endRow) {
        boolean[][] dest = new boolean[Constants.rowLength()][Constants.cellLength()];

        System.arraycopy(cells, endRow, dest, endRow, Constants.rowLength() - endRow);
        System.arraycopy(cells, 0, dest, endRow - startRow + 1, startRow);

        cells = dest;
    }

    public boolean[][] getCells() {
        return cells;
    }
}
