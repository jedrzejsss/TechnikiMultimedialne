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
    private ArrayList<GrupaKlawiszy> czteryONajwieksyejWartosci;
    private Reka rece;

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
        czteryONajwieksyejWartosci = new ArrayList<>();
        wybierz4Najwiekszych();
        for (GrupaKlawiszy gk : czteryONajwieksyejWartosci) {
            System.out.println("numer: " + gk.dajNumerGrupy() + " wartosc: " + gk.dajWartoscGrupy() + " ilosc1: " + gk.dajIloscPierwszychKlikniec() + " ilosc2: " + gk.dajIloscDrugichKlikniec());
        }
        uzupelnijGrupy();
    }

    /**
     * wybiera 4 grupy z największą liczbą wciśniętych klawiszy
     */
    private void wybierz4Najwiekszych() {
        for (GrupaKlawiszy gk : grupyKlawiszy) {
            if (czteryONajwieksyejWartosci.size() <= 3) {
                czteryONajwieksyejWartosci.add(gk);
            } else {
                Collections.sort(czteryONajwieksyejWartosci);
                if (gk.dajWartoscGrupy() > czteryONajwieksyejWartosci.get(czteryONajwieksyejWartosci.size() - 1).dajWartoscGrupy()) {
                    czteryONajwieksyejWartosci.remove(czteryONajwieksyejWartosci.size() - 1);
                    czteryONajwieksyejWartosci.add(gk);
                    utnijNiepotrzebneGrupy();
                } else {
                    if (gk.dajWartoscGrupy() == czteryONajwieksyejWartosci.get(czteryONajwieksyejWartosci.size() - 1).dajWartoscGrupy()) {
                        czteryONajwieksyejWartosci.add(gk);
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
        Collections.sort(czteryONajwieksyejWartosci);

        boolean poprawnaTablica = false;
        while (!poprawnaTablica && czteryONajwieksyejWartosci.size() > 4) {
            if (czteryONajwieksyejWartosci.get(3).dajWartoscGrupy() == czteryONajwieksyejWartosci.get(czteryONajwieksyejWartosci.size() - 1).dajWartoscGrupy()) {
                poprawnaTablica = true;
            } else {
                czteryONajwieksyejWartosci.remove(czteryONajwieksyejWartosci.size() - 1);
            }
        }
    }

    /**
     * popasowywuje słabsze grupy do czterech największych
     */
    private void uzupelnijGrupy() {
        if (dajNumerGrupyONajwiekszymNumerze() - dajNumerGrupyONajmniejszymNumerze() > 4) {
            dwieRece();
        } else {
            jednaReka();
        }

    }

    public void jednaReka() {
        for (GrupaKlawiszy gk : czteryONajwieksyejWartosci) {

            System.out.println("najw: " + gk.dajNajpopularniejszyKlawisz().dajZnak() + " wartość: " + gk.dajNajpopularniejszyKlawisz().dajWartoscKlawisza());
        }
        rece = new Reka();
        rece.tworzJednaReke(czteryONajwieksyejWartosci);
    }

    public void dwieRece() {
        for (GrupaKlawiszy gk : czteryONajwieksyejWartosci) {

            System.out.println("najw: " + gk.dajNajpopularniejszyKlawisz().dajZnak() + " wartość: " + gk.dajNajpopularniejszyKlawisz().dajWartoscKlawisza());
        }
    }

    /**
     * zwraca numer grupy o najmniejszym numerze
     *
     * @return numerGrupy
     */
    public int dajNumerGrupyONajmniejszymNumerze() {
        GrupaKlawiszy tmp = czteryONajwieksyejWartosci.get(0);
        for (GrupaKlawiszy gk : czteryONajwieksyejWartosci) {
            if (gk.dajNumerGrupy() < tmp.dajNumerGrupy()) {
                tmp = gk;
            }
        }
        return tmp.dajNumerGrupy();
    }

    /**
     * zwraca numer grupy o największym numerze grupy
     *
     * @return numerGrupy
     */
    public int dajNumerGrupyONajwiekszymNumerze() {
        GrupaKlawiszy tmp = czteryONajwieksyejWartosci.get(0);
        for (GrupaKlawiszy gk : czteryONajwieksyejWartosci) {
            if (gk.dajNumerGrupy() > tmp.dajNumerGrupy()) {
                tmp = gk;
            }
        }
        return tmp.dajNumerGrupy();
    }
}
