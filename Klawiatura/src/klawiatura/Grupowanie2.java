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
    private ArrayList<IloscWystapienKlawisza> znakiZ20NajszybszychPrzejsc;

    /**
     * odpowaida za pogrupowanie klawiszy
     */
    public void grupuj(ArrayList<Przejscie> p) {
        przejscia = wybierz20Najszybszych(p);
        znakiZ20NajszybszychPrzejsc = new ArrayList<>();
        OdleglosciMiedzyKlawiszami od = new OdleglosciMiedzyKlawiszami();
        for(Przejscie p1 : przejscia) {
            System.out.println("kl1: " + p1.dajPierwszyKlawisz() + " kl2: " + p1.dajDrugiKlawisz() + " czas: " + p1.dajCzas() + " odleglosc: " + p1.dajOdleglosc());
        }
        zlicz20Najszybszych();
        for (IloscWystapienKlawisza il: znakiZ20NajszybszychPrzejsc) {
            System.out.println(il);            
        }
    }

    /**
     * odpowaida za wybór 20 najmniejszych grup
     */
    private ArrayList<Przejscie> wybierz20Najszybszych(ArrayList<Przejscie> p) {
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
    
    /**
     * zlicza ilość trafień w najszybszej dwudziestce przejść
     */
    private void zlicz20Najszybszych() {
        zliczIloscWystapienPierwszego();
        zliczIloscWystapienDrugiego();
    }
    
    
    /**
     * zlicza ilość wystąpień danego klawisza jako drugi w przejściu
     */
    private void zliczIloscWystapienDrugiego() {
        boolean czyBylTenKlawisz;
        for (Przejscie p1 : przejscia) {
            czyBylTenKlawisz = false;
            for (IloscWystapienKlawisza il : znakiZ20NajszybszychPrzejsc) {
                if (il.dajZnak().equals(p1.dajDrugiKlawisz())) {
                    il.dodajWystapienie2Klawisza();
                    czyBylTenKlawisz = true;
                }
            }
            if (!czyBylTenKlawisz) {
                IloscWystapienKlawisza tmp = new IloscWystapienKlawisza(p1.dajDrugiKlawisz());
                tmp.dodajWystapienie2Klawisza();
                znakiZ20NajszybszychPrzejsc.add(tmp);
            }
        }
    }
    
    /**
     * zlicza ilość wystąpień danego klawisza jako pierwszy w przejściu
     */
    private void zliczIloscWystapienPierwszego() {
        boolean czyBylTenKlawisz;
        for (Przejscie p1 : przejscia) {
            czyBylTenKlawisz = false;
            for (IloscWystapienKlawisza il : znakiZ20NajszybszychPrzejsc) {
                if (il.dajZnak().equals(p1.dajPierwszyKlawisz())) {
                    il.dodajWystapienie1Klawisza();
                    czyBylTenKlawisz = true;
                }
            }
            if (!czyBylTenKlawisz) {
                IloscWystapienKlawisza tmp = new IloscWystapienKlawisza(p1.dajPierwszyKlawisz());
                tmp.dodajWystapienie1Klawisza();
                znakiZ20NajszybszychPrzejsc.add(tmp);
            }
        }
    }
}
