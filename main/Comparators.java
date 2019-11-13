/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Comparator;

/**
 * Vlastne comparatory napr pre porovnavanie veci typu MyResults.
 * @author Lenka
 */
public class Comparators {

    /**
     * Pouzije sa pred ukladanim do DB.
     */
    static class ById implements Comparator<MyResults> {

        @Override
        public int compare(MyResults o1, MyResults o2) {
            return (o1.getId()) - (o2.getId());
        }
    }
    
    /**
     * Pozije sa pre generovanie najmenej pouzitych.
     */
    static class ByNepouzivane implements Comparator<MyResults> {

        @Override
        public int compare(MyResults o1, MyResults o2) {
            return (o1.getVsetky()) - (o2.getVsetky());
        }
    }
    
    /**
     * Pozije sa pre generovanie tych, s ktorymi mal uzivatel najviac problemy,
     * ktore vie najmenej.
     */
    static class ByNeuspesne implements Comparator<MyResults> {

        @Override
        public int compare(MyResults o1, MyResults o2) {
            //System.err.println(" Toto porovnavanie bolo mozno vykonane naopak // class Comparators.java riadok 46");
            return Double.compare((o2.getPomerNespravneVsetky()), (o1.getPomerNespravneVsetky()));
        }
    }

}


