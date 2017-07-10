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
 *
 * klasa zawierająca grupę klawiszy i ilość ich wciśnięć potęcjalną dla pozycji
 * palca
 */
public class GrupaKlawiszy implements Comparable<GrupaKlawiszy> {

    private IloscWystapienKlawisza pierwszyRzad = null;
    private IloscWystapienKlawisza drugiRazad = null;
    private IloscWystapienKlawisza trzeciRazad = null;
    private int sumaPierwszychKlikniec;
    private int sumaDrugichKlikniec;
    private int numerGrupy;
    private float wartoscGrupy;

    public GrupaKlawiszy(int numer) {
        numerGrupy = numer;
    }

    /**
     * Uatawia główny klawisz w z pierwszego Rzędu
     *
     * @param klawisz
     */
    public void dodajKlawiszPierwszy(IloscWystapienKlawisza klawisz) {
        pierwszyRzad = klawisz;
    }

    /**
     * Uatawia klawisz w z drugiego Rzędu
     *
     * @param klawisz
     */
    public void dodajKlawiszDrugi(IloscWystapienKlawisza klawisz) {
        drugiRazad = klawisz;
    }

    /**
     * Uatawia główny klawisz w z trzeciego Rzędu
     *
     * @param klawisz
     */
    public void dodajKlawiszTrzeci(IloscWystapienKlawisza klawisz) {
        trzeciRazad = klawisz;
    }

    /**
     * Uatawia główny klawisz w z pierwszego Rzędu
     */
    public void sumujWystapienia() {
        sumaPierwszychKlikniec = 0;
        if (pierwszyRzad != null) {
            sumaPierwszychKlikniec += pierwszyRzad.dajIloscWystapien1Klawisza();
        }

        if (drugiRazad != null) {
            sumaPierwszychKlikniec += drugiRazad.dajIloscWystapien1Klawisza();
        }

        if (trzeciRazad != null) {
            sumaPierwszychKlikniec += trzeciRazad.dajIloscWystapien1Klawisza();
        }

        sumaDrugichKlikniec = 0;
        if (pierwszyRzad != null) {
            sumaDrugichKlikniec += pierwszyRzad.dajIloscWystapien2Klawisza();
        }

        if (drugiRazad != null) {
            sumaDrugichKlikniec += drugiRazad.dajIloscWystapien2Klawisza();
        }

        if (trzeciRazad != null) {
            sumaDrugichKlikniec += trzeciRazad.dajIloscWystapien2Klawisza();
        }
    }
    
    /**
     * Metoda obliczająca wartość grupy na podstawie liczby wciśnięć jako
     * pierwszy i drugi w przejściu w stosunku 1:2 oraz ilości klawiszy w grupie
     *
     */
    public void obliczWartoscGrupy() {
        wartoscGrupy = (float) (dajIloscPierwszychKlikniec() * 1 / 3) + (float) (dajIloscDrugichKlikniec() * 2 / 3);
        if (numerGrupy <= 6) {
            wartoscGrupy /= 3;
        } else {
            if (numerGrupy <= 8) {
                wartoscGrupy /= 2;
            }
        }
    }

    /**
     * zwraca ilość pierwszych kliknięć dla grupy
     *
     * @return
     */
    public int dajIloscPierwszychKlikniec() {
        return sumaPierwszychKlikniec;
    }

    /**
     * zwraca ilość drugich kliknięć dla grupy
     *
     * @return
     */
    public int dajIloscDrugichKlikniec() {
        return sumaDrugichKlikniec;
    }

    /**
     * zwraca numer grupy
     *
     * @return
     */
    public int dajNumerGrupy() {
        return numerGrupy;
    }
    
    /**
     * zwraca wartość grupy
     *
     * @return
     */
    public float dajWartoscGrupy() {
        return wartoscGrupy;
    }
    
    /**
     * zwraca najpopularniejszy Klawisz jako obiekt z ilością wciśnięć
     *
     * @return IloscWystapienKlawisza
     */
    public IloscWystapienKlawisza dajNajpopularniejszyKlawisz() {
        IloscWystapienKlawisza tmp = null;
        if(pierwszyRzad != null) {
            tmp = pierwszyRzad;
        }

        if (drugiRazad != null) {
            if (tmp != null) {
                if(drugiRazad.dajWartoscKlawisza() > tmp.dajWartoscKlawisza()) {
                    tmp = drugiRazad;
                }
            } else {
                tmp = drugiRazad;
            }
        }

        if (trzeciRazad != null) {
            if (tmp != null) {
                if(trzeciRazad.dajWartoscKlawisza() > tmp.dajWartoscKlawisza()) {
                    tmp = trzeciRazad;
                }
            } else {
                tmp = trzeciRazad;
            }
        }
        return tmp;
    }
    
    /**
     * zwraca drugi najpopularniejszy Klawisz jako obiekt z ilością wciśnięć
     *
     * @return IloscWystapienKlawisza
     */
    public IloscWystapienKlawisza dajDrugiNajpopularniejszyKlawisz() {
        IloscWystapienKlawisza tmp = null;
        IloscWystapienKlawisza najpopularniejszy = dajDrugiNajpopularniejszyKlawisz();
        if(pierwszyRzad != null) {
            if (!pierwszyRzad.dajZnak().equals(najpopularniejszy.dajZnak())) {
                 tmp = pierwszyRzad;
            }
        }
        
        if (drugiRazad != null) {
            if (tmp != null && !drugiRazad.dajZnak().equals(najpopularniejszy.dajZnak())) {
                if(drugiRazad.dajWartoscKlawisza() > tmp.dajWartoscKlawisza()) {
                    tmp = drugiRazad;
                }
            } else {
                tmp = drugiRazad;
            }
            
        }

        if (trzeciRazad != null) {
            if (tmp != null && !trzeciRazad.dajZnak().equals(najpopularniejszy.dajZnak())) {
                if(trzeciRazad.dajWartoscKlawisza() > tmp.dajWartoscKlawisza()) {
                    tmp = trzeciRazad;
                }
            } else {
                tmp = trzeciRazad;
            }
            
        }
        if (tmp == null) {
            return najpopularniejszy;
        } else {
            return tmp;
        }
    }
    
    
     @Override
    /** komparator do tablicy wartości grup, sortuje malejąco*/
    public int compareTo(GrupaKlawiszy o) {
        float porownaneWartosci = this.wartoscGrupy - o.wartoscGrupy;
        if(porownaneWartosci == 0.0) {
            return o.numerGrupy - this.numerGrupy; 
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
