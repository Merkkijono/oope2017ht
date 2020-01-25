// Tämä luokka kuuluu oope2017ht.omalista-pakkaukseen
package oope2017ht.omalista;
// Otetaan mukaan lista- ja apulaiset-pakkausten sisältö kokonaisuudessan
import fi.uta.csjola.oope.lista.*;
import apulaiset.*;
/**
* Tämä luokka on olio-ohjelmoinnin perusteet -kurssin harjoitustyön OmaLista-luokka.
* Luokan tehtävänä on huolehtia Hakemistojen sisällön listaamisesta.
* <p>
* Harjoitustyö, Olio-ohjelmoinnin perusteet 2017.
* </p>
* @author Matti Luiro, Luiro.Matti.J@student.uta.fi,
* Luonnontieteellinen tiedekunta, Tietojenkäsittelytieteet,
* Tampereen yliopiosto
*/
public class OmaLista extends LinkitettyLista implements Ooperoiva{

	/** Rakentaja, joka kutsuu yliluokan rakentajaa */
	public OmaLista(){
		super();
	}

	// Ooperoiva-rajapinnan metodien toteutus

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public Object hae(Object haettava){
		// Viite hakutulokseen
		Object haettu = null;
		// Muutetaan Comparable-tyyppiseksi, jotta olioita voidaan vertailla niiden
		// omilla vertailumetodeilla.
		Comparable haettavaComp = (Comparable)haettava;
		Comparable tempHaettu;
		// Tarkistetaan onko lista tai haettava tyhjä, jollei ole 
		// niin käydään lista läpi, muuten palautetaan null-viite.
		// Mikäli listalta löytyy sama alkio, niin asetataan se palautettavaksi arvoksi.
		if(onkoTyhja() || haettava == null){
			haettu = null;
		}
		else{
			for(int i = 0; i < koko(); ++i){
				tempHaettu = (Comparable)alkio(i);
				if(haettavaComp.equals(tempHaettu)){
					haettu = alkio(i);
				}
			}
		}
		return haettu;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public boolean lisaa(Object uusi){
		// Lisätään listalle olio, jos se onnistuu. Lisäys ei onnistu, 
		// mikäli olio on jo listalla tai kokeillaan lisätä null-arvoa.
		// Palautetaan tieto siitä, onnistuiko lisäys.
		boolean onnistuiko = false;

		if(uusi != null && uusi instanceof Comparable){
			boolean listalla = false;
			// Muutetaan Comparable-tyyppiseksi, jotta olioita voidaan vertailla niiden
			// omilla vertailumetodeilla.
			Comparable uusiAlkio = (Comparable)uusi;
			Comparable vanhaAlkio;
			// Tyhjän listan tapauksessa lisätään listan alkuun. 
			// Muuten tarkistetaan löytyykö listalta.
			// Mikäli ei löydy, niin tehdään lisäys sen perusteella, 
			// miten lisättävä vertautuu jo listalla oleviin.
			if(onkoTyhja()){
				lisaaAlkuun(uusi);
				onnistuiko = true; 
			}
			else{
				for(int i = 0; i < koko(); ++i){
					vanhaAlkio = (Comparable)alkio(i);
					if(uusiAlkio.equals(vanhaAlkio)){
						listalla = true;
						break;
					}
				}
				if(!listalla){
					while(!onnistuiko){
						int i = koko()-1;
						vanhaAlkio = (Comparable)alkio(i);
						if(uusiAlkio.compareTo(vanhaAlkio) > 0){
							lisaaLoppuun(uusi);
							onnistuiko = true;
						}
						else if(i == 0){
							lisaaAlkuun(uusi);
							onnistuiko = true;
						}
						else{
							while(!onnistuiko){
								i--;
								vanhaAlkio = (Comparable)alkio(i);
								if(uusiAlkio.compareTo(vanhaAlkio) > 0){
									lisaa(i+1, uusi);
									onnistuiko = true;
								}
								else if(i == 0){
									lisaaAlkuun(uusi);
									onnistuiko = true;
								}
							}
						}
					}
				}
				else{
					onnistuiko = false;
				}
			}       
		}
		else{
			onnistuiko = false;
		}
					  

		return onnistuiko;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public Object poista(Object poistettava){
		// Poistetaan annettu olio listalta, jollei se ole 
		// null-arvo ja mikäli olio ylipäänsä löytyy listalta.
		Object poistettu = null;
		boolean loytyi = false;
		// Muutetaan Comparable-tyyppiseksi, jotta olioita 
		// voidaan vertailla niiden
		// omilla vertailumetodeilla.
		Comparable poistettavaComp = (Comparable)poistettava;
		Comparable haettu;
		if(poistettava != null){
			for(int i = 0; i < koko() && !loytyi; ++i){
				haettu = (Comparable)alkio(i);
				if(poistettavaComp.equals(haettu)){
					poistettu = poista(i);
					loytyi = true;
				}
			}
		}
		return poistettu;
	}
}
