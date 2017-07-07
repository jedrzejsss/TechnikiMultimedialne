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
/**
 * Zlicza i przechowuje ilość wystąpień klawisza na liście
 */
public class IloscWystapienKlawisza {
    public IloscWystapienKlawisza(String _znak) {
        znak = _znak;
        OdleglosciMiedzyKlawiszami od = new OdleglosciMiedzyKlawiszami();
        numerRzedu = od.dajRzad(znak);
        numerWRzedzie = od.dajPozycjeWRzedzie(znak);
    }

    String znak;
    int iloscWystapien2KlawiszaWParze;
    int iloscWystapien1KlawiszaWParze;
    int numerRzedu;
    int numerWRzedzie;

    /**
     * Zlicza i przechowuje ilość wystąpień klawisza na liście
     * 
     */
    public void ustawZnak(String _znak) {
        znak = _znak;
    }
    
    /**
     * dodaje Wystąpienie pierwszego klawisza w przejściu
     */
    public void dodajWystapienie1Klawisza() {
        iloscWystapien1KlawiszaWParze++;
    }
    
    /**
     * dodaje Wystąpienie drugiego klawisza w przejściu
     */
    public void dodajWystapienie2Klawisza() {
        iloscWystapien2KlawiszaWParze++;
    }
    
    /**
     * Metoda zwracająca zmnak klawisza
     */
    public String dajZnak() {
        return znak;
    }
    
    /**
     * Metoda zwracająca ilość wystąpień pierwszego klawisza w przejściu
     */
    public int dajIloscWystapien1Klawisza() {
        return iloscWystapien1KlawiszaWParze;
    }
    
    /**
     * Metoda zwracająca ilość wystąpień drugiego klawisza w przejściu
     */
    public int dajIloscWystapien2Klawisza() {
        return iloscWystapien2KlawiszaWParze;
    }
    
    /**
     * Metoda zwracająca numer rzędu klawisza
     */
    public int dajRzadKlawisza() {
        return numerRzedu;
    }
    
    /**
     * Metoda zwracająca numer klawisza w rzędzie (od lewej)
     */
    public int dajNumerWRzedzie() {
        return numerWRzedzie;
    }
    
    @Override
    public String toString() {
        return "znak: " + znak + " jako 1-szy klawisz: " + iloscWystapien1KlawiszaWParze + " jako 2-gi klawisz: " + iloscWystapien2KlawiszaWParze + " rząd: " + numerRzedu + " pozycja: " + numerWRzedzie;
    }
}
