/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Vector;

/**
 * Umoznuje komunikaciu triedy Hra s triedou DBworker
 * 
 * @author Lenka
 */
class DBworkerPreHru {
    DBworker db;

    DBworkerPreHru(DBworker typdb) {
        db = typdb;
    }
    /**
     * Vypyta si zoznam slovicok/pismen v pocte "pocet", ktore bude testovat.
     */
    public Vector<Vybrane> vygeneruj(int pocet){
        //System.out.println("Idem generovat..");
        return db.vygeneruj(pocet);
    }
    /**
     * Po kazdej otazke zaznamena odpoved pouzivatela.
     */
    public void zaznamenajOdpoved(int id, boolean spravne) {
        //System.out.println("Idem zaznamenat odpoved "+ id + " " + spravne);
        db.zaznamenajOdpoved(id, spravne);
    }
    
    
}
