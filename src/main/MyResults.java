/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import javafx.scene.image.Image;

/**
 * Struktura, ktora obsahuje udaje o slovicku/pismene.
 * (pre slovicka pridat polozku vyslovnost/resp.romanji)
 * @author Lenka
 */
class MyResults {
    int id;
    String sk;
    String jp;
    int pocetVsetkych; // pocet kolkokrat uzivatel riesil toto slovicko/pismeno
    int pocetSpravnych; // pocet kolkorat zo Vsetkych odpovedal uzivatel spravne
    int chcem;  // ci uzivatel chce precvicovat toto slovicko/pismeno (pozn. boolean to nie je preto, keby sa rozhodneme davat uzivatelovi moznost vyjadrit sa ako moc chce precvicovat dane slovicko/pismeno)
    Image obrazokJP=null;
    
    // PLUS PRIDAT VECTOR ZE S KTORYMI SI TO UZIVATEL MYLI??

    public MyResults(int idd, String skk, String jpp, int vsetky, int spravne, int chcemm) {
        id = idd;
        sk = skk;
        jp = jpp;
        this.pocetVsetkych = vsetky;
        this.pocetSpravnych = spravne;
        this.chcem = chcemm;    
        String path = "HiraganaObr/" + id + ".PNG"; //////////////////CESTA K FILU
        System.out.println("Idem nacitavat obrazok " + path);
        this.obrazokJP = new Image(path);
        System.out.println("Nacitaval som obrazok " + path);        
    }
    /**
     * Toto slovicko/pismeno bolo testovane, zvysim pocet vsetkych testovani a
     * zaznamenam ci bolo spravne.
     */
    public void aktualizujOJedno(boolean spravne){
        this.pocetVsetkych++;
        if (spravne){
            this.pocetSpravnych++;
        }
    }
    /**
     * Povie ci bol nacitany pre toto slovicko/pismeno (pre druhy jazyk) obrazok.
     */
    public boolean mameObr(){
        if (this.obrazokJP!=null){
            return true;
        }
        return false;
    }
    
    /**
     * Vrati id seba.
     * @return Id seba.
     */
    public int getId(){
        return this.id;
    }
    public String getSlovenskyString(){ //prvy jazyk
        return this.sk;
    }
    public String getJaponskyString(){ //druhy jazyk
        return this.jp;
    }
    public int getVsetky(){
        return this.pocetVsetkych;
    }
    public int getSpravne(){
        return this.pocetSpravnych;
    }
    /**
     * Vrati uspesnost uzivatela na tomto slovicku/pismene, aby mohol byt zoznam
     * vygenerovanych zistit, ktore slovicko/pismeno ide uzivatelovi najhorsie.
     */
    public double getPomerNespravneVsetky(){
        if (pocetVsetkych==0){
            return 0.0;
        }
        return ((1.0*this.pocetVsetkych)-this.pocetSpravnych)/this.pocetVsetkych;
    }
}
