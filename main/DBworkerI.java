/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Vector;

/**
 * Interface pre DBworkera, pre viac info vid DBworker.
 * @author Lenka
 * 
 */
public interface DBworkerI {
    /**
     * Nacita veci z pc suboru a vrati ich vo vectore.
     * @param idUzivatela
     * @param typ je typ pismen/slova - napr. HIRAGANA (ine napr. KATAKANA alebo
     * potom SLOVICKA-ZELENINA, SLOVICKA-ZAKLADNE,..) 
     * Obrazok nacita tiez. 
     * Robi sa len raz, pri zaciatku, teda KONTROLA ci sa vector rovna null, az 
     * potom zacina.
     * @return 
     */
    public Vector<MyResults> nacitaj(int idUzivatela, String typ);
    
    /**
     * Zo vstupneho vecotra vyberie tie, ktore maju zaznacene, ze ich uzivatel
     * chce cvicit.
     * Bude sa volat uplne na zaciatku, pri nacitani z DB a vzdy
     * pri ulozeni nastaveni v bloku Nastavenia
     * @param zoznam
     * @return 
     */
    public Vector<MyResults> vyberZvolene(Vector<MyResults> zoznam);

    /**
     * Vytvori novu kostru suboru v pc pre noveho uzivatetla. Pri zruseni
     * uzivatela, resp. pred vytvaranim noveho.
     * (String typ pre buducnost, keby
     * chce uzivatel restartovat len jednu cast a chce, aby ostatne jeho
     * vysledky ostali zachovane? ale potom by sa tazsie robili achievements
     * @param idUzivatela
     * @param typ
     */
    public void vygenerujNovuDB(int idUzivatela, String typ);

    /**
     * Ulozi do suboru aktualny vector.
     * Volame priebezne, keby nahodou program spadol.
     * Vector musi obsahovat uplne vsetky idcka z danej skupiny slovicko/pismen
     * Tato funkcia najskor pouzije comparator pre zotriedenie podla id-cka
     * @param vsetky
     * @param typ
     */
    public void ulozDoDB(Vector<MyResults> vsetky, String typ);   
    
    /**
     * Vrati vector vsetkych, a ak sa rovna null, tak ho vytvori.
     * @return 
     */
    public Vector<MyResults> getVsetky();

    /**
     * Vrati vector vybranych.
     * @return 
     */
    public Vector<MyResults> getZvolenePouzivatelom();
    
    /**
     * Vygeneruje generatorom slovicka/pismena vybrane pre tuto hru (zvycajne 10).
     * @param pocet
     * @return 
     */
    public Vector<Vybrane> vygeneruj(int pocet);
}
