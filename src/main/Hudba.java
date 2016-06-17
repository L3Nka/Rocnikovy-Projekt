/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Trieda s hudbou.
 * @author Lenka
 */
public class Hudba {

    /**
     * Pusti hudbu.
     */
    public Hudba(Stage primaryStage) {
        ////Hudba v pozadi///
        
        URL resource = getClass().getResource("Hudba/CherryBlossom.mp3");
        MediaPlayer a = new MediaPlayer(new Media(resource.toString()));
        a.setVolume(0.2);
        a.setOnEndOfMedia(new Runnable() {
            public void run() {
                a.seek(Duration.ZERO);
            }
        });
        a.play();
        primaryStage.setOnCloseRequest(windowEvent -> {
            a.stop();
        });
    }
    
}
