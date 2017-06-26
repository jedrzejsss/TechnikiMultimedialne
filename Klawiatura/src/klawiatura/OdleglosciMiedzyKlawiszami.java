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
public class OdleglosciMiedzyKlawiszami {
    private Rzad pierwszy;
    private Rzad drugi;
    private Rzad trzeci;
    
    public OdleglosciMiedzyKlawiszami() { //tworzymy układ klawiatury "qwerty"
        String[] tmpPierwszy = {"q", "w", "e", "r" , "t", "y", "u", "i", "o", "p"};
        pierwszy = new Rzad(tmpPierwszy);
        String[] tmpDrugi = {"a", "s", "d", "f" , "g", "h", "j", "k", "l"};
        drugi = new Rzad(tmpDrugi);
        String[] tmpTrzeci = {"z", "x", "c", "v" , "b", "n", "m"};
        trzeci = new Rzad(tmpTrzeci);
    }
    
    public int dajRoznice(String a, String b){      //daje różnicę odległości między klawiszami
        Pozycja pozycjaPierwszego = ustalPozycje(a);
        Pozycja pozycjaDrugiego = ustalPozycje(b);
        return pozycjaPierwszego.dajRoznicePozycji(pozycjaDrugiego);
    }
    
    
    private Pozycja ustalPozycje(String znak) { // ustala pozycję znaku na klawiaturze. Rząd i numer
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
    
    
    private class Rzad {
        private String[] klawisze;
        public Rzad(String[] s) {
            klawisze = s;
        }
        
        public int dajPozycję(String k) { //daje pozycję klawisza, gdy klawisza nie ma w tablicy zwraca -1
            for (int i = 0; i < klawisze.length; i++) {
                if(klawisze[i].equals(k)) {
                    return i;
                } 
            } 
            return -1;
        }
    }
    private class Pozycja { //przechowuje numer rzędu i pozycję klawisza oraz daje różniceę między klawiszami
        public int rzad;
        public int numer;
        private Pozycja(int _rzad, int _numer) {
            rzad = _rzad;
            numer = _numer;
        }
        
        public int dajRoznicePozycji(Pozycja p) { //oblicza różnicę pozycji klawiszy
            return Math.abs(this.rzad - p.rzad) + Math.abs(this.numer - p.numer);
        }
}
}
