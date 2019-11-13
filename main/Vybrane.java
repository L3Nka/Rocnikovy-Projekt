/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Vector;

/**
 * Toto vybral generator.
 * 
 * @param testovane je to ktore sa ma testovat, hlavne vybrane slovicko
 * @param dalsieMoznosti su moznosti, ktore vybral generator ako ine moznosti
 * pre konkretne testovanie ci uz nahodne alebo domyselne
 * @author Lenka
 */
class Vybrane {

    MyResults testovane;
    Vector<MyResults> dalsieMoznosti;
    
    public Vybrane(MyResults testovane, Vector<MyResults> dalsieMoznosti) {
        this.testovane = testovane;
        this.dalsieMoznosti = dalsieMoznosti;
    }
    /**
     * Vrati testovanu otazku.
     */
    public MyResults getHlavneSlovicko(){
        return testovane;
    }
    /**
     * Vrati vector moznosti, ktory este nie je rozhodeni, ale spravna odpoved
     * je posledna.
     */
    public Vector<MyResults> getMoznosti(){
        return dalsieMoznosti;
    }
}
