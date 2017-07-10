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
    
    /**
     * Metoda używana gdy cztery najpopularniejsze grupy są w zasięgu rozpiętości jednej ręki
     * @param czteryNajpopularniejszeGrupy 
     */
    public void tworzJednaReke(ArrayList<GrupaKlawiszy> czteryNajpopularniejszeGrupy) {
        lewa = new ArrayList<>();
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
    
    private void sortujCzteryNjapopularniejsze() {
        
    }
}
