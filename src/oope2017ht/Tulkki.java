// Tämä luokka kuuluu oope2017ht-pakkaukseen
package oope2017ht;
// Tuodaan käyttöön LinkitettyLista ja tiedot.paukkauksen sisältö kokonaisuudessaan.
import fi.uta.csjola.oope.lista.LinkitettyLista;
import oope2017ht.tiedot.*;
/**
* Tämä luokka on olio-ohjelmoinnin perusteet -kurssin harjoitustyön tulkkiluokka.
* Luokan tehtävänä on tulkita käyttäjän antamia komentoja.
* <p>
* Harjoitustyö, Olio-ohjelmoinnin perusteet 2017.
* <p>
* @author Matti Luiro, Luiro.Matti.J@student.uta.fi,
* Luonnontieteellinen tiedekunta, Tietojenkäsittelytieteet,
* Tampereen yliopiosto
*/
public class Tulkki {

	/** Tallentaa hakemistopolun */
	private String polku;
	/** Tallentaa juurihakemiston */
	private Hakemisto juuriHakemisto;
    
	/** 
	* Tulkki-luokan rakennin, jossa luodaan uusi Hakemisto-olio ja asetetaan se
	* juuriHakemisto-muuttujan arvoksi. Myös polku alustetaan juurihakemiston nimellä
	* ja päätemerkillä.
	*/
	public Tulkki(){
		juuriHakemisto = new Hakemisto();
		polku = juuriHakemisto.palautaNimi() + ">";
	}

	/** 
	* Palauttaa polku-attribuutin arvon
	* @return Palautettu polku
	*/
	public String palautaPolku(){
		return polku;
	}

	/** 
	* Palauttaa juuriHakemisto-attribuutin arvon
	* @return Palautettu juurihakemisto
	*/
	public Hakemisto palautaJuuriHakemisto(){
		return juuriHakemisto;
	}

	/** 
	* Hakee parametriksi annetun hakemiston polun
	* @param tama Hakemisto, jonka polku haetaan
	* @return Palauttaa parametriksi annetun hakemiston polun
	*/
	private String haePolku(Hakemisto tama){
		String hakemistoPolku = "";
		if(tama.equals(juuriHakemisto)){
			hakemistoPolku = "/";
		}
		else{
			// liitetään hakemistopolkuun lisää hakemistoja ja muutetaan arvo ylihakemiston arvoksi,
			// niin kauan, kunnes hakemiston arvo on null.
			while(tama != null){
				if(tama.palautaNimi().toString().equals("/")){
					hakemistoPolku = tama.palautaNimi() + hakemistoPolku;
				}
				else{
					hakemistoPolku = tama.palautaNimi() + "/" + hakemistoPolku;
				}
				tama = tama.palautaYlihakemisto();
			}
		}
		return hakemistoPolku;
	}

	/** 
	* Tulostaa parametriksi annetun hakemiston puun alihakemistoineen rekursiivisesti
	* @param hakemisto Hakemisto, jonka puu tulostetaan
	*/
	private void puunTulostus(Hakemisto hakemisto){
		LinkitettyLista sisalto = hakemisto.sisalto();
		int i = 0;
		while(i < sisalto.koko()){
			Tieto tieto = (Tieto)sisalto.alkio(i);
			System.out.print(haePolku(hakemisto));
			System.out.println(tieto);
			if(tieto instanceof Hakemisto){
				puunTulostus((Hakemisto)tieto);
			}
			i++;
		}
	}

	/** 
	* Tarkistaa parametriksi annetun nimen oikeellisuuden
	* @param nimi Tarkistettava nimi
	* @return Palauttaa true, jos nimi oli vaatimusten mukainen,
	* muuten palauttaa false
	*/
	protected boolean tarkistaNimi(String nimi){
		// kelpaako-muuttujan arvo on palautettava tulos ja
		// lippu-muuttuja on sitä varten, että jos jossain kohtaa tarkistuksia tulee
		// epäkelpo merkki, niin lippu tiputetaan epätodeksi ja tämän perusteella
		// kerrotaan kelpaako-muuttujalle, jos voidaan kelpuuttaa nimi.
		// pisteitä-muuttuja taltioi pisteiden määrän ja niitä ei saa esiintyä 1 enempää,
		// eikä myöskään olla ainoa merkki.
		boolean kelpaako = false;
		boolean lippu = true;
		int pisteita = 0;
		if(nimi != null){
			for(int i = 0; i < nimi.length(); ++i){
				char merkki = nimi.charAt(i);
				if(nimi.length() == 1 && merkki == '.'){
					lippu = false;
					break;
				}
				else if(merkki == '.'){
					pisteita++;
					if(pisteita > 1){
						lippu = false;
						break;
					}
				}
				else if(!Character.isLetter(merkki) && !Character.isDigit(merkki) 
					&& merkki != '.' && merkki != '_'){
					lippu = false;
					break;
				}
			}
			if(lippu){
				kelpaako = true;
			}
		}
		return kelpaako;
	}

