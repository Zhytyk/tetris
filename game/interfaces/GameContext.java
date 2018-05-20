package tetris.game.interfaces;

import tetris.models.errors.TetrisException;
import tetris.models.shapes.abstracts.Shape;

public interface GameContext {

    Shape getRandomShape() throws TetrisException;

}
