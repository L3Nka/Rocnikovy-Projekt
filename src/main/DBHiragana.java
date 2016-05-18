/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Vector;

/**
 * Konkretna implementacia abstraktnej triedy DBworker, tato je pre abecedu
 * Hiragana.
 *
 * @author Lenka
 */
public class DBHiragana extends DBworker {

    @Override
    public Vector<MyResults> nacitaj(int idUzivatela, String typ) {
        return super.nacitaj(idUzivatela, "HIRAGANA");
    }    

    @Override
    public Vector<MyResults> vyberZvolene(Vector<MyResults> zoznam) {
        return super.vyberZvolene(zoznam);
    }

    @Override
    public void vygenerujNovuDB(int idUzivatela, String typ) {
        super.vygenerujNovuDB(idUzivatela, "HIRAGANA");
    }

    @Override
    public void ulozDoDB(Vector<MyResults> vsetky, String typ) {
        super.ulozDoDB(this.vsetky, "HIRAGANA");
    }

    @Override
    public Vector<MyResults> getVsetky() {
        return super.getVsetky();
    }

    @Override
    public Vector<MyResults> getZvolenePouzivatelom() {
        return super.getZvolenePouzivatelom();
    }

    @Override
    public void zaznamenajOdpoved(int id, boolean spravne) {
        super.zaznamenajOdpoved(id, spravne);;
    }

    @Override
    public void aktualizujVyber(int id, int chcem) {
        super.aktualizujVyber(id, chcem);
    }
}
