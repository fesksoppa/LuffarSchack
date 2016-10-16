
import controller.LuffaSchackController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import view.BoardWindow;
import view.NewGameWindow;

/**
 * Creates Controller and the view classes and ties them together. 
 *
 * 
 */
public class LuffaSchack extends Application {

    @Override
    public void start(Stage primaryStage) {

        BoardWindow boardWindow = new BoardWindow();
        NewGameWindow newGameWindow = new NewGameWindow();
        LuffaSchackController luffarSchackController = new LuffaSchackController(boardWindow, newGameWindow);

        ImageView image = new ImageView(new Image(getClass().getResourceAsStream("Luffarschackimage.jpg")));
        image.setRotate(-90);
        boardWindow.setCenter(image);
        boardWindow.setLeft(null);
        boardWindow.setRight(null);

        Scene scen1 = new Scene(boardWindow, 1150, 800);

        primaryStage.setScene(scen1);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
