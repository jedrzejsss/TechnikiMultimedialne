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
/**
 * Klasa odpowiedzialna za pogrupowanie klawiszy
 */
public class Grupowanie {

    private long czas;
    private ArrayList<Przejscie> przejscia;
    private ArrayList<String> grupaPierwsza;
    private ArrayList<String> grupaDruga;
    private ArrayList<SredniCzasNacisniecia> srednieCzasy;
    private ArrayList<SredniCzasNacisniecia> srednieCzasyOdPuszczenia;
    
    public Grupowanie() {
        srednieCzasy = new ArrayList<>();
        srednieCzasyOdPuszczenia = new ArrayList<>();
    }

    /**
     * odpowaida za pogrupowanie klawiszy
     */
    public void grupuj(ArrayList<Przejscie> p) {
        przejscia = p;
        
        Collections.sort(p);
        for(Przejscie p1: przejscia){
            System.out.println("kl1: " + p1.dajPierwszyKlawisz() + " kl2: " + p1.dajDrugiKlawisz() + " czas: " + p1.dajCzas());
        }

        zrobTabliceSrednichCzasowDoWcisniecia();
        Collections.sort(srednieCzasy);
        for (SredniCzasNacisniecia sr : srednieCzasy) {
            System.out.println("znak: " + sr.dajSwojZnak() + " czas: " + sr.dajSrednia());
        }
        
        zrobTabliceSrednichCzasowOdJegoPuszczenia();
        System.out.println("---------------------------------------------");
        for (SredniCzasNacisniecia sr : srednieCzasyOdPuszczenia) {
            System.out.println("znak: " + sr.dajSwojZnak() + " czas: " + sr.dajSrednia());
        }

        tworzGrupe();

        for (String s1 : grupaPierwsza) {
            System.out.println(s1);
        }
    }

    /**
     * tworzy tablicę wciśnięć dla każdego klawisza do jego wciśnięcia
     */
    private void zrobTabliceSrednichCzasowDoWcisniecia() {
        boolean czyBylTenKlawisz;
        for (Przejscie p1 : przejscia) {
            czyBylTenKlawisz = false;
            for (SredniCzasNacisniecia sr : srednieCzasy) {
                if (sr.dajSwojZnak().equals(p1.dajDrugiKlawisz())) {
                    sr.dodajCzas(p1.dajCzas());
                    czyBylTenKlawisz = true;
                }
            }
            if (!czyBylTenKlawisz) {
                srednieCzasy.add(new SredniCzasNacisniecia(p1.dajDrugiKlawisz(), p1.dajCzas()));
            }
        }
    }
    
    
    /**
     * tworzy tablicę wciśnięć dla każdego klawisza od jego puszczenia
     */
    private void zrobTabliceSrednichCzasowOdJegoPuszczenia() {
        boolean czyBylTenKlawisz;
        for (Przejscie p1 : przejscia) {
            czyBylTenKlawisz = false;
            for (SredniCzasNacisniecia sr : srednieCzasyOdPuszczenia) {
                if (sr.dajSwojZnak().equals(p1.dajDrugiKlawisz())) {
                    sr.dodajCzas(p1.dajCzas());
                    czyBylTenKlawisz = true;
                }
            }
            if (!czyBylTenKlawisz) {
                srednieCzasyOdPuszczenia.add(new SredniCzasNacisniecia(p1.dajDrugiKlawisz(), p1.dajCzas()));
            }
        }
    }

    private void tworzGrupe() {
        OdleglosciMiedzyKlawiszami odl = new OdleglosciMiedzyKlawiszami();
        ArrayList<SredniCzasNacisniecia> tmp = srednieCzasy;
        grupaPierwsza = new ArrayList<>();
        for (SredniCzasNacisniecia sr : tmp) {
            boolean czyPasuje = true;
            if (grupaPierwsza.isEmpty()) {
                grupaPierwsza.add(sr.dajSwojZnak());
            } else {
                for (String s1 : grupaPierwsza) {
                    if (!(odl.dajRoznice(s1, sr.dajSwojZnak()) < 4 && grupaPierwsza.size() <= 5 && odl.dajPozycjeWRzedzie(s1) != odl.dajPozycjeWRzedzie(sr.dajSwojZnak()) && Math.abs(odl.dajRzad(s1)- odl.dajRzad(sr.dajSwojZnak())) <=1)) {
                        czyPasuje = false;
                    }
                }
                if (czyPasuje) {
                    grupaPierwsza.add(sr.dajSwojZnak());
                    //tmp.remove(sr);
                    System.out.println("cos");
                }

            }
        }
    }

}
