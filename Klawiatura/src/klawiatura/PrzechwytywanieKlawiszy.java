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
public class PrzechwytywanieKlawiszy { //klasa odpowiedzialna za przechowywanie klawiszy, liczenie odległości i czasów przejścia między dwoma klawiszami

    private ArrayList<Przejscie> przejscia;
    private Przejscie przejscie;
    private Timer timer;
    private OdleglosciMiedzyKlawiszami odlegloscMiedzyKlawiszami;

    public PrzechwytywanieKlawiszy() {
        przejscia = new ArrayList<Przejscie>(); //przecowywuje tablicę przejść
        timer = new Timer();                    //obiekt klasy Timer, służący do liczenia czasu przejść 
        odlegloscMiedzyKlawiszami = new OdleglosciMiedzyKlawiszami(); //obiekt Klasy OdleglosciMiedzyKlawiszami, służcej do obliczania odległości
        
    }

    public void zapiszPressed(String znak) {//zapisuje wciśnięty znak i czas w którym został wciśnięty
        if (przejscie != null) {
            przejscie.setDrugi(znak);
            timer.setStop();
            przejscie.setTime(timer.licz());
            przejscia.add(przejscie);
        }
    }

    public void zapiszRelased(String znak) { //zapisuje puszczony klawisz i zapisuje czas, w którym został puszczony
        przejscie = new Przejscie(znak);
        timer.setStart();
    }

    public void sortuj() { //rosnąco sortuje przejścia między klawiszami
        Sortowanie sort = new Sortowanie();
        przejscia = sort.sortuj(przejscia);
        for (Przejscie p1 : przejscia) {
            p1.addOdleglosc(odlegloscMiedzyKlawiszami.dajRoznice(p1.pierwszy, p1.drugi));
            System.out.println("s1: " + p1.pierwszy + "  s2: " + p1.drugi + " czas: " + p1.czas + "   odległość: " + p1.odleglosc);
        }
    }

    private class Przejscie { //klasa tworząca przejście między klawiszami, liczy czas

        private String pierwszy; //przechowywuje pierwszy przycisk w przejściu
        private String drugi; //przechowywuje drugi przycisk w przejściu
        private long czas; //przechowywuje czas przejścia
        private int odleglosc; //przechowywuje odległość między klawiszami

        public Przejscie(String znak) { //tworzy przejście z tylko jednym klawiszem
            this.pierwszy = znak;
        }

        public Przejscie(String s1, String s2, long tim) { //tworzy obiekt przejście z dwoma klawiszami i czasem
            this.pierwszy = s1;
            this.drugi = s2;
            this.czas = tim;
        }

        public void setDrugi(String znak) { //ustawia drugi znak w przejściu. Pole "drugi"
            this.drugi = znak;
        }

        public void setTime(long tim) { //ustawia pole "czas"
            this.czas = tim;
        }
        
        public void addOdleglosc(int _odleglosc) { //dodaje odległość między klawiszami // dodaje odległość między klawiszami. Pole "odleglosc"
            this.odleglosc = _odleglosc;
        }
    }

    private class Timer {

        private long start;
        private long stop;

        public void setStart() {
            start = System.currentTimeMillis();
        }

        public void setStop() {
            stop = System.currentTimeMillis();
        }

        public long licz() {
            return stop - start;
        }

    }

    private class Sortowanie {

        private ArrayList<Tymczasowa> tmp;

        public Sortowanie() {
            tmp = new ArrayList<Tymczasowa>();
        }

        public ArrayList<Przejscie> sortuj(ArrayList<Przejscie> a) {
            
            boolean czyNowePrzejscie = false;
            tmp.add(new Tymczasowa(a.get(0).pierwszy, a.get(0).drugi, a.get(0).czas));
            for (Przejscie a1 : a) {
                for (Tymczasowa t1 : tmp) {
                    if (t1.sprawdz(a1.pierwszy, a1.drugi)) {
                        t1.addTime(a1.czas);
                        czyNowePrzejscie = false;
                    }
                }
                if(czyNowePrzejscie)
                    tmp.add(new Tymczasowa(a1.pierwszy, a1.drugi, a1.czas));
                czyNowePrzejscie = true;
            }
            Tymczasowa ttt = new Tymczasowa();
            a = ttt.sortuj(tmp);
            return a;
        }

        private class Tymczasowa {

            private String pierwszy;
            private String drugi;
            private ArrayList<Long> czasy;

            public Tymczasowa() {
                czasy = new ArrayList<Long>();
            }
            public Tymczasowa(String s1, String s2, long l) {
                czasy = new ArrayList<Long>();
                pierwszy = s1;
                drugi = s2;
                czasy.add(l);
            }

            public boolean sprawdz(String s1, String s2) {
                return pierwszy.equals(s1) && drugi.equals(s2);
            }

            public void addTime(long t) {
                czasy.add(t);
            }

            private long stednia() {
                long srednia = 0;
                for (Long l1 : czasy) {
                    srednia += l1;
                }
                return srednia /= czasy.size();
            }

            public ArrayList<Przejscie> sortuj(ArrayList<Tymczasowa> t) {
                ArrayList<Przejscie> nowa = new ArrayList<Przejscie>();
                for (Tymczasowa tmp : t) {
                    nowa.add(new Przejscie(tmp.pierwszy, tmp.drugi, tmp.stednia()));
                }
                return nowa;
            }
        }
    }

}
