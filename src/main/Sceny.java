/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import static javafx.scene.layout.BackgroundSize.AUTO;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Obsahuje pokope skoro vsetky sceny.
 * @author Lenka
 */
class Sceny {
    
    static DBHiragana db = new DBHiragana();
    
    /**
     * Uplne prva, uvodna scena s buttonami Nova hra(resp "meno") a Koniec.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    static Scene getUvod(Stage primaryStage) throws FileNotFoundException, IOException {
        Pane pan = new VBox();
        Scanner s = new Scanner(new File("hraci.txt"));
        Label textHore = new Label("Kon'nichiwa!");
        Button Hra = new Button("");   //nova hra
        Hra.setBackground(new Background(new BackgroundImage(new Image("/btn.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(AUTO, AUTO, true, true, true, true))));
        Hra.setMinSize(235,60);
        if(!s.hasNext()){ //NovaHra
            s.close();
            Hra.setText("Nova Hra");
            Hra.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Stage dialog = new Stage();
                    dialog.initStyle(StageStyle.UTILITY);
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    TilePane pan = new TilePane();
                    pan.setMinSize(150, 150);
                    Scene sc = new Scene(pan);

                    TextField nText = new TextField();
                    Label nLabel = new Label("Zadajte svoje meno: ");
                    Button ok = new Button("OK");
                    
                    ok.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                String meno=nText.getText();   
                                if (meno.length()>0 && !meno.equals("Nova Hra") && !meno.equals("Nova hra")) {
                                    PrintWriter outhraci = new PrintWriter(new FileWriter(new File("hraci.txt")));
                                    System.out.println("Meno napisane je " + meno);
                                    outhraci.println(meno+"\n");
                                    outhraci.close();
                                    db.vygenerujNovuDB(0, "HIRAGANA");
                                    primaryStage.setScene(Sceny.getUvod(primaryStage));
                                    dialog.close();
                                }
                            } catch (Exception e) {
                                System.err.println("ERR:Zle meno");
                            }

                        }
                    });
                    pan.setPrefColumns(2);
                    pan.getChildren().addAll(nLabel, nText, ok);
                    dialog.setScene(sc);
                    dialog.showAndWait();
                };
        });
///////////////////////////////////////////////////
        }
        else{ //je uzivatel vytvoreny
            String name = s.nextLine();
            s.close();
            Hra.setText("" + name);
            Hra.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    db.idUzivatela = 0;
                    db.nacitaj(0, null);
                    primaryStage.setScene(Sceny.getMenu(primaryStage));
                }
            });
        
        }
            Button koniec = new Button("Koniec");
        koniec.setBackground(new Background(new BackgroundImage(new Image("/btn.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(AUTO, AUTO, true, true, true, true))));
        koniec.setMinSize(235, 60);
        koniec.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });   
        //koniec.setAlignment(Pos.CENTER);
        pan.getChildren().addAll(textHore,Hra,koniec);
        
        Scene uvod = new Scene(pan);
        return uvod;
    }

    /**
     * Scena Menu s buttonami Hiragana, Nastavenie a Koniec.
     */
    static Scene getMenu(Stage primaryStage) {
        Pane pan = new VBox();
        Label textHore = new Label("Menu");
        Button hiragana = new Button("Hiragana");
        Button nastavenie= new Button("Natavenie");
        Button koniec = new Button("Koniec");
        
        hiragana.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(Sceny.getHiraganaGames(primaryStage));
            }
        });   
        
        nastavenie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(Sceny.getNastavania(primaryStage));
            }
        });
        
        koniec.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });   
        pan.getChildren().addAll(textHore,hiragana,nastavenie,koniec);
        return new Scene(pan);
    }
    /**
     * Scena s vyberom hier. Zatial je tu len Test na 4 moznosti.
     */
    private static Scene getHiraganaGames(Stage primaryStage) {
        Pane pan = new VBox();
        Label lab = new Label("Hiragana Games");
        Button test = new Button("Test so 4 možnosťami");
        Button back = new Button("Naspät");
        
        pan.getChildren().addAll(lab,test,back);
        
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(Sceny.getMenu(primaryStage));
            }
        });   
        
        test.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int idHry = 1;
                String typHry = "HIRAGANA";
                primaryStage.setScene(Sceny.getHraTestZaciatok(primaryStage, idHry, typHry));
            }
        });   
        return new Scene(pan);
    }

    /**
     * Ak si hrac zvoli hru, nainicializuje sa tu a spusti sa.
     */
    
    private static Scene getHraTestZaciatok(Stage primaryStage, int idHry, String typHry) {
        //inicializacia ////prerobit hore db na GET INSTENCE / Singleton
        //db.vyberZvolene(db.nacitaj(0, null)); //su nacitane aj vybrane lokalne v "db
        HraTestSo4Moznostami hra = new HraTestSo4Moznostami();
        hra.InicializujNovuHru(db);
        return Sceny.getHraTest(primaryStage, idHry, typHry, hra);
    }
    
    /**
     * Samotna hra "test" navrchu s otazkou a 4 moznostami.
     */
    private static Scene getHraTest(Stage primaryStage, int idHry, String typHry, HraTestSo4Moznostami hra) {
        BorderPane root = new BorderPane();
        Pane hore = new Pane();
        Pane stred = new HBox();
        Pane dole = new Pane();
        vecNaZobrazenie zobraz = hra.vytvorNaJednoZobrazenie();
        Button dalej = new Button("DALEJ");        
        Label otazka = new Label(""+zobraz.otazka);
        otazka.setTextAlignment(TextAlignment.CENTER);
        hore.getChildren().add(otazka);
        
        Button[] odpovede = new Button[zobraz.moznosti.size()];
        for (int i = 0; i < zobraz.moznosti.size(); i++) {
            if (zobraz.moznosti.get(i).mameObr()) {
                odpovede[i] = new Button(" ", new ImageView(zobraz.moznosti.get(i).obrazokJP));
            } else {
                odpovede[i] = new Button("" + zobraz.moznosti.get(i).getJaponskyString());
                odpovede[i].setStyle("-fx-font: 22 arial;");
            }
            final int i2 = i;
            odpovede[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                //db.ulozDoDB(null, null);
                    int myId = i2;
                    System.out.println("Idem posielat button s id " + myId);
                    int vyhodnotenie = hra.vyhodnot(myId);
                    if (vyhodnotenie == -1) {
                        System.err.println("Tato otazka uz bola vyhodnotena!");
                    } else if (vyhodnotenie == 1) {
                        for (int j = 0; j < 4; j++) {
                            odpovede[j].setDisable(true);
                        }
                        odpovede[myId].setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
                        odpovede[myId].setDisable(true);
                    } else {
                        for (int j = 0; j < 4; j++) {
                            //stred.getChildren().removeAll(odpovede[j]);
                            odpovede[j].setDisable(true);
                        }
                        odpovede[myId].setStyle("-fx-font: 22 arial; -fx-base: red;");
                        odpovede[hra.spravneIdPoradia].setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
                    }
                    dole.getChildren().add(dalej);

                }
            });
        }
        dalej.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(hra.mozeDalej()) { //Dalsia otazka
                    primaryStage.setScene(Sceny.getHraTest(primaryStage, idHry, typHry, hra));
                }
                else { // Koniec tejto hry, chcem vypisat uspesnost
                    db.ulozDoDB(null, null);
//                    hra.koniecTejtoHry();
                    primaryStage.setScene(hra.vypisVysledok(primaryStage));
                }
            }
        });   
        stred.getChildren().addAll(odpovede);
        root.setCenter(stred);
        root.setTop(hore);
        root.setBottom(dole);
        return new Scene(root);
    }    

    /**
     * Vytvori "Nastavenia" pre dany typ a zavola tuto scenu s nastaveniami.
     */
    private static Scene getNastavania(Stage primaryStage) {
        Nastavenie n = new Nastavenie(db);        
        return Nastavenie.getScenaPreTutoDb(primaryStage);
    }
}