	/**
	* Tarkistaa parametriksi annetun koon oikeellisuuden
	* @param koko Tarkistettava koko
	* @return Palauttaa true, jos koko oli vaatimusten mukainen,
	* muuten palauttaa false
	*/
	protected boolean tarkistaKoko(String koko){
		// Muutetaan parametriarvo int-tyyppiseksi.
		// kelpaako-muuttuja sisältää tiedon koon oikeellisuudesta
		int kokoInt;
		boolean kelpaako = false;
		try{
			kokoInt = Integer.parseInt(koko);
			if(kokoInt >= 0){
				kelpaako = true;
			}
			else{
				kelpaako = false;
			}
		}
		catch(NumberFormatException e){
			kelpaako = false;
		}
		return kelpaako;
	}

	/**
	* Tulostaa parametriksi annetun listan alkiot
	* @param lista Tulostettava lista
	*/
	protected void tulosta(LinkitettyLista lista) {
		if (lista != null) {
			for (int i = 0; i < lista.koko(); i++) {
				System.out.println(lista.alkio(i));
			}
		}
	}

	/**
	* Luo uuden hakemiston työhakemistoon ja nimeää sen parametriksi annetulla nimellä
	* @param nimi Uuden hakemiston nimi
	*/
	protected void md(String nimi){
		if(tarkistaNimi(nimi)){
			// Pilkotaan hakemistopolku osiin eroteltuna / merkillä
			String[] polkuOsat = palautaPolku().split("/");
			Hakemisto haettu;
			// Mikäi polkuosia on enemmän kuin kaksi, niin selvitetään juurihakemistosta lähtevää
			// polkua oikeaan hakemistoon ja luodaan sinne uusi hakemisto. Muussa tapauksessa
			// luodaan uusi hakemisto juurihakemistoon. Tulostetaan virheilmoitus jos hakemisto
			// on jo olemassa hakemistossa.
			if(polkuOsat.length > 2){
				haettu = juuriHakemisto;
				int i = 1;
				while(!polkuOsat[i].equals(">")){
					haettu = (Hakemisto)haettu.hae(polkuOsat[i]);
					i++;                    
				}
				if(haettu.hae(nimi) == null){
					Hakemisto uusiHakemisto = new Hakemisto(new StringBuilder(nimi), haettu);
					haettu.lisaa(uusiHakemisto); 
				}
				else{
					System.out.println("Error!");
				}
			}
			else{
				if(juuriHakemisto.hae(nimi) == null){
					Hakemisto uusiHakemisto = new Hakemisto(new StringBuilder(nimi), juuriHakemisto);
					juuriHakemisto.lisaa(uusiHakemisto);   
				}
				else{
					System.out.println("Error!");
				}
			}
		}
		else{
			System.out.println("Error!");
		}
	}

