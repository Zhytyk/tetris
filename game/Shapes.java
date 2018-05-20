package tetris.game;


import tetris.models.errors.TetrisException;
import tetris.models.shapes.*;
import tetris.models.shapes.abstracts.Shape;

public enum Shapes {
    Cell(Cell.class), Square(Square.class),
    Line(Line.class), LShape(LShape.class),
    TShape(TShape.class), SShape(SShape.class);

    private Class<?> cls;

    Shapes(Class<?> cls) {
        this.cls = cls;
    }

    public <T extends Shape> T newInstance() throws TetrisException {
        try {
            return (T) cls.getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new TetrisException(e.getMessage());
        }
    }
}
