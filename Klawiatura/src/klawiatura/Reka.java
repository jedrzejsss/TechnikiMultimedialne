/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klawiatura;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;


/**
 *
 * @author Jedrzej
 */

/**
 * Klasa odpowiedzialna za układ ręki
 */
public class Reka {
    private ArrayList<IloscWystapienKlawisza> lewa;
    private ArrayList<IloscWystapienKlawisza> prawa;
    ArrayList<GrupaKlawiszy> czteryNajpopularniejszeGrupy;
    ArrayList<GrupaKlawiszy> wszystkieGrupy;
    ArrayList<GrupaKlawiszy> czteryNajpopularniejszePodGrupyLewa;
    
    /**
     * Metoda używana gdy cztery najpopularniejsze grupy są w zasięgu rozpiętości jednej ręki
     * @param _czteryNajpopularniejszeGrupy
     */
    public void tworzJednaReke(ArrayList<GrupaKlawiszy> _czteryNajpopularniejszeGrupy) {
        lewa = new ArrayList<>();
        czteryNajpopularniejszeGrupy = _czteryNajpopularniejszeGrupy;
        Collections.sort(czteryNajpopularniejszeGrupy, (GrupaKlawiszy c1, GrupaKlawiszy c2) -> {return (c1.dajNumerGrupy() - c2.dajNumerGrupy());});
        for (GrupaKlawiszy gk : czteryNajpopularniejszeGrupy) {
            if (gk.dajWartoscGrupy() > 0) {
                lewa.add(gk.dajNajpopularniejszyKlawisz());
                System.out.println("jedna reka: " + gk.dajNajpopularniejszyKlawisz());
            } else {
                lewa.add(new IloscWystapienKlawisza(" "));
            }
        }
        String tmp = "";
        for (IloscWystapienKlawisza il : lewa) {
            tmp += il.dajZnak() + " ";
        }
        JOptionPane.showMessageDialog(null, "Prawdopodobnie trzymasz palce na klawiszach: " + tmp);
    }
    
    /**
     * Metoda używana gdy cztery najpopularniejsze grupy są poza zasięgiem jednej ręki 
     * @param _czteryNajpopularniejszeGrupy
     * @param _wszystkieGrupy
     */
    public void tworzDwieRece(ArrayList<GrupaKlawiszy> _czteryNajpopularniejszeGrupy, ArrayList<GrupaKlawiszy> _wszystkieGrupy) {
        lewa = new ArrayList<>();
        prawa = new ArrayList<>();
        czteryNajpopularniejszeGrupy = _czteryNajpopularniejszeGrupy;
        wszystkieGrupy = _wszystkieGrupy;
        Collections.sort(czteryNajpopularniejszeGrupy, (GrupaKlawiszy c1, GrupaKlawiszy c2) -> {return (c1.dajNumerGrupy() - c2.dajNumerGrupy());});
       
        tworzLewaReke();
        //JOptionPane.showMessageDialog(null, "Prawdopodobnie trzymasz palce na klawiszach: " + tmp);
    }
    
    /**
     * Metoda używana gdy cztery najpopularniejsze grupy są w zasięgu rozpiętości jednej ręki 
     */
    public void tworzLewaReke() {
        int max = dajMaxZakresPodgrupy(czteryNajpopularniejszeGrupy.get(0));
        System.out.println("maxccccccc: " + max);
        int min = dajMinZakresPodgrupy(max);
        System.out.println("asgdjasdkasdfk : " + min);
        wybierz4NajwiekszychWPodgrupie(max, min);
        
        
    }
    
    /**
     * Metoda używana aby określić maksymalny zakres podgrupy
     */
    public int dajMaxZakresPodgrupy(GrupaKlawiszy g) {
        int podstawa = g.dajNumerGrupy();
        if (podstawa < 6) {
            return podstawa + 3;
        } else {
            return 8;
        }
        
    }
    
    /**
     * Metoda używana aby określić minimalny zakres podgrupy
     * @param max
     * @return min
     */
    public int dajMinZakresPodgrupy(int max) {
        boolean znaleziono = false;
        int min = max - 3;
        for (GrupaKlawiszy gk : czteryNajpopularniejszeGrupy) {
            if (gk.dajNumerGrupy() <= max) {
                min = gk.dajNumerGrupy() - 3;
            }
        }
        if (min < 0) {
            min = 0;
        }
        return min;
    }
    
    /**
     * Metoda używana gdy cztery najpopularniejsze grupy są w zasięgu rozpiętości jednej ręki
     * @param _czteryNajpopularniejszeGrupy
     */
    private void wybierz4NajwiekszychWPodgrupie(int maxNumerPodgrupy, int minNumerPodgrupy) {
        czteryNajpopularniejszePodGrupyLewa = new ArrayList<>();
        System.out.println("dupamin: " + minNumerPodgrupy + " dupaMax: "  +maxNumerPodgrupy);
        for (int i = minNumerPodgrupy; i <= maxNumerPodgrupy; i++) {
            if (czteryNajpopularniejszePodGrupyLewa.size() <= 3) {
                czteryNajpopularniejszePodGrupyLewa.add(wszystkieGrupy.get(i));
            } else {
                Collections.sort(czteryNajpopularniejszePodGrupyLewa);
                if (wszystkieGrupy.get(i).dajWartoscGrupy() > czteryNajpopularniejszePodGrupyLewa.get(czteryNajpopularniejszePodGrupyLewa.size() - 1).dajWartoscGrupy()) {
                    czteryNajpopularniejszePodGrupyLewa.remove(czteryNajpopularniejszePodGrupyLewa.size() - 1);
                    czteryNajpopularniejszePodGrupyLewa.add(wszystkieGrupy.get(i));
                    czteryNajpopularniejszePodGrupyLewa = utnijNiepotrzebneGrupy(czteryNajpopularniejszePodGrupyLewa);
                } else {
                    if (wszystkieGrupy.get(i).dajWartoscGrupy() == czteryNajpopularniejszePodGrupyLewa.get(czteryNajpopularniejszePodGrupyLewa.size() - 1).dajWartoscGrupy()) {
                        czteryNajpopularniejszePodGrupyLewa.add(wszystkieGrupy.get(i));
                    }
                }
            }
        }
        for (GrupaKlawiszy gk : czteryNajpopularniejszePodGrupyLewa) {
            System.out.println("gkKurwa: " + gk.dajNajpopularniejszyKlawisz().dajZnak());
        }
    }
    
    /**
     * ucina niepotrzebne grupy jeśli jest ich więcej niż 5 i w liście nie ma
     * kilku grup o najmniejszej wartości
     */
    private ArrayList<GrupaKlawiszy> utnijNiepotrzebneGrupy(ArrayList<GrupaKlawiszy> czteryNajpopularniejsze) {
        Collections.sort(czteryNajpopularniejsze);

        boolean poprawnaTablica = false;
        while (!poprawnaTablica && czteryNajpopularniejsze.size() > 4) {
            if (czteryNajpopularniejsze.get(3).dajWartoscGrupy() == czteryNajpopularniejsze.get(czteryNajpopularniejsze.size() - 1).dajWartoscGrupy()) {
                poprawnaTablica = true;
            } else {
                czteryNajpopularniejsze.remove(czteryNajpopularniejsze.size() - 1);
            }
        }
        return czteryNajpopularniejsze;
    }
    
}
