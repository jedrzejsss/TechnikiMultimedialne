/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klawiatura;

/**
 *
 * @author Jedrzej
 */

/** klasa tworząca przejście między klawiszami, liczy czas */
    public class Przejscie { 

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
        
        /** zwraca pierwszy klawisz */
        public String dajPierwszyKlawisz() {
            return pierwszy;
        }
        
        /** zwraca drugi klawisz */
        public String dajDrugiKlawisz() {
            return drugi;
        }
        
        /** zwraca czas */
        public long dajCzas() {
            return czas;
        }
        
        /** zwraca odległość */
        public int dajOdleglosc() {
            return odleglosc;
        }
    }
