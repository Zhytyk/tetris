package tetris.game.interfaces;

import tetris.models.shapes.abstracts.Shape;

public interface GameField {

    boolean isHorizontalLeftEdge(Shape shape);

    boolean isHorizontalRightEdge(Shape shape);

    boolean isVerticalEdge(Shape shape);

    boolean isRotateUpEdge(Shape shape);

    boolean isRotateDownEdge(Shape shape);

    void cleanCells(Shape shape);

    void occupyCells(Shape shape);

    boolean rearrange();

    boolean[][] getCells();
}
