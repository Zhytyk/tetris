package tetris.models.errors;

public class ExceptionHandler {
    public static void handleExcpetion(TetrisException e) {
        System.out.println(e.getMessage());
    }
}
