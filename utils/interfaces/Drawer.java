package tetris.utils.interfaces;

import tetris.models.shapes.abstracts.Shape;

public interface Drawer {

    void clean(Shape shape);

    void drawDown(Shape shape);

    void drawLeft(Shape shape);

    void drawRight(Shape shape);

    void drawRotateUp(Shape shape);

    void drawRotateDown(Shape shape);

    void rearrange(boolean[][] cells);
}
