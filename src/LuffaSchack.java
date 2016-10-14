
import controller.LuffaSchackController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import view.BoardWindow;
import view.NewGameWindow;

/**
 *
 * @author Krist
 */
public class LuffaSchack extends Application {
    
      
    
    @Override
    public void start(Stage primaryStage) {
        
        
        
        BoardWindow boardWindow = new BoardWindow();
        NewGameWindow newGameWindow = new NewGameWindow();
        LuffaSchackController luffarSchackController = new LuffaSchackController(boardWindow,newGameWindow);
        
        
        ImageView image = new ImageView(new Image(getClass().getResourceAsStream("Luffarschackimage.jpg")));
        image.setRotate(-90);
        boardWindow.setCenter(image);
        boardWindow.setLeft(null);
        boardWindow.setRight(null);
        
       
        
        Scene scen1 = new Scene(boardWindow, 1150,800);
        
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
