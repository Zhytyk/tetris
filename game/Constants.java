package tetris.game;

public interface Constants {
    /**
     * Localization
     */
    String TITLE = "TetrisContext";

    /**
     * Game properties
     */
    int CANVAS_WIDTH = 600;
    int CANVAS_HEIGHT = 600;
    int VERTICAL_STEP = 20;
    int HORIZONTAL_STEP = 20;

    /**
     * Paths
     */
    String VIEW_FXML = "views/view.fxml";
    String STYLE_CSS = "resources/style.css";

    static int rowLength() {
        return Constants.CANVAS_WIDTH / Constants.HORIZONTAL_STEP;
    }

    static int cellLength() {
        return Constants.CANVAS_WIDTH / Constants.HORIZONTAL_STEP;
    }
}
