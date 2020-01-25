/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// Tämä luokka kuuluu oope2017ht.tiedot-pakkaukseen
package oope2017ht.tiedot;

/**
* Tämä luokka on olio-ohjelmoinnin perusteet -kurssin harjoitustyön Tieto-luokka.
* Luokan tehtävänä on toimia yliluokkana, joka pitää sisällään nimi-attribuutin,
* joka on yhteistä kaikille luokasta periytyville luokille.
* <p>
* Harjoitustyö, Olio-ohjelmoinnin perusteet 2017.
* </p>
* @author Matti Luiro, Luiro.Matti.J@student.uta.fi,
* Luonnontieteellinen tiedekunta, Tietojenkäsittelytieteet,
* Tampereen yliopiosto
*/
public abstract class Tieto implements Comparable<Tieto>{
	/** Tallentaa Tieto-luokan nimen */
	private StringBuilder nimi;

	/**
	* Rakentaja, joka luo uuden StringBuilder-olion
	* ja asettaa sen nimi-attribuutin arvoksi.
	*/
	public Tieto(){
		nimi = new StringBuilder("/");
	}

	/**
	* Toinen rakentaja, joka asettaa parametrina annetun arvon nimeksi.
	* @param nimi Asetettava nimi
	* @throws IllegalArgumentException Heittää virheilmoituksen, jos nimi
	* on virheellinen
	*/
	public Tieto (StringBuilder nimi) throws IllegalArgumentException{
		if(nimi.length() >= 1){
			asetaNimi(nimi);
		}
		else{
			throw new IllegalArgumentException("Error!");
		}
	}

	/**
	* Luokan kopiorakentaja
	* @param tieto Kopioitava tieto
	*/
	public Tieto(Tieto tieto){
		if(tieto instanceof Tieto){
			StringBuilder uusiNimi = new StringBuilder(tieto.palautaNimi());
			asetaNimi(uusiNimi);
		}
	}

	// 
	// 
	/**
	* Nimenasetusmetodi, joka asettaa parametrina annetun arvon nimeksi.
	* @param nimi Asetettava nimi
	* @throws IllegalArgumentException Heittää virheilmoituksen, jos nimi
	* on virheellinen
	*/
	public void asetaNimi(StringBuilder nimi) throws IllegalArgumentException{
		// Tarkistetaan pisteiden määrä, nimen pituutta ja 
		// merkkien oleminen sallittujen merkkien joukossa.
		if(nimi.length() >= 1){
			int pisteita = 0;
			for(int i = 0; i < nimi.length(); ++i){
				char merkki = nimi.charAt(i);
				if(merkki == '.'){
					pisteita++;
					if(pisteita > 1){
						throw new IllegalArgumentException("Error!");
					}
				}
				if(Character.isLetter(merkki) || Character.isDigit(merkki) || merkki == '.' || merkki == '_'){
					
				}
				else{
					throw new IllegalArgumentException("Error!");
				}
			}
			if(pisteita == 1 && nimi.length() == 1){
				throw new IllegalArgumentException("Error!");
			}
			else{
				this.nimi = nimi;
			}
		}
		else{
			throw new IllegalArgumentException("Error!");
		}
	}

	/** 
	* Palauttaa nimen
	* @return Palauttaa nimen
	*/
	public StringBuilder palautaNimi(){
		return nimi;
	}

	// Comparable-rajapinnan metodien toteutus
    
	/**
	* Vertaillaan ovatko olioiden nimet yhtenevät
	* ja tarkistetaan myös onko parametrina annettu olio Tieto-luokan ilmentymä.
	* Tulostetaan virheilmoitus, jollei olioiden vertailu onnistu.
	* @param o Vertailtava olio
	* @return Palauttaa true, jos oliot ovat samat ja false, jos eivät ole
	*/
	@Override
	public boolean equals(Object  o){
		boolean samat = false;
		if(o != null && o instanceof Tieto){
			try{
				Tieto tieto = (Tieto)o;
				String nimi1 = this.nimi.toString();
				String nimi2 = tieto.nimi.toString();
				if(nimi1.equals(nimi2)){
					samat = true;
				}
			}
			catch(Exception error){
				System.out.print("Error!");
			}
		}
		return samat;  
	}

	/**
	* Olioiden vertailumetodi, joka palauttaa tiedon siitä, kuinka kaukana
	* olioiden nimet ovat toisistaan. Mikäli olioiden nimet 
	* ovat samat toisen pituuteen
	* asti ja muuten eri pituiset, niin selvitetään tuleeko 
	* seuraavaksi toisessa vertailtavassa
	* piste, jolloin arvotetaan nimen perusmuoto ilman pistettä niin,
	* että saadaan toteutettua
	* järjestäminen, jossa nimen alussa esiintyvä . tulee ennen muita 
	* sallittuja merkkijä ja muuten taas ei
	* @param o Vertailtava olio
	* @return Palauttavaa tiedon olioiden vertailusta
	* @throws NullPointerException Heittää virheilmoituksen, 
	* jos parametri on tyhjä viite
	*/
	@Override
	public int compareTo(Tieto o) throws NullPointerException{
		if(o == null){
			throw new NullPointerException("Error!");
		}
		else{
			String nimi1 = this.nimi.toString();
			String nimi2 = o.nimi.toString();
			boolean eriMerkki = false;
			int tulos;
			if(nimi1.length() > nimi2.length()){
				for(int i = 0; i < nimi2.length(); ++i){
					if(nimi1.charAt(i) != (nimi2.charAt(i))){
						eriMerkki = true;
					}
				}
				if(!eriMerkki && nimi1.charAt(nimi2.length()) == '.'){
					tulos = 1;
				}
				else{
					tulos = nimi1.compareTo(nimi2);
				}
			}
			else if((nimi1.length() < nimi2.length())){
				for(int i = 0; i < nimi1.length(); ++i){
					if(nimi1.charAt(i) != (nimi2.charAt(i))){
						eriMerkki = true;
					}
				}
				if(!eriMerkki && nimi2.charAt(nimi1.length()) == '.'){
					tulos = -1;
				}
				else{
					tulos = nimi1.compareTo(nimi2);
				}
			}
			else{
				tulos = nimi1.compareTo(nimi2);
			}
			return tulos;
		}
	}

	/**
	* Luokan merkkijonotulostuksen tulostava metodi
	* @return Palauttaa Tiedon nimen merkkijonoesityksen
	*/
	@Override
	public String toString(){
		return nimi.toString();
	}
}
