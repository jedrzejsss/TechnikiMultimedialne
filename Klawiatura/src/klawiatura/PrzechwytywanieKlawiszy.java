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
    /** tworzy nową ArrayList dla obiektów Pzejście */
    public PrzechwytywanieKlawiszy() {
        przejscia = new ArrayList<Przejscie>(); 
        timer = new Timer();                    
        odlegloscMiedzyKlawiszami = new OdleglosciMiedzyKlawiszami(); 
        
    }
    /** zapisuje wciśnięty znak i czas w którym został wciśnięty */
    public void zapiszPressed(String znak) {
        if (przejscie != null) {
            przejscie.setDrugi(znak);
            timer.setStop();
            przejscie.setTime(timer.licz());
            przejscia.add(przejscie);
        }
    }
    /** zapisuje puszczony klawisz i zapisuje czas, w którym został puszczony */
    public void zapiszRelased(String znak) { 
        przejscie = new Przejscie(znak);
        timer.setStart();
    }
    /** rosnąco sortuje przejścia między klawiszami */
    public void sortuj() { 
        Sortowanie sort = new Sortowanie();
        przejscia = sort.sortuj(przejscia);
        for (Przejscie p1 : przejscia) {
            p1.addOdleglosc(odlegloscMiedzyKlawiszami.dajRoznice(p1.pierwszy, p1.drugi));
            System.out.println("s1: " + p1.pierwszy + "  s2: " + p1.drugi + " czas: " + p1.czas + "   odległość: " + p1.odleglosc);
        }
    }
    /** klasa tworząca przejście między klawiszami, liczy czas */
    private class Przejscie { 

        private String pierwszy; //przechowywuje pierwszy przycisk w przejściu
        private String drugi; //przechowywuje drugi przycisk w przejściu
        private long czas; //przechowywuje czas przejścia
        private int odleglosc; //przechowywuje odległość między klawiszami
        
        /** //tworzy przejście z tylko jednym klawiszem*/
        public Przejscie(String znak) { 
            this.pierwszy = znak;
        }

        /** tworzy obiekt przejście z dwoma klawiszami i czasem */
        public Przejscie(String s1, String s2, long tim) { 
            this.pierwszy = s1;
            this.drugi = s2;
            this.czas = tim;
        }
        
        /** ustawia drugi znak w przejściu. Pole "drugi" */
        public void setDrugi(String znak) { 
            this.drugi = znak;
        }
        
        /** ustawia pole "czas" */
        public void setTime(long tim) { 
            this.czas = tim;
        }
        /** dodaje odległość między klawiszami // dodaje odległość między klawiszami. Pole "odleglosc" */
        public void addOdleglosc(int _odleglosc) { 
            this.odleglosc = _odleglosc;
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

    /** klasa odpowiedźialna za posortowanie i policzenie średnich czasów przejść */
    private class Sortowanie {

        private ArrayList<SredniaPrzejsc> tmp;
        
        /** tworzy nową ArrayList */
        public Sortowanie() {
            tmp = new ArrayList<SredniaPrzejsc>();
        }

        /** liczy czasy dla przejść  */
        public ArrayList<Przejscie> sortuj(ArrayList<Przejscie> a) {
            
            boolean czyNowePrzejscie = false;
            tmp.add(new SredniaPrzejsc(a.get(0).pierwszy, a.get(0).drugi, a.get(0).czas));
            for (Przejscie a1 : a) {
                for (SredniaPrzejsc t1 : tmp) {
                    if (t1.sprawdz(a1.pierwszy, a1.drugi)) {
                        t1.addTime(a1.czas);
                        czyNowePrzejscie = false;
                    }
                }
                if(czyNowePrzejscie)
                    tmp.add(new SredniaPrzejsc(a1.pierwszy, a1.drugi, a1.czas));
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
