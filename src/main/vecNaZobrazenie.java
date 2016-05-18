/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Collections;
import java.util.Vector;

/**
 * Vec na jedno zobrazenie, obsahuje otazku a mnozinu odpovedi.
 * @author Lenka
 */
class vecNaZobrazenie {
    String otazka; /// SPRAVIT ABY TOTO MOHOL BYT AJ OBRAZOK??
    Vector<MyResults> moznosti; // Vacsinou dlzky 4 /// SU STACI STRING/OBRAZOK
    int idSpravneho;
    /**
     * Rozhodi moznosti do nahodneho poradia a zaznaci, ktore je spravne.
     */
    public vecNaZobrazenie(String otazka, Vector<MyResults> m) {
        //System.err.println("TOTO JE NEDOKONCENE class vecNaZobrazenie");
        this.otazka=otazka;
        
        int spravneId = m.get(3).getId();
        Collections.shuffle(m);        
        for (int i=0;i<m.size();i++){
            if (m.get(i).id==spravneId){
                idSpravneho=i;
                break;
            }
        }
        this.moznosti = m;
    }
    
    public int idSpravneho(){
        return idSpravneho;
    }
    
}
