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


/** liczy i przeckowuje średni czas wciśnięcia przycisku od puszczenia dowolnego ostatniego klawisza*/
   class SredniCzasNacisniecia {
       public SredniCzasNacisniecia(String _znakKlawisza, long _czas) {
           czasy = new ArrayList<>();
           znakKlawisza = _znakKlawisza;
           czasy.add(_czas);
           srednia = _czas;
       }
       
       private final String znakKlawisza;
       private ArrayList<Long> czasy;
       private long srednia;
       
       /** liczy średni czas wciśnięć przycisku*/
       private void liczSrednia() {
           srednia = 0;
           for(Long l : czasy) {
               srednia += l;
           }
           srednia /= czasy.size();
       }
       
       /** zwraca znak klawisza*/
       public String dajSwojZnak() {
           return znakKlawisza;
       }
       
       /** dodaje czas wciśnięcia i aktualizuje średnią*/
       public void dodajCzas(long _czas) {
           czasy.add(_czas);
           liczSrednia();
       }
       
       /** zwraca średni czas wciśnięcia klawisza*/
       public long dajSrednia() {
           return srednia;
       }
   }
