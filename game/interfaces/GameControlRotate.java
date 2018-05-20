package tetris.game.interfaces;

import tetris.models.shapes.abstracts.Shape;

public interface GameControlRotate {
    void setFallingShape(Shape fallingShape);

    void goUp();

    void goDown();
}