	/** 
	* Luo uuden tiedoston työhakemistoon ja nimeää tiedoston parametriksi
	* annetulla nimellä ja asettaa tiedoston kooksi parametriksi annetun koon
	* @param nimi Uuden tiedoston nimi
	* @param koko Uuden tiedoston koko
	*/
	protected void mf(String nimi, String koko){
		if(tarkistaNimi(nimi) && tarkistaKoko(koko)){
			// Pilkotaan hakemistopolku osiin eroteltuna / merkillä
			String[] polkuOsat = palautaPolku().split("/");
			// Muutetaan koko int-tyyppiseksi.
			int kokoInt = Integer.parseInt(koko);
			Tiedosto uusiTiedosto = new Tiedosto(new StringBuilder(nimi), kokoInt);
			Hakemisto haettu;
			// Mikäi polkuosia on enemmän kuin kaksi, niin selvitetään juurihakemistosta lähtevää
			// polkua oikeaan hakemistoon ja luodaan sinne uusi tiedosto. Muussa tapauksessa
			// luodaan uusi tiedosto juurihakemistoon. Tulostetaan virheilmoitus jos tiedosto
			// on jo olemassa hakemistossa.
			if(polkuOsat.length > 2){
				haettu = juuriHakemisto;
				int i = 1;
				while(!polkuOsat[i].equals(">")){
					haettu = (Hakemisto)haettu.hae(polkuOsat[i]);
					i++;                    
				}
				if(haettu.hae(nimi) == null){
					haettu.lisaa(uusiTiedosto); 
				}
				else{
					System.out.println("Error!");
				}
			}
			else{
				if(juuriHakemisto.hae(nimi) == null){
					juuriHakemisto.lisaa(uusiTiedosto); 
				}
				else{
					System.out.println("Error!");
				}
			}
		}
		else{
			System.out.println("Error!");
		}
	}

	/**
	* Hakemistopolussa etenemiseen tarkoitettu metodi. Annetun
	* parametrin mukaan edetään joko taaksepäin tai eteenpäin hakemistopolussa
	* @param komento Hakemistopuussa etenemistä määrittelevä parametri
	*/
	protected void cd(String komento){
		// Mikäli parametri on null-viite tai juurihakemiston niminen, niin asetetaan poluksi
		// juurihakemiston polku. Muussa tapauksessa pilkotaan polkuosa ja tutkitaan parametrina
		// annetusta nimestä, onko tarkoitus mennä kumpaan suuntaan hakemistopolussa.
		// .. tarkoittaa etenemistä pykälän verran juurihakemistoa kohti ja muuten selvitetään
		// löytyykö parametrin mukaista hakemistoa kansiosta. Mikäli löytyy, niin edetään sinne. 
		// Muussa tapauksessa tulostetaan virheilmoitus.
		if(komento == null || komento.equals(juuriHakemisto.palautaNimi().toString())){
			polku = juuriHakemisto.palautaNimi() + ">";
		}
		else{
			String[] polkuOsat = palautaPolku().split("/");
			if(komento.equals("..") && polkuOsat.length > 2){
				polku = "/";
				for(int i = 1; i < polkuOsat.length-2; ++i){
					polku = polku + polkuOsat[i] + "/";
				}
				polku = polku + ">";
			}
			else if(tarkistaNimi(komento)){
				Hakemisto haettu;
				if(polkuOsat.length > 2){
					haettu = juuriHakemisto;
					int i = 1;
					while(!polkuOsat[i].equals(">")){
						haettu = (Hakemisto)haettu.hae(polkuOsat[i]);
						i++;                    
					}
					if(haettu.hae(komento) != null && haettu.hae(komento) instanceof Hakemisto){
						polku = "/";
						for(int j = 1; j < polkuOsat.length-1; ++j){
							polku = polku + polkuOsat[j] + "/";
						}
						polku = polku + komento + "/>";
					}
					else{
						System.out.println("Error!");
					}

				}
				else{
					if(juuriHakemisto.hae(komento) != null && juuriHakemisto.hae(komento) instanceof Hakemisto){
						polku = "/" + komento + "/>"; 
					}
					else{
						System.out.println("Error!");
					}

				}        
			}
			else{
				System.out.println("Error!");
			}
		}
	}
    
