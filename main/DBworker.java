/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Vykonáva všetku prácu - načítava slovíčka/písmená zo súboru do pogramu,
 * ukladá ju tam, sprostredkúva generátor a komunikuje s "Hra".
 * @author Lenka
 */
public abstract class DBworker implements DBworkerI,AktualizacieOdpovedi {
    KanaGeneratorPreDBworkera generator = new KanaGeneratorPreDBworkera();
    /**
     * Vector zlozeny zo vsetkych slovicok/pismen z databazy zo zvoleneho druhu.
     * Pozor! Toto poradie nikdy nemenit, menit poradie sa smie len v "oznacene".
     */
    Vector<MyResults> vsetky = new Vector<MyResults>();
    /**
     * Vector zlozeny len z takych veci daneho typu, ktore maju od uzivatela
     * zadanu hodntou 'chcem'. Poradie vo vektore sa smie menit.
     */
    Vector<MyResults> oznacene = new Vector<MyResults>();
    
    int idUzivatela=-1;

    @Override
    public Vector<MyResults> nacitaj(int idUzivatela, String typ) {
        if (vsetky!=null || idUzivatela!=this.idUzivatela){
            //System.err.println("Tu je chyba..");
            vsetky = new Vector<MyResults>();
        if (typ.equals("HIRAGANA")){
            try {
                int uzSomKdeChcem = 0;
                //Scanner s = new Scanner(new File(idUzivatela+"H.txt"));
                //Scanner s = new Scanner(new File("hiraganadb.txt"));
                System.out.println("Idem nacitavat..");
                BufferedReader br = new BufferedReader(new FileReader(("hiraganadb.txt")));
                BufferedReader brHrac = new BufferedReader(new FileReader(((idUzivatela)+"H.txt")));
                //BufferedReader br2 = new BufferedReader(new FileReader(("1.txt")));
                String[] line = br.readLine().split(" ");
                while(line!=null || line.length != 0) {
                    //System.out.println("Line[0] "+ line[0] + " ");
                    if (uzSomKdeChcem > 0 && (line.length < 2)) { //uz som presiel, ktore som chcel nacitat, zacina dalsia sekcia, lebo line.lenght<2 cize tam je zase nejaky nazov
                        uzSomKdeChcem = 0;
                        break;
                    }
                    if(uzSomKdeChcem>0){
                        //System.out.println("Nacital som " + line[1]);
                        String[] hracoveHodnoty = brHrac.readLine().split(" ");
                        vsetky.add(new MyResults(Integer.parseInt(line[0]), line[1], line[1], Integer.parseInt(hracoveHodnoty[1]), Integer.parseInt(hracoveHodnoty[2]), Integer.parseInt(hracoveHodnoty[3])));
                        //System.out.println("Nacital "+ line[0] + " " + line[1]);
                    }
                    
                    if (line[0].equals("HIRAGANA")){
                        uzSomKdeChcem=1;
                    }
                    line = br.readLine().split(" ");
                }
            } catch (IOException ex) {
                Logger.getLogger(DBworker.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        }
        this.vyberZvolene(this.vsetky);
        return this.vsetky;
    }

    @Override
    public Vector<MyResults> vyberZvolene(Vector<MyResults> zoznam) {
        Vector<MyResults> ret = new Vector<MyResults>();
        for (int i=0;i<this.vsetky.size();i++){
            if(this.vsetky.get(i).chcem>0){
                ret.add(this.vsetky.get(i));
            }
        }
        this.oznacene=ret;
        return ret;
    }

    @Override
    public void vygenerujNovuDB(int idUzivatela, String typ) {
        try {
            PrintStream out = new PrintStream(idUzivatela + "H.txt");
            //   out.println("HIRAGANA");

            int pocetHiraganaZnakov = 46;   //pocet hiragan znakov
            //Zaznaci idcka znakov a nastavi prvym 10 znakom, ze ich chcem teraz precvicovat
            for (int i = 1; i <= pocetHiraganaZnakov; i++) {
                if (i < 10) {
                    out.println(i + " 0 0 1"); //Id_znaku Pocet_Spravnych Pocet_Vsetkych Zancka_True/False(ci ho chce)
                } else {
                    out.println(i + " 0 0 0"); //Id_znaku Pocet_Spravnych Pocet_Vsetkych Zancka_True/False(ci ho chce)
                }
            }
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DBworker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ulozDoDB(Vector<MyResults> vsetky, String typ) {
        try {
            System.out.println("UKLADAAAAAAAAAAAAAAAM " + idUzivatela+ "H.txt");
            PrintWriter out = new PrintWriter(new FileWriter(new File(idUzivatela+ "H.txt"), false));

            for (int i=0;i<this.vsetky.size();i++){
                MyResults mr = this.vsetky.get(i);
                System.out.println("Vsetky pri ukladani do DB " + mr.pocetVsetkych);
                //System.out.println("Ukl " + mr.id + " " + mr.pocetSpravnych + " " + mr.pocetVsetkych + " " + mr.chcem);
                out.println( mr.id + " " + mr.pocetVsetkych + " " + mr.pocetSpravnych + " " + mr.chcem);
            }
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DBworker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DBworker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Vector<MyResults> getVsetky() {
        return this.vsetky;
    }

    @Override
    public Vector<MyResults> getZvolenePouzivatelom() {
        return this.oznacene;
    }

    @Override
    public void zaznamenajOdpoved(int id, boolean spravne) {
        //Collections.sort(vsetky, Comparators.ById); //netreba, vsetky su v poradi, akom maju byt STALE
        System.out.println("Povodny get podla id vsetkych "+ vsetky.get(id).pocetVsetkych);
        vsetky.get(id).pocetVsetkych++;
        System.out.println("Povodny get podla id vsetkych " + vsetky.get(id).pocetVsetkych);
        if (spravne){
            vsetky.get(id).pocetSpravnych++;
        }
        System.out.println("Zaznamenal som odpoved " + id + " " + spravne);
    }

    @Override
    public void aktualizujVyber(int id, int chcem) {
        //Collections.sort(vsetky, Comparators.ById); //netreba, vsetky su v poradi, akom maju byt STALE
        vsetky.get(id).chcem = chcem;
    }
    
    @Override
    public Vector<Vybrane> vygeneruj(int pocet){
        
        return generator.generuj(this.oznacene, pocet);
    }
}
