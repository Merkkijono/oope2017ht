// Tämä luokka kuuluu oope2017ht-pakkaukseen
package oope2017ht;
// tuodaan apulaiset.pakkauksesta In-luokka käytettäväksi
import apulaiset.In;
/**
* Tämä luokka on olio-ohjelmoinnin perusteet -kurssin harjoitustyön käyttöliittymäluokka.
* Luokan tehtävänä on hoitaa vuorovaikutusta käyttäjän ja koneen välillä.
* <p>
* Harjoitustyö, Olio-ohjelmoinnin perusteet 2017.
* </p>
* @author Matti Luiro, Luiro.Matti.J@student.uta.fi,
* Luonnontieteellinen tiedekunta, Tietojenkäsittelytieteet,
* Tampereen yliopiosto
*/
public class Kayttoliittyma {
	 
	/** Rakentaja, joka kutsuu kaynnista-metodia */
	public Kayttoliittyma(){
		kaynnista();
	}
	
	/** kaynnista-metodi käynnistää komentotulkin ja kysyy käyttäjältä komennot */
	private void kaynnista(){
		// Mikäli arvo tulee todeksi, niin tiedetään lopettaa ohjelman suoritus
		boolean lopeta = false;
		// Luodaan uusi Tulkki-olio
		Tulkki tulkki = new Tulkki();
		// Tervehdysteksti ohjelman alussa.
		System.out.println("Welcome to SOS.");
		// while-loop, jota suoritetaan niin kauan, kunnes lopeta-arvo on tosi.
		while(!lopeta){
			// Tulostetaan juurihakemiston polku
			System.out.print(tulkki.palautaPolku());
			// Luetaan käyttäjältä syöte ja tallennetaan muuttujaan
			String syote = In.readString();
			// Pilkotaan syöte split-metodilla.
			String[] syotteenOsat = syote.split(" ");
			// Lasketaan syötteen osien määrä ja tallennetaan muuttujaan.
			int osia = syotteenOsat.length;
			// Mikäli syötteen ensimmäinen arvo on md ja myos kansion 
			// nimi löytyy, niin lähetään kansion nimi tulkin md-metodille.
			// Lisäksi syötteen nimi tarkistetaan. Muussa tapauksessa 
			// tulostetaan virheilmoitus.
			if(syotteenOsat[0].equals("md")){
				if(osia == 2){
					if(tulkki.tarkistaNimi(syotteenOsat[1])){
						tulkki.md(syotteenOsat[1]);
					}
					else{
						System.out.println("Error!");
					}
				}
				else{
					System.out.println("Error!");
				}
			}
			// Mikäli syötteen ensimmäinen arvo on mf ja toinen on tiedoston nimeksi kelpaava
			// sekä kolmas on tiedoston kooksi kelpaava, niin lähetään 
			// tiedoston nimi ja koko tulkin mf-metodille.
			// Muussa tapauksessa tulostetaan virheilmoitus.
			else if(syotteenOsat[0].equals("mf")){
				if(osia == 3 && tulkki.tarkistaNimi(syotteenOsat[1]) && tulkki.tarkistaKoko(syotteenOsat[2])){  
					tulkki.mf(syotteenOsat[1], syotteenOsat[2]);
				}
				else{
					System.out.println("Error!");
				}
			}
			// Mikäli syötteen ensimmäinen osa on cd ja mikäli enempää 
			// osia ei ole, niin annetaan tulkin cd-metodille parametriksi
			// juurihakemiston nimi. Ja mikäli toinen osa löytyy, niin 
			// annetaan se tulkin cd-metodille parametriksi. Muussa tapauksessa tulostetaan
			// virheilmoitus.
			else if(syotteenOsat[0].equals("cd")){
				if(osia <= 2){
					if(osia == 1){
						tulkki.cd(tulkki.palautaJuuriHakemisto().palautaNimi().toString());
					}
					else{
						tulkki.cd(syotteenOsat[1]);
					}
				}
				else{
					System.out.println("Error!");
				}
			}
			// Mikäli syötteen ensimmäinen osa on ls ja mikäli osia ei ole enempää kuin yksi, niin annetaan
			// tulkin ls-metodille parametriksi tyhjä merkkijono. Mikäli toinen osa löytyy, niin anetaan se.
			// Muussa tapauksessa tulostetaan virheilmoitus
			else if(syotteenOsat[0].equals("ls")){
				if(osia <= 2){
					if(osia == 1){
						tulkki.ls("");
					}
					else{
						tulkki.ls(syotteenOsat[1]);
					}
				}
				else{
					System.out.println("Error!");
				}
			}
			// Mikäli syötteen ensimmäinen osa on find ja osia ei ole enempää, 
			// niin kutsutaan tulkin find-metodia. 
			// Muussa tapauksessa tulostetaan virheilmoitus
			else if(syotteenOsat[0].equals("find")){
				if(osia == 1){
					tulkki.find();
				}
				else{
					System.out.println("Error!");
				}
			}
			// Mikäli syötteen ensimmäinen osa on rm ja toisen osan nimi kelpaa, 
			// niin annetaan se parametriksi tulkin
			// rm-metodille. Muussa tapauksessa tulostetaan virheilmoitus.
			else if(syotteenOsat[0].equals("rm")){
				if(osia == 2 && tulkki.tarkistaNimi(syotteenOsat[1])){
					tulkki.rm(syotteenOsat[1]);
				}
				else{
					System.out.println("Error!");
				}
			}
			// Mikäki syötteen ensimmäinen osa on cp ja osia on kolme, 
			// joista kaksi viimeistä on tiedoston nimiä,
			// niin lähetetään tulkin cp-metodille. Muussa tapauksessa 
			// tulostetaan virheilmoitus.
			else if(syotteenOsat[0].equals("cp")){
				if(osia == 3 && tulkki.tarkistaNimi(syotteenOsat[1]) && tulkki.tarkistaNimi(syotteenOsat[2])){
					tulkki.cp(syotteenOsat[1], syotteenOsat[2]);
				}
				else{
					System.out.println("Error!");
				}
			}
			// Mikäli syötteen ensimmäinen osa on mv ja osia on kolme
			// ja kaksi viimeistä kelpuutetaan,
			// niin lähetetään kaksi viimeistä tulkin mv-metodille. 
			// Muussa tapauksessa tulostetaan virheilmoitus. 
			else if(syotteenOsat[0].equals("mv")){
				if(osia == 3 && tulkki.tarkistaNimi(syotteenOsat[1]) && tulkki.tarkistaNimi(syotteenOsat[2])){
					tulkki.mv(syotteenOsat[1], syotteenOsat[2]);
				}
				else{
					System.out.println("Error!");
				}
			}
			// Mikäli syötteen ensimmäinen osa on exit ja muita osia ei ole, niin lähetetään
			// tulkin exit-metodille. Muussa tapauksessa tulostetaan virheilmoitus.
			else if(syote.equals("exit")){
				if(osia == 1){
					lopeta = tulkki.exit();
				}
				else{
					System.out.println("Error!");
				}
			}
			else{
				System.out.println("Error!");
			}
		}
	}
}
