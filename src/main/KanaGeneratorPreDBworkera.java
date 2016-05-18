/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Vector;

/**
 * Na komunikacie DBworkera s Generatorom.
 * @author Lenka
 */
class KanaGeneratorPreDBworkera {
    
    Generator gen = new Generator();
    public Vector<Vybrane> generuj(Vector<MyResults> Zoznam, int nahodnych, int nepouzivanych, int nespravnych) {
        return gen.generuj(Zoznam, nahodnych, nepouzivanych, nespravnych);
    }
    
    public Vector<Vybrane> generuj(Vector<MyResults> Zoznam, int pocet) {
        int nes = pocet/3;
        int nep = pocet/3;
        int nah = pocet - nes - nep;
        
        return gen.generuj(Zoznam, nah, nes, nep);
    }
}
