/*
 * PROJEKT MY JAPANESE WORLD
 */
package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javafx.application.Application;
import javafx.scene.input.KeyCombination;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Lenka
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, IOException {
////////////////////////////////////////////////////////////////////////////////
        Hudba hudba = new Hudba(primaryStage);
////////////////////////////////////////////////////////////////////////////////
        
        primaryStage.setFullScreenExitHint("");
        
        Sceny.ZistiRozlisenie();
        
        primaryStage.setFullScreen(true);
        primaryStage.setScene(Sceny.getUvod(primaryStage));
        ////////////////////////////////////////////////////////////////////////////////              
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
