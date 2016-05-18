/*
 * PROJEKT MY JAPANESE WORLD
 */
package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Lenka
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, IOException {
////////////////////////////////////////////////////////////////////////////////
        primaryStage.setScene(Sceny.getUvod(primaryStage));
        ////////////////////////////////////////////////////////////////////////////////              
        //primaryStage.setFullScreen(true);
        primaryStage.setMinHeight(220);

        primaryStage.setTitle("My Japanese World");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        //Hra h = new Hra();
    }
    
}
