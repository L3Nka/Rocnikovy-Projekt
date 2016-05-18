/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Vector;

/**
 * Interface pre DBworkera, aby hra mohla posielat vysledky hry a pre
 * Nastavenia, aby mohli aktualizovat vyber slov.
 *
 * @author Lenka
 */
public interface AktualizacieOdpovedi {
    
    /**
     * Zaznamena vysledok tejto jednej otazky do vectora.
     * @param id
     * @param spravne
     */
    public void zaznamenajOdpoved(int id, boolean spravne);

    /**
     * Aktualizuje VYBER slovicok/pismen. Teda to ci uzivatel chce alebo nechce
     * dane slovicko/pismeno precvicovat.
     * VYKONAT PO ULOZENI NASTAVENI POUZIVATELOM.
     * @param id
     * @param chcem
     */
    public void aktualizujVyber(int id, int chcem);
    
}
