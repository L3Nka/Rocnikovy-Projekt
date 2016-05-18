/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Vector;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Hra sprostredkuva vymenu informacii medzi tym co sa zobrazi na obrazovke
 * uzivatela pri spusteni hry a spracuvava interakcie hraca s hrou.
 *
 * @author Lenka
 */
public abstract class Hra {
    DBworkerPreHru db;
    int pocetOtazok;
    Vector<MyResults> zleOdpovede = new Vector<MyResults>();
        
    /**
     * Vypise vysledok, ako aj zobrazi zle odpovede, aby sa uzivatel mohol
     * poucit. POZOR vo vecore zleOdpovede nie su len tie, na ktore sme sa
     * pytali ako otazku, ale hrac ak dal zle, tak nevedel ani to druhe co
     * urcil, lebo inak by vedel, ze to je zle a nedal by to.
     * Vysledok/percentualna uspesnost je teda 100-((zleOdpovede.lenght/2)
     * /pocetOtazok).
     * @param primaryStage
     * @return 
     */
    public Scene vypisVysledok(Stage primaryStage){
        //ScrollPane scrollpane = new ScrollPane();
        BorderPane root = new BorderPane();
        if (zleOdpovede==null || zleOdpovede.size()==0){
            Label lab = new Label("Gratulujeme, mate 100%!!\n\n");
            Pane pan = new Pane();
            pan.getChildren().add(lab);
            root.setCenter(pan);
        }
        else {
            Pane stlp = new VBox();
            System.out.println("pocet otazok " + pocetOtazok);
            System.out.println("zle odpo. size " + zleOdpovede.size());
            int uspesnost = 100-((100*(zleOdpovede.size()/2))/pocetOtazok);
            Label lab = new Label("Vasa uspesnost je " + uspesnost + "%\n");
            Label lab2 = new Label("Pozrite si vase zle odpovede:\n\n");
            stlp.getChildren().addAll(lab,lab2);
            for (int i=0;i<zleOdpovede.size();i++){
                Pane riadok = new HBox();
                Label sk = new Label("  "+zleOdpovede.get(i).getSlovenskyString()+ "  ");
                if (zleOdpovede.get(i).mameObr()){
                    ImageView img = new ImageView(zleOdpovede.get(i).obrazokJP);
                    riadok.getChildren().add(img);
                }
                Label jp = new Label("  "+zleOdpovede.get(i).getJaponskyString());
                riadok.getChildren().addAll(sk,jp);
                stlp.setPadding(new Insets(20));
                stlp.getChildren().addAll(riadok);
            }
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(stlp);
            root.setCenter(scrollPane);
        }
        Pane p = new Pane();
        Button menu = new Button("Menu");
        menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(Sceny.getMenu(primaryStage));
            }
        });  
        p.getChildren().add(menu);
        root.setBottom(p);
        root.setPrefHeight(300);
        return new Scene(root);
    }
        
}
