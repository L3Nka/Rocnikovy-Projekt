/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Vector;

/**
 * Generator na vygenerovanie slovicok/pismen do prave spustenej hry. Generator
 * dava pozor zatial len nato, aby nevygeneroval medzi moznostami aj moznost
 * rovnaku s otazkou, ale moze sa stat, ze vygeneruje viac rovnkych moznosti.
 * Pozn. interface - keby sa rozhodneme doplnit aj generovanie podla toho, ktore
 * s ktorymi si uzivatel najviac myli
 *
 * @author Lenka
 */
public interface GeneratorI {
    
    /**
     * Vrati v zozname veci typu Vybrane, ktore vybral pre testovanie.
     * @param Zoznam - zoznam veci, z ktorych idem vybrat
     * @param nahodnych
     * @param nepouzivanych
     * @param nespravnych
     * @return
     */
    public Vector<Vybrane> generuj(Vector<MyResults> Zoznam, int nahodnych, int nepouzivanych, int nespravnych);
    
    /**
     * Vrati vector velkosti "pocet", slovicka vyberie pseudo nahodne.
     * @param pocet
     * @param Zoznam
     * @return
     */
    public Vector<Vybrane> generujNahodne(int pocet,Vector<MyResults> Zoznam);
    
    /**
     * Vrati vector velkosti "pocet", vyberie tie slovicka, ktore uzivatel 
     * najmenej-krat testoval.
     * @param pocet
     * @param Zoznam
     * @return
     */
    public Vector<Vybrane> generujMaloTestovane(int pocet,Vector<MyResults> Zoznam);
    
    /**
     * Vrati vector velkosti "pocet", vyberie tie slovicka, ktore uzivatel 
     * zatial najhorsie ovlada.
     * @param pocet 
     * @param Zoznam 
     * @return
     */
    public Vector<Vybrane> generujNespravne(int pocet,Vector<MyResults> Zoznam);
    
}
