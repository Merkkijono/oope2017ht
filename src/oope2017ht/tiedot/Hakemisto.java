// Tämä luokka kuuluu oope2017ht.tiedot-pakkaukseen
package oope2017ht.tiedot;
// Otetaan mukaan OmaLista ja apulaiset- sekä lista-pakkauksen koko sisällöt.
import oope2017ht.omalista.OmaLista;
import apulaiset.*;
import fi.uta.csjola.oope.lista.*;

/**
* Tämä luokka on olio-ohjelmoinnin perusteet -kurssin harjoitustyön Hakemisto-luokka.
* Luokan tehtävänä on luoda Hakemisto ja huolehtia siihen liittyvistä käsittelyistä.
* <p>
* Harjoitustyö, Olio-ohjelmoinnin perusteet 2017.
* </p>
* @author Matti Luiro, Luiro.Matti.J@student.uta.fi,
* Luonnontieteellinen tiedekunta, Tietojenkäsittelytieteet,
* Tampereen yliopiosto
*/
public class Hakemisto extends Tieto implements Komennettava<Tieto> {
	/** Hakemiston ylihakemiston tallettava attribuutti */
	private Hakemisto ylihakemisto;
	/** Hakemiston sisallön tallettava attribuutti */
	private OmaLista sisalto;

	/** 
	* Rakentaja, joka kutsuu Tieto-luokan rakentajaa ja alustaa 
	* ylihakemiston null-arvolla
	* sekä luo sisältö-attribuutille uuden OmaLista-olion.
	*/
	public Hakemisto(){
		super();
		ylihakemisto = null;
		sisalto = new OmaLista();
	}

	/** 
	* Kuormitettu rakentaja, joka luo uuden Hakemisto-olion 
	* annettujen parametrien perusteella.
	* @param nimi Hakemiston nimi
	* @param ylihakemisto Hakemiston ylihakemisto
	* @throws IllegalArgumentException Heittää virheilmoituksen, 
	* jos hakemiston nimessä on virhe
	*/
	public Hakemisto(StringBuilder nimi, Hakemisto ylihakemisto) throws IllegalArgumentException{
		super(nimi);
		asetaYlihakemisto(ylihakemisto);
		sisalto = new OmaLista();
	}

	/**
	* Asettaa ylihakemiston
	* @param ylihakemisto Asetettava ylihakemisto
	*/
	public void asetaYlihakemisto(Hakemisto ylihakemisto){
		if(ylihakemisto instanceof Hakemisto){
			this.ylihakemisto = ylihakemisto;
		}
		else{
			this.ylihakemisto = null;
		}
	}

	/**
	* Palauttaa ylihakemiston
	* @return Palautettava ylihakemisto
	*/
	public Hakemisto palautaYlihakemisto(){
		return ylihakemisto;
	}

	/**
	* Asettaa sisällöksi annettavan sisällön.
	* @param sisalto Asetettava sisältö
	*/
	public void asetaSisalto(OmaLista sisalto){
		if(sisalto != null && sisalto instanceof OmaLista){
			this.sisalto = sisalto;
		}
	}

	/**
	* Palauttaa sisällön
	* @return Palautettava sisältö
	*/
	public OmaLista palautaSisalto(){
		return sisalto;
	}

	// Komennettava-rajapinnan metodien toteutus

	/** {@inheritDoc} */
	@Override
	public LinkitettyLista sisalto(){
		return (LinkitettyLista)sisalto;
	}

	/** {@inheritDoc} */
	@Override
	public Tieto hae(String nimi){
		Tieto tulos = null;
		if(nimi != null){
			Hakemisto haku = new Hakemisto();
			StringBuilder nimiSb = new StringBuilder(nimi);
			haku.asetaNimi(nimiSb);
			tulos = (Tieto)sisalto.hae((Tieto)haku);  
		}
		return tulos;
	}

	/** {@inheritDoc} */
	@Override
	public boolean lisaa(Tieto lisattava){
		boolean onnistuiko = false;
		Tieto hakutulos;
		if(lisattava != null && lisattava instanceof Tieto){
			hakutulos = (Tieto)sisalto.hae(lisattava);
			if(hakutulos == null){
				onnistuiko = sisalto.lisaa(lisattava);
			}
		}
		return onnistuiko;
	}

	/** {@inheritDoc} */
	@Override
	public Tieto poista(String nimi){
		Tieto poistettu = null;
		if(nimi != null){
			Hakemisto haku = new Hakemisto();
			StringBuilder nimiSb = new StringBuilder(nimi);
			haku.asetaNimi(nimiSb);
			poistettu = (Tieto)sisalto.hae((Tieto)haku);
			sisalto.poista(poistettu);
		}
		return poistettu;
	}

	/** 
	* Luokan merkkijonoesityksen tulostava metodi.
	* @return Palauttaa Hakemiston nimen / merkin ja välilyönnin,
	* jonka jälkeen vielä tiedon Hakemiston tiedostojen ja Hakemistojen
	* lukumäärästä.
	*/
	@Override
	public String toString(){
		return super.toString() + "/ " + sisalto.koko();
	}
}
