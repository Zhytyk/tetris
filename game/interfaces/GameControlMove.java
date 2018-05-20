package tetris.game.interfaces;

import tetris.models.shapes.abstracts.Shape;

public interface GameControlMove {

    void setFallingShape(Shape shape);

    void goRight();

    void goLeft();

    void stopLeft();

    void stopRight();

}
