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
public class PrzypisanieDoPalcow {

    private ArrayList<GrupaKlawiszy> grupyKlawiszy;
    private ArrayList<GrupaKlawiszy> piecONajwieksyejWartosci;

    /**
     * Uatawia listę grup klawiszy
     */
    public void ustawGrupeKlawiszy(ArrayList<GrupaKlawiszy> gk) {
        grupyKlawiszy = gk;
    }
    
    /**
     * test
     */
    public void test() {
        piecONajwieksyejWartosci = new ArrayList<>();
        wybierz5Najwiekszych();
        for (GrupaKlawiszy gk : piecONajwieksyejWartosci) {
            System.out.println("numer: " + gk.dajNumerGrupy() + " wartosc: " + gk.dajWartoscGrupy() + " ilosc1: " + gk.dajIloscPierwszychKlikniec() + " ilosc2: " + gk.dajIloscDrugichKlikniec());
        }
    }
    
    

    /**
     * wybiera 4 grupy z największą liczbą wciśniętych klawiszy
     */
    private void wybierz4Najwiekszych() {
        for (GrupaKlawiszy gk : grupyKlawiszy) {
            if (piecONajwieksyejWartosci.size() <= 3) {
                piecONajwieksyejWartosci.add(gk);
            } else {
                Collections.sort(piecONajwieksyejWartosci);
                if (gk.dajWartoscGrupy() > piecONajwieksyejWartosci.get(piecONajwieksyejWartosci.size() - 1).dajWartoscGrupy()) {
                    piecONajwieksyejWartosci.remove(piecONajwieksyejWartosci.size() - 1);
                    piecONajwieksyejWartosci.add(gk);
                    utnijNiepotrzebneGrupy();
                } else {
                    if (gk.dajWartoscGrupy() == piecONajwieksyejWartosci.get(piecONajwieksyejWartosci.size() - 1).dajWartoscGrupy()) {
                        piecONajwieksyejWartosci.add(gk);
                    }
                }
            }
        }

    }

    /**
     * ucina niepotrzebne grupy jeśli jest ich więcej niż 5 i w liście nie ma
     * kilku grup o najmniejszej wartości
     */
    private void utnijNiepotrzebneGrupy() {
        Collections.sort(piecONajwieksyejWartosci);

        boolean poprawnaTablica = false;
        while(!poprawnaTablica && piecONajwieksyejWartosci.size() > 4) {
            if(piecONajwieksyejWartosci.get(3).dajWartoscGrupy() == piecONajwieksyejWartosci.get(piecONajwieksyejWartosci.size() - 1).dajWartoscGrupy()) {
                poprawnaTablica = true;
            } else {
                piecONajwieksyejWartosci.remove(piecONajwieksyejWartosci.size() - 1);            }
        }
    }
}
