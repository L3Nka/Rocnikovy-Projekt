/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Vector;

/**
 * Rozsirenie abstraktnej triedy "Hra".
 * @author Lenka
 */
public abstract class HraNaMoznosti extends Hra{
    int tuSom; // tu som vo Vector<Vybrane>, (pri pytani dalsieho, robi ++)
    int spravneIdPoradia;
    int uzVyhodnotil;
    Vector<Vybrane> otazky = new Vector<Vybrane>();
    
    
    /**
     * Vytvori z Vectoru, ktore mu poskytne DBworker/(resp jeho interface na
     * komunikaciu) vec na zobrazenie, teda spravi otazku a doda zoznam moznosti,
     * ktore uz su v nahodnom poradi. Poradove id spravneho si ulozi, aby ho
     * mohol potom porovnat a vykonat tak zaznam odpovede.
     * @return 
     */
    public abstract vecNaZobrazenie vytvorNaJednoZobrazenie();
    
    /**
     * Povie hre ci hrac odpovedal spravne. Ak vrati true, tak vysvieti to
     * kliknute na zeleno, ak nie, tak na cerveno a pozrie si z tejto classy int
     * spravneIdPoradia a to vysvieti na zeleno.
     * Vrati -1 ak toto uz bolo vyhodnotene, a preto to nechcem vyhodnoti znovu.
     * @param poradieKliknuteho teda id toho, co oznacil hrac za spravne
     * @return 
     */
    public abstract int vyhodnot(int poradieKliknuteho);
    
    /**
     * Vola sa ak hrac odpovedal nespravne, ak vyhodnot vratil false. Vrati
     * poradie spravneho ako to rozhodil do tych x moznosti a tak moze hra
     * vysvietit, ktore bolo spravne.
     * @return 
     */
    public int getPoradieSpravneho(){
        return this.spravneIdPoradia;
    }
    
    /**
     * Vrati zle urcene pismena, ktore by sa mali zobrazit ako info pre hraca.
     * @return 
     */
    public Vector<MyResults> dajZleUrcenePismena(){
        return super.zleOdpovede;
    }
    
    /**
     * Volanie tejto funkcie sluzi nielen pre tuto funkciu, ked hrac buttonom
     * povie, ze chce dalsie, ale aj naopak pre pouzivatela, ked mu povie ci su
     * este dalsie otazky v tejto hre.
     * @return True-ak je dalsia otazka, False-ak je uz koniec a toto bolo posledne
     */
    public abstract boolean mozeDalej();
    
    /**
     * Nainicializuje si novu hru. Vypyta si vektor vybranych otazok, a uprace si.
     * @param typdb
     */
    public abstract void InicializujNovuHru(DBworker typdb);
    
}
