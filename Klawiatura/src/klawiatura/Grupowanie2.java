/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klawiatura;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

/**
 *
 * @author Jedrzej
 */
public class Grupowanie2 {

    private ArrayList<Przejscie> przejscia;

    /**
     * odpowaida za pogrupowanie klawiszy
     */
    public void grupuj(ArrayList<Przejscie> p) {
        przejscia = wybierz12Najszybszych(p);
        for(Przejscie p1 : przejscia) {
            System.out.println("kl1: " + p1.dajPierwszyKlawisz() + " kl2: " + p1.dajDrugiKlawisz() + " czas: " + p1.dajCzas() + " odleglosc: " + p1.dajOdleglosc());
        }
    }

    /**
     * odpowaida za wybór 12 najmniejszych grup
     */
    private ArrayList<Przejscie> wybierz12Najszybszych(ArrayList<Przejscie> p) {
        ArrayList<Przejscie> tmp = new ArrayList<>();
        Collections.sort(p);
        if (p.size() < 20) {
            JOptionPane.showMessageDialog(null, "Wpisano za małą liczbę znaków");
        } else {
            for (int i = 0; i < 20; i++) {
                tmp.add(p.get(i));
            }
        }
        return tmp;
    }
}
