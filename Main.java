package tetris;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static tetris.game.Constants.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene root = FXMLLoader.load(getClass().getResource(VIEW_FXML));
        root.getStylesheets().add(getClass().getResource(STYLE_CSS).toExternalForm());

        primaryStage.setTitle(TITLE);
        primaryStage.setScene(root);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
