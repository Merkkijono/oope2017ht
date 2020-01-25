// Tämä luokka kuuluu oope2017ht.tiedot-pakkaukseen
package oope2017ht.tiedot;

/**
* Tämä luokka on olio-ohjelmoinnin perusteet -kurssin harjoitustyön Tiedosto-luokka.
* Luokan tehtävänä on luoda Tiedosto ja huolehtia siihen liittyvistä käsittelyistä.
* <p>
* Harjoitustyö, Olio-ohjelmoinnin perusteet 2017.
* </p>
* @author Matti Luiro, Luiro.Matti.J@student.uta.fi,
* Luonnontieteellinen tiedekunta, Tietojenkäsittelytieteet,
* Tampereen yliopiosto
*/
public class Tiedosto extends Tieto{
	/** Tallettaa tiedoston koko-tiedon */
	private int koko;
 

	/**
	* Rakentaja, joka asettaa parametrina annetun nimen Tieto-luokan rakentajalle
	* ja asettaa koko-parametrin tiedoston kooksi.
	* @param nimi Uuden tiedoston nimi
	* @param koko Uuden tiedoston koko
	* @throws IllegalArgumentException Heittää virheilmoituksen,
	* jos nimi tai koko on virheellinen
	*/
	public Tiedosto(StringBuilder nimi, int koko) throws IllegalArgumentException{
			super(nimi);
			asetaKoko(koko);
	}
 
	/**
	* Tiedoston kopiorakentaja
	* @param tiedosto Kopioitava tiedosto
	*/
	public Tiedosto(Tiedosto tiedosto){
		super(tiedosto);
		if(tiedosto instanceof Tiedosto){
			asetaKoko(tiedosto.palautaKoko());
		}
	}
 
	/**
	* Asettaa Tiedoston koon
	* @param koko Asetettava koko
	* @throws IllegalArgumentException Heittää virheilmoituksen 
	* virheellisestä koosta
	*/
	public void asetaKoko(int koko) throws IllegalArgumentException{
		if(koko >= 0){
			this.koko = koko;
		}
		else{
			throw new IllegalArgumentException("Error!");
		}
	}

	/**
	* Palauttaa Tiedoston koon
	* @return Palautettava koko
	*/
	public int palautaKoko(){
		return koko;
	}
 
	/**
	* Luokan merkkijonotulostuksen tulostava metodi
	* @return Palauttaa Tiedoston nimen merkkijonoesityksen,
	* välilyönnin ja koko-tiedon.
	*/
	@Override
	public String toString(){
		return super.toString() + " " + koko;
	}
}
