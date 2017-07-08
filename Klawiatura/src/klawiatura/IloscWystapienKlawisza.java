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
public class IloscWystapienKlawisza implements Comparable<IloscWystapienKlawisza> {
    public IloscWystapienKlawisza(String _znak) {
        znak = _znak;
        OdleglosciMiedzyKlawiszami od = new OdleglosciMiedzyKlawiszami();
        numerRzedu = od.dajRzad(znak);
        numerWRzedzie = od.dajPozycjeWRzedzie(znak);
    }

    String znak;
    private int iloscWystapien2KlawiszaWParze;
    private int iloscWystapien1KlawiszaWParze;
    private int numerRzedu;
    private int numerWRzedzie;
    private float wartoscKlawisza;

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
        obliczWartoscKlawisza();
    }
    
    /**
     * dodaje Wystąpienie drugiego klawisza w przejściu
     */
    public void dodajWystapienie2Klawisza() {
        iloscWystapien2KlawiszaWParze++;
        obliczWartoscKlawisza();
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
    
    /**
     * Metoda zwracająca numer klawisza w rzędzie (od lewej)
     */
    public float dajWartoscKlawisza() {
        return (iloscWystapien1KlawiszaWParze / 3) + (iloscWystapien2KlawiszaWParze * 2 / 3);
    }
    
    
    @Override
    public String toString() {
        return "znak: " + znak + " jako 1-szy klawisz: " + iloscWystapien1KlawiszaWParze + " jako 2-gi klawisz: " + iloscWystapien2KlawiszaWParze + " rząd: " + numerRzedu + " pozycja: " + numerWRzedzie + " wartosc: " + wartoscKlawisza;
    }
    
    
    public void obliczWartoscKlawisza() {
        wartoscKlawisza = (float) (dajIloscWystapien1Klawisza() * 1 / 3) + (float) (dajIloscWystapien2Klawisza() * 2 / 3);
    }
    
    @Override
    /** komparator do tablicy wartości grup malejąco*/
    public int compareTo(IloscWystapienKlawisza o) {
        obliczWartoscKlawisza();
        float porownaneWartosci = this.wartoscKlawisza - o.wartoscKlawisza;
        if(porownaneWartosci == 0.0) {
            return o.znak.compareTo(this.znak);  
        }
        else {
            if(porownaneWartosci < 0.0) {
                return  1;
            } else {
                return -1;
            }
        }
    }
}