	/**
	* Listaa joku työhakemiston sisällön tiedot tai parametriksi annetun hakemiston tai
	* tiedoston tiedot
	* @param listattava Listattava asia, tyhjä merkkijono listaa juurihakemiston
	* sisällön tiedot
	*/
	protected void ls(String listattava){
		// Pilkotaan jälleen hakemistopolku osiin eroteltuna / merkillä. 
		// Mikäli osia on 2 tai vähemmän tai listattava
		// tyhjä merkkijono, niin listataan juurihamiston sisältö. 
		// Muussa tapauksessa selvitetään hakemisto ja listataan
		// sen sisällön tiedot. Mikäli lisättävän arvo on jotain muuta 
		// kuin tyhjä merkkijono, niin selvitetään löytyykö
		// hakemistosta kyseinen tiedosto tai hakemisto. 
		// Mikäli ei löydy, niin tulostetaan virheilmoitus.
		String[] polkuOsat = palautaPolku().split("/");
		if(listattava.equals("")){
			if(polkuOsat.length <= 2){
				tulosta(juuriHakemisto.sisalto());
			}
			else{
				Hakemisto haettu;
				haettu = juuriHakemisto;
				int i = 1;
				while(!polkuOsat[i].equals(">")){
					haettu = (Hakemisto)haettu.hae(polkuOsat[i]);
					i++;                    
				}
				tulosta(haettu.sisalto());
			}
		}
		else{
			if(tarkistaNimi(listattava)){
				if(polkuOsat.length <= 2){
					if(juuriHakemisto.hae(listattava) != null){
						System.out.println(juuriHakemisto.hae(listattava));
					}
					else{
						System.out.println("Error!");
					}
				}
				else{
					Hakemisto haettu;
					haettu = juuriHakemisto;
					int i = 1;
					while(!polkuOsat[i].equals(">")){
						haettu = (Hakemisto)haettu.hae(polkuOsat[i]);
						i++;                    
					}
					if(haettu.hae(listattava) != null){
						System.out.println(haettu.hae(listattava));
					}
					else{
						System.out.println("Error!");
					}
				}
			}
			else{
				System.out.println("Error!");
			}

		}
	}
	
	/** Listaa työhakemiston sisällön alihakemistoineen rekursiivisesti */
	protected void find(){
		String[] polkuOsat = palautaPolku().split("/");
		if(polkuOsat.length <= 2){
			puunTulostus(juuriHakemisto);
		}
		else{
			Hakemisto haettu;
			haettu = juuriHakemisto;
			int i = 1;
			while(!polkuOsat[i].equals(">")){
				haettu = (Hakemisto)haettu.hae(polkuOsat[i]);
				i++;                    
			}
			puunTulostus(haettu);
		}
	}
	
	// Tiedoston ja hakemiston poistoon tarkoitettu metodi.
	/**
	* Poistaa parametriksi annetun hakemiston tai tiedoston
	* @param poistettava Poistettavan hakemiston tai tiedoston nimi
	*/
	protected void rm(String poistettava){
		// Pilkotaan polku jälleen osiin / merkillä eroteltuna ja tehdään parametrin nimitarkastus.
		// Sen jälkeen selvitetään työhakemisto ja tarkistetaan 
		// löytyykö poistettava hakemisto tai tiedosto.
		// Mikäli ei löydy, niin tulostetaan virheilmoitus.
		String[] polkuOsat = palautaPolku().split("/");
		if(tarkistaNimi(poistettava)){
			if(polkuOsat.length <= 2){
				if(juuriHakemisto.hae(poistettava) != null){
					juuriHakemisto.poista(poistettava);
					if(juuriHakemisto.hae(poistettava) != null){
						juuriHakemisto.poista(poistettava);
					}
				}
				else if(juuriHakemisto.hae(poistettava) != null){
					juuriHakemisto.poista(poistettava);
				}
				else{
					System.out.println("Error!");
				}
			}
			else{
				Hakemisto haettu;
				haettu = juuriHakemisto;
				int i = 1;
				while(!polkuOsat[i].equals(">")){
					haettu = (Hakemisto)haettu.hae(polkuOsat[i]);
					i++;                    
				}
				if(haettu.hae(poistettava) != null){
					haettu.poista(poistettava);
					if(haettu.hae(poistettava) != null){
						haettu.poista(poistettava);
					}
				}
				else if(haettu.hae(poistettava) != null){
					haettu.poista(poistettava);
				}
				else{
					System.out.println("Error!");
				}
			}
		}
		else{
			System.out.println("Error!");
		}
	}
	
