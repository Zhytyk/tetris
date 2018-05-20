package tetris.models.shapes.abstracts;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {
    private Color color;
    private int x;
    private int y;
    private int w;
    private int h;

    public Shape() {
        x = 0;
        y = 0;
    }

    public abstract void drawDown(GraphicsContext gc);

    public abstract void drawLeft(GraphicsContext gc);

    public abstract void drawRight(GraphicsContext gc);

    public abstract void drawRotateUp(GraphicsContext gc);

    public abstract void drawRotateDown(GraphicsContext gc);

    public abstract void occupyCells(boolean[][] cells);

    public abstract void cleanCells(boolean[][] cells);

    public abstract boolean isVerticalEdge(boolean[][] cells);

    public abstract boolean isHorizontalRightEdge(boolean[][] cells);

    public abstract boolean isHorizontalLeftEdge(boolean[][] cells);

    public abstract boolean isRotateUpEdge(boolean[][] cells);

    public abstract boolean isRotateDownEdge(boolean[][] cells);

    public void clean(GraphicsContext gc) {
        gc.clearRect(getX(), getY(), getW(), getH());
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
}
