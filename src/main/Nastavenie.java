/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Nastavenia.
 * @author Lenka
 */
public class Nastavenie {
    static DBworker db;
    
    /**
     *
     * @param db
     */
    public Nastavenie(DBworker db) {
        this.db = db;
    }

    /**
     * Vrati scenu s nastavenim pre zvolenu databazu.
     */
    static Scene getScenaPreTutoDb(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Vector<MyResults> nastavujem = db.getVsetky();
        Pane hore = new Pane();
        hore.getChildren().add(new Label("Nastavenie\n\n"));
        VBox zoznam = new VBox();
        /////////////////////////////////////////////
        CheckBox[] cb = new CheckBox[nastavujem.size()];
        for(int i=0;i<nastavujem.size();i++){
            MyResults mr = nastavujem.get(i);
            cb[i] = new CheckBox(""+mr.getSlovenskyString());
            if (mr.chcem>0){
                cb[i].setSelected(true);
            }
            zoznam.getChildren().add(cb[i]);
        }
        /////////////////////////////////////////////
        Button ok = new Button("Uloz Zmeny");
        ok.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                int pocetZaznacenych = 0;
                for(int i=0;i<cb.length;i++){
                    if(cb[i].isSelected()){
                        db.aktualizujVyber(i, 1);
                        //nastavujem.get(i).chcem=1;
                        pocetZaznacenych++;
                    }
                    else {
                        db.aktualizujVyber(i, 0);
                        //nastavujem.get(i).chcem=0;
                    }       
                }
                if(pocetZaznacenych<3){
                    System.err.println("MUSITE ZAZNACIT ASPON 3");
                }
                else{
                    db.ulozDoDB(null, null);
                    db.vyberZvolene(null);
                    primaryStage.setScene(Sceny.getMenu(primaryStage));
                }
            }
            
        });
        /////////////////////////////////////////////
        Button oznacV = new Button("Oznac Vsetky");
        oznacV.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                for(int i=0;i<cb.length;i++){
                    cb[i].setSelected(true);
                }
            }
            
        });
        /////////////////////////////////////////////
        Button odznacV = new Button("Odznac Vsetky");
        odznacV.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                for(int i=0;i<cb.length;i++){
                    cb[i].setSelected(false);
                }
            }
        });
        /////////////////////////////////////////////
        Button ZrusitUcet= new Button("!!!ZRUSIT UCET!!!");
        ZrusitUcet.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    PrintWriter outhraci = new PrintWriter(new FileWriter(new File("hraci.txt"),false));
                    outhraci.print("");
                    primaryStage.setScene(Sceny.getUvod(primaryStage));
                } catch (IOException ex) {
                    Logger.getLogger(Nastavenie.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        /////////////////////////////////////////////
        HBox dole = new HBox();
        dole.getChildren().addAll(ok,oznacV,odznacV,ZrusitUcet);
        root.setBottom(dole);
        
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(zoznam);
        root.setCenter(scrollPane);
        root.setTop(hore);
        root.setPrefHeight(300);
        return new Scene(root);
    }
}