	// Tiedoston kopiointiin tarkoitettu metodi.
	/**
	* Kopioi ensimääiseksi parametriksi annetun tiedoston toiseksi annetun
	* parametrin nimiseksi.
	* @param kopioitava Kopioitavan tiedoston nimi
	* @param kopio Tiedoston kopion nimi
	*/
	protected void cp(String kopioitava, String kopio){
		// Pilkotaan hakemistopolku ja tarkistetaan parametrien oikeellisuus. 
		// Sen jälkeen selvitetään
		// työhakemisto ja kokeilla löytää kopioitava tiedosto. 
		// Kopiointi toteutetaan kopiorakentimella. Mikäli ei löydy, niin tulostetaan virheilmoitus.
		String[] polkuOsat = palautaPolku().split("/");
		if(tarkistaNimi(kopioitava) && tarkistaNimi(kopio)){
			Tiedosto temp;
			if(polkuOsat.length <= 2){
				if(juuriHakemisto.hae(kopioitava) != null && (juuriHakemisto.hae(kopioitava) instanceof Tiedosto)
					&& juuriHakemisto.hae(kopio) == null && !kopioitava.equals(kopio)){
					temp = new Tiedosto((Tiedosto)juuriHakemisto.hae(kopioitava));
					temp.asetaNimi(new StringBuilder(kopio));
					juuriHakemisto.lisaa(temp);
				}
				else{
					System.out.println("Error!");
				}
			}
			else{
				Hakemisto haettu;
				haettu = juuriHakemisto;
				int i = 1;
				while(!polkuOsat[i].equals(">")){
					haettu = (Hakemisto)haettu.hae(polkuOsat[i]);
					i++;                    
				}
				if(haettu.hae(kopioitava) != null && (haettu.hae(kopioitava) instanceof Tiedosto)
					&& haettu.hae(kopio) == null && !kopioitava.equals(kopio)){
					temp = new Tiedosto((Tiedosto)haettu.hae(kopioitava));
					temp.asetaNimi(new StringBuilder(kopio));
					haettu.lisaa(temp);
				}
				else{
					System.out.println("Error!");
				}
			}
		}
		else{
			System.out.println("Error!");
		}
	}
	
	// Tiedoston ja hakemiston uudelleennimeämiseen tarkoitettu metodi.
	/**
	* Nimeää ensimmäiseksi parametriksi annetun tiedoston tai hakemiston
	* toiseksi parametriksi annetun nimiseksi
	* @param vanhaNimi Uudelleen nimettävän hakemiston tai tiedoston nimi
	* @param uusiNimi Nimi jolla tiedosto tai hakemisto nimetään
	*/
	protected void mv(String vanhaNimi, String uusiNimi){
		// Tarkistetaan parametrin nimien oikeellisuus ja pilkotaan 
		// polku osiin / merkillä eroteltuna.
		// Sen jälkeen selvitetään työhakemisto ja kokeillaan löytää 
		// uudelleennimettävä tiedosto tai hakemisto.
		// Mikäli ei löydy tai nimissä esiintyy muuten virhettä, 
		// niin tulostetaan virheilmoitus.
		if(tarkistaNimi(vanhaNimi) && tarkistaNimi(uusiNimi)){
			String[] polkuOsat = palautaPolku().split("/");
			Tieto temp;
				if(polkuOsat.length <= 2){
					if(juuriHakemisto.hae(vanhaNimi) != null && juuriHakemisto.hae(uusiNimi) == null 
						&& !vanhaNimi.equals(uusiNimi)){
						temp = juuriHakemisto.hae(vanhaNimi);
						juuriHakemisto.poista(vanhaNimi);
						temp.asetaNimi(new StringBuilder(uusiNimi));
						juuriHakemisto.lisaa(temp);
					}
					else{
						System.out.println("Error!");
					}
				}
				else{
					Hakemisto haettu;
					haettu = juuriHakemisto;
					int i = 1;
					while(!polkuOsat[i].equals(">")){
						haettu = (Hakemisto)haettu.hae(polkuOsat[i]);
						i++;                    
					}
					if(haettu.hae(vanhaNimi) != null && haettu.hae(uusiNimi) == null 
						&& !vanhaNimi.equals(uusiNimi)){
						temp = haettu.hae(vanhaNimi);
						haettu.poista(vanhaNimi);
						temp.asetaNimi(new StringBuilder(uusiNimi));
						haettu.lisaa(temp);
					}
					else{
						System.out.println("Error!");
					}
				}
		}
		else{
			System.out.println("Error!");
		}
	}
	
	/** 
	* Tulostaa lopetusviestin ja palauttaa tiedon, jonka
	* perusteella sovelluksen suorituksen voi lopettaa
	* @return Palauttaa true merkiksi sovelluksen lopettamisesta
	*/
	protected boolean exit(){
		System.out.println("Shell terminated.");
		return true;
	}
}
