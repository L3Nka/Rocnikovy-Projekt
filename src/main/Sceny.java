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
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Reflection;
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
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * Obsahuje pokope skoro vsetky sceny.
 *
 * @author Lenka
 */
class Sceny {

    static DBHiragana db = new DBHiragana();

    static Double vyska;
    static Double sirka;
    static Double buttonSirka = 235D;
    static Double buttonVyska = 86D;

    /**
     * Zisti uzivatelovo rozlisenie obrazovky.
     */
    static void ZistiRozlisenie() {
        Rectangle2D obrazovka = Screen.getPrimary().getVisualBounds();
        vyska = obrazovka.getHeight();
        sirka = obrazovka.getWidth();
    }

    /**
     * Nastavi pozadie na Fullscreen a prida scene stylesheet.
     */
    static void NastavPozadie(Stage st, Scene sc) {
        //st.setFullScreen(false);
        st.setFullScreen(true);
        sc.getStylesheets().add("styly/Styles.css");
    }

    /**
     * Uplne prva, uvodna scena s buttonami Nova hra(resp "meno") a Koniec.
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    static Scene getUvod(Stage primaryStage) throws FileNotFoundException, IOException {
        VBox pan = new VBox();
        pan.getStyleClass().add("vbox");
        pan.setId("uvitacka");

        Scanner s = new Scanner(new File("hraci.txt"));
        Label textHore = new Label("Kon'nichiwa!");

        Button Hra = new Button("");   //nova hra
        Hra.setMinSize(buttonSirka, buttonVyska);
        if (!s.hasNext()) { //NovaHra
            s.close();
            Hra.setText("Nová Hra");
            Hra.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Stage dialog = new Stage();
                    dialog.initStyle(StageStyle.UTILITY);
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    TilePane pan = new TilePane();
                    pan.setMinSize(150, 150);
                    Scene sc3 = new Scene(pan);
                    sc3.getStylesheets().add("styly/Styles.css");
                    TextField nText = new TextField();
                    Label nLabel = new Label("Zadajte svoje meno: ");
                    Button ok = new Button("OK");

                    ok.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                String meno = nText.getText();
                                if (meno.length() > 0 && !meno.equals("Nová Hra") && !meno.equals("Nová hra")) {
                                    PrintWriter outhraci = new PrintWriter(new FileWriter(new File("hraci.txt")));
                                    //System.out.println("Meno napisane je " + meno);
                                    outhraci.println(meno + "\n");
                                    outhraci.close();
                                    db.vygenerujNovuDB(0, "HIRAGANA");
                                    Scene sc2 = Sceny.getUvod(primaryStage);
                                    primaryStage.setScene(sc2);
                                    NastavPozadie(primaryStage, sc2);
                                    dialog.close();
                                }
                            } catch (Exception e) {
                                System.err.println("ERR:Zle meno");
                            }

                        }
                    });
                    pan.setPrefColumns(2);
                    pan.getChildren().addAll(nLabel, nText, ok);
                    dialog.setScene(sc3);
                    dialog.showAndWait();
                }
            ;
        }  
            );
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
                    Scene sc = Sceny.getMenu(primaryStage);
                    primaryStage.setScene(sc);
                    NastavPozadie(primaryStage, sc);
                }
            });

        }
        Button koniec = new Button("Koniec");

        koniec.setMinSize(buttonSirka, buttonVyska);
        koniec.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        pan.getChildren().addAll(textHore, Hra, koniec);

        ////////Style
        textHore.setId("velkyLabel");
        Hra.setId("UvodnyButton");
        koniec.setId("UvodnyButton");
        ////////
        Scene uvod = new Scene(pan);

        uvod.getStylesheets().add("styly/Styles.css");
        return uvod;
    }

    /**
     * Scena Menu s buttonami Hiragana, Nastavenie a Koniec.
     */
    static Scene getMenu(Stage primaryStage) {
        Pane pan = new VBox();
        pan.setId("uvitacka");
        Label textHore = new Label("Menu");
        Button hiragana = new Button("Hiragana");
        Button nastavenie = new Button("Nastavenia");
        Button koniec = new Button("Koniec");

        hiragana.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Scene sc = Sceny.getHiraganaGames(primaryStage);
                primaryStage.setScene(sc);
                NastavPozadie(primaryStage, sc);
            }
        });

        nastavenie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Scene sc = Sceny.getNastavania(primaryStage);
                primaryStage.setScene(sc);
                NastavPozadie(primaryStage, sc);
            }
        });

        koniec.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        pan.getChildren().addAll(textHore, hiragana, nastavenie, koniec);

        ////////Style
        textHore.setId("velkyLabel");
        hiragana.setId("UvodnyButton");
        hiragana.setMinSize(buttonSirka, buttonVyska);
        nastavenie.setId("UvodnyButton");
        nastavenie.setMinSize(buttonSirka, buttonVyska);
        koniec.setId("UvodnyButton");
        koniec.setMinSize(buttonSirka, buttonVyska);
        ////////

        Scene sc = new Scene(pan);
        return sc;
    }

    /**
     * Scena s vyberom hier. Zatial je tu len Test na 4 moznosti.
     */
    private static Scene getHiraganaGames(Stage primaryStage) {
        Pane pan = new VBox();
        pan.setId("Hra_root_pane");
        Label lab = new Label("Hiragana Hry");
        Button test = new Button("Test so 4 možnosťami");
        Button back = new Button("Naspäť");

        pan.getChildren().addAll(lab, test, back);

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Scene sc = Sceny.getMenu(primaryStage);
                primaryStage.setScene(sc);
                NastavPozadie(primaryStage, sc);
            }
        });

        test.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int idHry = 1;
                String typHry = "HIRAGANA";
                Scene sc = Sceny.getHraTestZaciatok(primaryStage, idHry, typHry);
                primaryStage.setScene(sc);
                NastavPozadie(primaryStage, sc);
            }
        });

        ////////Style
        lab.setId("velkyLabel");
        test.setId("UvodnyButton");
        test.setMinSize(buttonSirka, buttonVyska);
        back.setId("UvodnyButton");
        back.setMinSize(buttonSirka, buttonVyska);
        ////////

        Scene sc = new Scene(pan);
        return sc;
    }

    /**
     * Ak si hrac zvoli hru, nainicializuje sa tu a spusti sa.
     */
    private static Scene getHraTestZaciatok(Stage primaryStage, int idHry, String typHry) {
        //inicializacia ////mozno prerobit neskor hore db na GET INSTENCE / Singleton
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
        Pane hore = new VBox();
        Pane stred = new HBox();
        Pane dole = new VBox();
        vecNaZobrazenie zobraz = hra.vytvorNaJednoZobrazenie();
        Button dalej = new Button("ĎALEJ");
        Label otazka = new Label("" + zobraz.otazka);
        otazka.setTextAlignment(TextAlignment.CENTER);
        hore.getChildren().add(otazka);

        root.setId("Hra_root_pane");
        hore.setId("pane_otazka");
        stred.setId("pane_s_moznostami");
        dole.setId("pane_s_objavujucim_sa_dalej");

        stred.setMaxSize(sirka / 2 / 2 / 2, vyska / 2 / 2 / 2);

        Button[] odpovede = new Button[zobraz.moznosti.size()];
        for (int i = 0; i < zobraz.moznosti.size(); i++) {
            if (zobraz.moznosti.get(i).mameObr()) {
                odpovede[i] = new Button(" ", new ImageView(zobraz.moznosti.get(i).obrazokJP));
                odpovede[i].setMinSize(250, 250);
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
                        //odpovede[myId].setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
                        odpovede[myId].setId("spravna_odpoved");
                        odpovede[myId].setDisable(true);

                        ////////////////Animacia, zvacsenie gombika, ak je spravny
                        ScaleTransition sctr = new ScaleTransition(Duration.millis(800), odpovede[myId]);
                        sctr.setByX(0.5);
                        sctr.setByY(0.5);
                        sctr.setCycleCount(2);
                        sctr.setAutoReverse(true);
                        sctr.play();
                        /////////////////
                    } else {
                        for (int j = 0; j < 4; j++) {
                            odpovede[j].setDisable(true);
                        }
                        odpovede[myId].setId("nespravna_odpoved");
                        odpovede[hra.spravneIdPoradia].setId("spravna_odpoved");
                        
                        ////////////////Animacia, zvacsenie gombika, ak je nespravny
                        ScaleTransition sctr = new ScaleTransition(Duration.millis(400), odpovede[myId]);
                        sctr.setByX(-0.5);
                        sctr.setByY(-0.5);
                        sctr.setCycleCount(2);
                        sctr.setAutoReverse(true);
                        sctr.play();
                        /////////////////
                        ////////////////Animacia, zvacsenie gombika, ak je spravny
                        ScaleTransition sctr2 = new ScaleTransition(Duration.millis(800), odpovede[hra.spravneIdPoradia]);
                        sctr2.setByX(0.5);
                        sctr2.setByY(0.5);
                        sctr2.setCycleCount(2);
                        sctr2.setAutoReverse(true);
                        sctr2.play();
                        /////////////////
                        
                    }
                    dalej.setVisible(true);

                }
            });
        }
        dalej.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (hra.mozeDalej()) { //Dalsia otazka
                    Scene sc = Sceny.getHraTest(primaryStage, idHry, typHry, hra);
                    primaryStage.setScene(sc);
                    NastavPozadie(primaryStage, sc);
                } else { // Koniec tejto hry, chcem vypisat uspesnost
                    db.ulozDoDB(null, null);
//                    hra.koniecTejtoHry();
                    Scene sc = hra.vypisVysledok(primaryStage);
                    primaryStage.setScene(sc);
                    NastavPozadie(primaryStage, sc);
                }
            }
        });
        dole.getChildren().add(dalej);
        dalej.setVisible(false);
        stred.getChildren().addAll(odpovede);
        root.setCenter(stred);
        root.setTop(hore);
        root.setBottom(dole);

        Scene sc = new Scene(root);
        return sc;
    }

    /**
     * Vytvori "Nastavenia" pre dany typ a zavola tuto scenu s nastaveniami.
     */
    private static Scene getNastavania(Stage primaryStage) {
        Nastavenie n = new Nastavenie(db);
        return Nastavenie.getScenaPreTutoDb(primaryStage);
    }
}
