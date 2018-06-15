package javafxapplication2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXApplication2 extends Application {
    static FXMLLoader okienko1;
  //  static FXMLLoader okienko2;
    
    @Override
    public void start(Stage stage) throws Exception {
        okienko1 = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
      //  okienko2 = new FXMLLoader(getClass().getResource("NewWindow.fxml"));
        
        Parent root = (Parent) okienko1.load();
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
