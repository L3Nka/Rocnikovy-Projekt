/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Collections;
import java.util.Random;
import java.util.Vector;

/**
 * GENERATOR pre pismena/abecedu. Pre slovicka bude asi inak premylseny
 * generator. Minimalne tu budu urcite ine default hodnoty ako pri slovickach
 * pre pocty
 *
 * @author Lenka
 */
public class Generator implements GeneratorI {

    @Override
    public Vector<Vybrane> generuj(Vector<MyResults> Zoznam, int nahodnych, int nepouzivanych, int nespravnych) {
        Vector<Vybrane> v = this.generujNahodne(nahodnych, Zoznam);
        Vector<Vybrane> v1 = this.generujMaloTestovane(nepouzivanych, Zoznam);
        for (int i = 0; i < v1.size(); i++) {
            v.add(v1.get(i));
        }
        v1 = this.generujNespravne(nespravnych, Zoznam);
        for (int i = 0; i < v1.size(); i++) {
            v.add(v1.get(i));
        }
        return v;
    }

    @Override
    public Vector<Vybrane> generujNahodne(int pocet, Vector<MyResults> Zoznam) {
        Vector<Vybrane> ret = new Vector<Vybrane>();
        System.out.println("GENERUJEM " + Zoznam.size());
        for (int i = 0; i < pocet; i++) {
            //vygenerovat 3 navzajom rozne cisla rozne od i 
            Random rand = new Random();
            int nahodne = rand.nextInt(Zoznam.size());
            
            Vector<MyResults> moznosti = new Vector<MyResults>();
            while (moznosti.size() < 3) {
                int n = nahodne;
                while ( nahodne == n) {
                    n = rand.nextInt(Zoznam.size());
                }
                System.out.println("Vybral som z preusporiadaneho vectora" + n + " to je v jeho id " + Zoznam.get(n).getId());
                moznosti.add(Zoznam.get(n));
            }
            System.out.println("To co idem skusat " + Zoznam.get(nahodne).id);
            moznosti.add(Zoznam.get(nahodne));
            System.out.println("toto boli moznosti pre nahodne zvolene " + nahodne + " to je v jeho id " + Zoznam.get(nahodne).getId());
            ret.add(new Vybrane(Zoznam.get(nahodne), moznosti));
        }
        return ret;
        
        
    }

    @Override
    public Vector<Vybrane> generujMaloTestovane(int pocet, Vector<MyResults> Zoznam) {
        Collections.sort(Zoznam, new Comparators.ByNepouzivane());
        Vector<Vybrane> ret = new Vector<Vybrane>();
        for (int i = 0; i < pocet; i++) {
            //vygenerovat 3 navzajom rozne cisla rozne od i 
            //int zakazane = Zoznam.get(i).id;

            Random rand = new Random();
            //50 is the maximum and the 1 is our minimum 

            Vector<MyResults> moznosti = new Vector<MyResults>();
            while (moznosti.size() < 3) {
                int n = i;
                while (i == n) {
                    n = rand.nextInt(Zoznam.size());
                }
                System.out.println("Vybral som z preusporiadaneho vectora" + n + " to je v jeho id " + Zoznam.get(n).getId());
                moznosti.add(Zoznam.get(n));
            }
            System.out.println("To co idem skusat " + i + " tu to bolo zle " + Zoznam.get(i).id);
            moznosti.add(Zoznam.get(i));
            ret.add(new Vybrane(Zoznam.get(i), moznosti));
        }
        return ret;
    }

    @Override
    public Vector<Vybrane> generujNespravne(int pocet, Vector<MyResults> Zoznam) {
        Collections.sort(Zoznam, new Comparators.ByNeuspesne());
        Vector<Vybrane> ret = new Vector<Vybrane>();
        for (int i = 0; i < pocet; i++) {
            int zakazane = Zoznam.get(i).id;
            Random rand = new Random();

            Vector<MyResults> moznosti = new Vector<MyResults>();
            while (moznosti.size() < 3) {
                int n = i;
                while (i == n) {
                    n = rand.nextInt(Zoznam.size());
                }
                System.out.println("Vybral som z preusporiadaneho vectora" + n + " to je v jeho id " + Zoznam.get(n).getId());
                moznosti.add(Zoznam.get(n));
            }
            moznosti.add(Zoznam.get(i));
            System.out.println("To co idem skusat " + Zoznam.get(i).id);
            ret.add(new Vybrane(Zoznam.get(i), moznosti));
        }
        return ret;
    }
}
