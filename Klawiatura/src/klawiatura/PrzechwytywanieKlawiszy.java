/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klawiatura;

import java.util.ArrayList;

/**
 *
 * @author Jedrzej
 */

/** klasa odpowiedzialna za przechowywanie klawiszy, liczenie odległości i czasów przejścia między dwoma klawiszami */
public class PrzechwytywanieKlawiszy { 
    /** przecowywuje tablicę przejść */
    private ArrayList<Przejscie> przejscia;
    private Przejscie przejscie;
    private Timer timer;
    private OdleglosciMiedzyKlawiszami odlegloscMiedzyKlawiszami;
    private String[] listaDozwolonychZnakow;
    /** tworzy nową ArrayList dla obiektów Pzejście */
    public PrzechwytywanieKlawiszy() {
        przejscia = new ArrayList<Przejscie>(); 
        timer = new Timer();                    
        odlegloscMiedzyKlawiszami = new OdleglosciMiedzyKlawiszami(); 
        this.listaDozwolonychZnakow = new String[]{"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m"};
        
    }
    /** zapisuje wciśnięty znak i czas w którym został wciśnięty */
    public void zapiszPressed(String znak) {
        for (String listaDozwolonychZnakow1 : listaDozwolonychZnakow) {
            if (listaDozwolonychZnakow1.equals(znak)) {
                if (przejscie != null) {
                    przejscie.setDrugi(znak);
                    timer.setStop();
                    przejscie.setTime(timer.licz());
                    przejscia.add(przejscie);
                }
            }
        }
    }
    /** zapisuje puszczony klawisz i zapisuje czas, w którym został puszczony */
    public void zapiszRelased(String znak) { 
        for (String listaDozwolonychZnakow1 : listaDozwolonychZnakow) {
            if (listaDozwolonychZnakow1.equals(znak)) {
                przejscie = new Przejscie(znak);
                timer.setStart();
            }
        }
        
    }
    /** segreguje przejścia między klawiszami */
    public void segreguj() { 
        Segregator sort = new Segregator();
        przejscia = sort.sortuj(przejscia);
        for (Przejscie p1 : przejscia) {
            p1.addOdleglosc(odlegloscMiedzyKlawiszami.dajRoznice(p1.dajPierwszyKlawisz(), p1.dajDrugiKlawisz()));
            System.out.println("s1: " + p1.dajPierwszyKlawisz() + "  s2: " + p1.dajDrugiKlawisz() + " czas: " + p1.dajCzas() + "   odległość: " + p1.dajOdleglosc());
        }
    }
    
    
    /** klasa odpowiedzialna za liczenie czasu przejścia */
    private class Timer {

        private long start;
        private long stop;

        /** pobiera początkowy czas */
        public void setStart() {
            start = System.currentTimeMillis();
        }

        /** pobiera końcowy czas */
        public void setStop() {
            stop = System.currentTimeMillis();
        }

        /** liczy różnicę czasów */
        public long licz() {
            return stop - start;
        }

    }

    /** klasa odpowiedźialna za segregowanie i policzenie średnich czasów przejść */
    private class Segregator {

        private ArrayList<SredniaPrzejsc> tmp;
        
        /** tworzy nową ArrayList */
        public Segregator() {
            tmp = new ArrayList<SredniaPrzejsc>();
        }

        /** liczy czasy dla przejść  */
        public ArrayList<Przejscie> sortuj(ArrayList<Przejscie> a) {
            
            boolean czyNowePrzejscie = false;
            tmp.add(new SredniaPrzejsc(a.get(0).dajPierwszyKlawisz(), a.get(0).dajDrugiKlawisz(), a.get(0).dajCzas()));
            for (Przejscie a1 : a) {
                for (SredniaPrzejsc t1 : tmp) {
                    if (t1.sprawdz(a1.dajPierwszyKlawisz(), a1.dajDrugiKlawisz())) {
                        t1.addTime(a1.dajCzas());
                        czyNowePrzejscie = false;
                    }
                }
                if(czyNowePrzejscie)
                    tmp.add(new SredniaPrzejsc(a1.dajPierwszyKlawisz(), a1.dajDrugiKlawisz(), a1.dajCzas()));
                czyNowePrzejscie = true;
            }
            SredniaPrzejsc ttt = new SredniaPrzejsc();
            a = ttt.sortuj(tmp);
            return a;
        }

        /** liczy średnią takich samych przejść */
        private class SredniaPrzejsc {

            private String pierwszy;
            private String drugi;
            private ArrayList<Long> czasy;

            public SredniaPrzejsc() {
                czasy = new ArrayList<Long>();
            }
            
            
            public SredniaPrzejsc(String s1, String s2, long l) {
                czasy = new ArrayList<Long>();
                pierwszy = s1;
                drugi = s2;
                czasy.add(l);
            }

            /** sprawdza czy klawisze są takie same*/
            public boolean sprawdz(String s1, String s2) {
                return pierwszy.equals(s1) && drugi.equals(s2);
            }

            /** dodaje czas do klasy */
            public void addTime(long t) {
                czasy.add(t);
            }

            /** liczy średni czas dla jednego przejścia*/
            private long stednia() {
                long srednia = 0;
                for (Long l1 : czasy) {
                    srednia += l1;
                }
                return srednia /= czasy.size();
            }

            /** liczy czasy dla wszystkich przejść  */
            public ArrayList<Przejscie> sortuj(ArrayList<SredniaPrzejsc> t) {
                ArrayList<Przejscie> nowa = new ArrayList<Przejscie>();
                for (SredniaPrzejsc tmp : t) {
                    nowa.add(new Przejscie(tmp.pierwszy, tmp.drugi, tmp.stednia()));
                }
                return nowa;
            }
        }
    }

}
