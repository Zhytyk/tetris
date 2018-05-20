package tetris.models.shapes.interfaces;

import tetris.game.Constants;

public interface TShapeRotatable {
    int[][][] ROTATE_PATTERN = {
        {
            {0, 0},
            {Constants.HORIZONTAL_STEP, 0},
            {2 * Constants.HORIZONTAL_STEP, 0},
            {Constants.HORIZONTAL_STEP, Constants.VERTICAL_STEP}
        }, {
            {0, 0},
            {0, -Constants.VERTICAL_STEP},
            {0, -Constants.VERTICAL_STEP * 2},
            {Constants.HORIZONTAL_STEP, -Constants.VERTICAL_STEP}
        }, {
            {0, 0},
            {-Constants.HORIZONTAL_STEP, 0},
            {-Constants.HORIZONTAL_STEP * 2, 0},
            {-Constants.HORIZONTAL_STEP, -Constants.VERTICAL_STEP}
        }, {
            {0, 0},
            {0, Constants.VERTICAL_STEP},
            {0, 2 * Constants.VERTICAL_STEP},
            {-Constants.HORIZONTAL_STEP, Constants.VERTICAL_STEP}
        }
    };
}
