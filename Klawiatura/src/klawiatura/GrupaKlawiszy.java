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
 * klasa zawierająca grupę klawiszy i ilość ich wciśnięć potęcjalną dla pozycji palca
 */
public class GrupaKlawiszy {
    
    private IloscWystapienKlawisza pierwszyRzadGlowna;
    private IloscWystapienKlawisza pierwszyRzadBoczna;
    private IloscWystapienKlawisza drugiRazad;
    private IloscWystapienKlawisza trzeciRazad;
    private int sumaPierwszychKlikniec;
    private int sumaDrugichKlikniec;
    private int numerGrupy;
    
    public GrupaKlawiszy(int numer) {
        numerGrupy = numer;
    }
    
    /**
     *  Uatawia główny klawisz w z pierwszego Rzędu
     * @param klawisz 
     */
    public void dodajKlawiszPierwszyGlowna(IloscWystapienKlawisza klawisz) {
        pierwszyRzadGlowna = klawisz;
    }
    
    /**
     *  Uatawia boczny klawisz w z pierwszego Rzędu
     * @param klawisz 
     */
    public void dodajKlawiszPierwszyBoczna(IloscWystapienKlawisza klawisz) {
        pierwszyRzadBoczna = klawisz;
    }
    
    /**
     *  Uatawia klawisz w z drugiego Rzędu
     * @param klawisz 
     */
    public void dodajKlawiszDrugi(IloscWystapienKlawisza klawisz) {
        drugiRazad = klawisz;
    }
    
    /**
     *  Uatawia główny klawisz w z trzeciego Rzędu
     * @param klawisz 
     */
    public void dodajKlawiszTrzeci(IloscWystapienKlawisza klawisz) {
        trzeciRazad = klawisz;
    }
    
    /**
     *  Uatawia główny klawisz w z pierwszego Rzędu
     */
    public void sumujWystapienia() {
        sumaPierwszychKlikniec = 0;
        if(pierwszyRzadGlowna != null)
        sumaPierwszychKlikniec += pierwszyRzadGlowna.iloscWystapien1KlawiszaWParze;
        
        if(pierwszyRzadBoczna != null)
        sumaPierwszychKlikniec += pierwszyRzadBoczna.iloscWystapien1KlawiszaWParze;
        
        if(drugiRazad != null)
        sumaPierwszychKlikniec += drugiRazad.iloscWystapien1KlawiszaWParze;
        
        if(trzeciRazad != null)
        sumaPierwszychKlikniec += trzeciRazad.iloscWystapien1KlawiszaWParze;
        
        
        sumaDrugichKlikniec = 0;
        if(pierwszyRzadGlowna != null)
        sumaDrugichKlikniec += pierwszyRzadGlowna.iloscWystapien2KlawiszaWParze;
        
        if(pierwszyRzadBoczna != null)
        sumaDrugichKlikniec += pierwszyRzadBoczna.iloscWystapien2KlawiszaWParze;
        
        if(drugiRazad != null)
        sumaDrugichKlikniec += drugiRazad.iloscWystapien2KlawiszaWParze;
        
        if(trzeciRazad != null)
        sumaDrugichKlikniec += trzeciRazad.iloscWystapien2KlawiszaWParze;
    }
    
    /**
     *  zwraca ilość pierwszych kliknięć dla grupy
     * @return 
     */
    public int dajIloscPierwszychKlikniec() {
        return sumaPierwszychKlikniec;
    }
    
    /**
     *  zwraca ilość drugich kliknięć dla grupy
     * @return 
     */
    public int dajIloscDrugichKlikniec() {
        return sumaDrugichKlikniec;
    }
    
    /**
     *  zwraca numer grupy
     * @return 
     */
    public int dajNumerGrupy() {
        return numerGrupy;
    }
}
