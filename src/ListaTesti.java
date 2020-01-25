/*
 * Olio-ohjelmoinnin perusteet, Jorma Laurikkala.
 *
 * Linkitetyn listan testailua.
 *
 */

// Otetaan k�ytt��n lista-pakkauksen luokat.
import fi.uta.csjola.oope.lista.*;

public class ListaTesti {
   /* Apumetodi listan l sis�ll�n tulostamiseen alusta loppuun.
    */
   private static void tulostaLista(LinkitettyLista l) {
      System.out.println("-----");
      System.out.println("koko = " + l.koko());
      int koko = l.koko();
      for (int i = 0; i < koko; i++) {
         Object alkio = l.alkio(i);
         System.out.println(alkio);
      }
   }

   public static void main(String[] args) {
      // Luodaan lista.
      LinkitettyLista lista = new LinkitettyLista();

      // Lis�t��n listan alkuun merkkijonoja ja tulostetaan listan alkiot.
      lista.lisaaAlkuun("katolla");
      lista.lisaaAlkuun("kissa");
      lista.lisaaAlkuun("musta");
      tulostaLista(lista);

      // Lis�t��n listan keskelle merkkijono ja tulostetaan listan alkiot.
      lista.lisaa(2, "istuu");
      tulostaLista(lista);

      // Poistetaan listan lopusta ja tulostetaan listan alkiot.
      lista.poistaLopusta();
      tulostaLista(lista);

      // Poistetaan listan keskelt� ja tulostetaan listan alkiot.
      lista.poista(1);
      tulostaLista(lista);
   }
}
