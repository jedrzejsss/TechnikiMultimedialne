/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klawiatura;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Jedrzej
 */

/** Klasa odpowiedzialna za pogrupowanie klawiszy */
public class Grupowanie {
    private long czas;
    private ArrayList<Przejscie> przejscia;
    private ArrayList<String> grupaPierwsza;
    private ArrayList<String> grupaDruga;
    private String[] wszystkieZnaki;
    
    public Grupowanie() {
        wszystkieZnaki = new String[]{"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m"};
    }
 
    /** odpowaida za pogrupowanie klawiszy*/
    public void grupuj(ArrayList<Przejscie> p) {
        przejscia = p;
        Collections.sort(przejscia);
        liczCzas();
        for(Przejscie p1 : przejscia) {
            if((p1.dajPierwszyKlawisz().equals("a") || p1.dajDrugiKlawisz().equals("a")) && p1.dajOdleglosc() < 4)
            System.out.println("1: " + p1.dajPierwszyKlawisz() + " 2: " + p1.dajDrugiKlawisz() + " czas: " + p1.dajCzas() + " odległość : " + p1.dajOdleglosc());
        }
    }
    
    /** ustawia maksymalny czas przejść dla grup */
    private void liczCzas() {
        for (Przejscie p1 : przejscia) {
            czas += p1.dajCzas();
        }
        czas /= przejscia.size();
    }
    
    

   private class WszystkieZnaki {
       public WszystkieZnaki() {
           
       }
       
   }
    
}
