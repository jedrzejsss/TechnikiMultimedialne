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

/** klasa odpowiedzialna za liczenie odległości między klawiszami*/
public class OdleglosciMiedzyKlawiszami {
    private Rzad pierwszy;
    private Rzad drugi;
    private Rzad trzeci;
    
    /** tworzymy układ klawiatury "qwerty" */
    public OdleglosciMiedzyKlawiszami() { 
        String[] tmpPierwszy = {"q", "w", "e", "r" , "t", "y", "u", "i", "o", "p"};
        pierwszy = new Rzad(tmpPierwszy);
        String[] tmpDrugi = {"a", "s", "d", "f" , "g", "h", "j", "k", "l"};
        drugi = new Rzad(tmpDrugi);
        String[] tmpTrzeci = {"z", "x", "c", "v" , "b", "n", "m"};
        trzeci = new Rzad(tmpTrzeci);
    }
    /** daje różnicę odległości między klawiszami */
    public int dajRoznice(String a, String b){      
        Pozycja pozycjaPierwszego = ustalPozycje(a);
        Pozycja pozycjaDrugiego = ustalPozycje(b);
        return pozycjaPierwszego.dajRoznicePozycji(pozycjaDrugiego);
    }
    
    /** ustala pozycję znaku na klawiaturze. Rząd i numer */
    private Pozycja ustalPozycje(String znak) { 
        Pozycja p;
        int tmp = pierwszy.dajPozycję(znak);
        if(tmp != -1) {
            p = new Pozycja(1, tmp);
            return p;
        }
        else {
            tmp = drugi.dajPozycję(znak);
            if(tmp != -1) {
                p = new Pozycja(2, tmp);
            return p;
            } else {
                tmp = trzeci.dajPozycję(znak);
                p = new Pozycja(3, tmp);
                return p;
            }
        }
    }
    
    /** klasa definiująca rzedy klawiszy oraz oblicza różnicę między klawiszami */
    private class Rzad {
        private String[] klawisze;
        public Rzad(String[] s) {
            klawisze = s;
        }
        
        /** daje pozycję klawisza, gdy klawisza nie ma w tablicy zwraca -1 */
        public int dajPozycję(String k) { 
            for (int i = 0; i < klawisze.length; i++) {
                if(klawisze[i].equals(k)) {
                    return i;
                } 
            } 
            return -1;
        }
    }
    
    /** przechowuje numer rzędu i pozycję klawisza oraz daje różniceę między klawiszami */
    private class Pozycja { 
        public int rzad;
        public int numer;
        private Pozycja(int _rzad, int _numer) {
            rzad = _rzad;
            numer = _numer;
        }
        
        /** oblicza różnicę pozycji klawiszy */
        public int dajRoznicePozycji(Pozycja p) { 
            return Math.abs(this.rzad - p.rzad) + Math.abs(this.numer - p.numer);
        }
}
}
