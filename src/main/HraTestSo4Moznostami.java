/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Vector;

/**
 * Implementacia triedy HraNaMoznosti
 * @author Lenka
 */
public class HraTestSo4Moznostami extends HraNaMoznosti {
    vecNaZobrazenie zobrazujem;
    
    @Override
    public vecNaZobrazenie vytvorNaJednoZobrazenie() {
        System.out.println((otazky.get(tuSom).testovane.sk + " testovane "));
        vecNaZobrazenie vec = new vecNaZobrazenie(otazky.get(tuSom).testovane.sk, otazky.get(tuSom).getMoznosti());        
        spravneIdPoradia = vec.idSpravneho();
        uzVyhodnotil =0;
        zobrazujem=vec;
        return vec;
    }

    @Override
    public int vyhodnot(int poradieKliknuteho) {
        if (uzVyhodnotil == 0) {
            uzVyhodnotil=1;
            if (poradieKliknuteho == spravneIdPoradia) { //spravne
                db.zaznamenajOdpoved(zobrazujem.moznosti.get(spravneIdPoradia).id - 1, true);
                return 1;
            }
            //nespravne, pridam do vektora zlych odpovedi
            db.zaznamenajOdpoved(zobrazujem.moznosti.get(spravneIdPoradia).id - 1, false);
            db.zaznamenajOdpoved(zobrazujem.moznosti.get(poradieKliknuteho).id - 1, false);
            super.zleOdpovede.add(zobrazujem.moznosti.get(spravneIdPoradia));
            super.zleOdpovede.add(zobrazujem.moznosti.get(poradieKliknuteho));
            return 0;
        } else {
            return -1;  //Nevyhodnotil som, uz bolo vyhodnotene
        }
    }

    @Override
    public boolean mozeDalej() {
        tuSom++;
        if (tuSom<otazky.size()){
            return true;
        }
        return false;
    }    
    
    @Override
    public void InicializujNovuHru(DBworker typdb) {
        this.db = new DBworkerPreHru(typdb);
        otazky = new Vector<Vybrane>();
        this.otazky = db.vygeneruj(10);
        pocetOtazok=otazky.size();
        int tuSom=0;        
    }
    
    
}
