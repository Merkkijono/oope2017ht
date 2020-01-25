Olio-ohjelmoinnin perusteet -kurssin harjoitustyö.
Tehtävänä oli ohjelmoida Java-kielellä komentoikkunaa(komentotulkkia, komentoriviä) simuloiva olioperustainen ohjelma.
Osa tiedostoista tuli tehtävänannon mukana ja osa tuli kirjoittaa itse.
src/oope2017ht -kansiosta löytyvät itse kirjoitetut tiedostot.

Tulkin ymmärtämät komennot:

md <kansion nimi>

Luo parametrina annetulla nimellä alihakemiston nykyiseen
hakemistoon, jos samannimistä alihakemisto ei jo ole.

mf <tiedoston nimi> <koko>

Luo annetun nimisen ja kokoisen tiedoston nykyiseen
hakemistoon, jos samannimistä tiedostoa ei jo ole.

cd <".." tai alihakemiston nimi>

Asettaa työhakemistoksi joko yli- tai alihakemiston, jos voidaan
siirtyä. Ylihakemistoon viitataan kahdella pisteellä. Ilman parametria
palataan juureen.

ls <tiedoston tai hakemiston nimi>

Listaa näytölle annetun nimisen tiedoston tai hakemiston tiedot, jos
listauksen kohde löytyy nykyisestä hakemistosta. Listaa nykyisen
hakemiston sisällön, jos parametria ei anneta.

find

Listaa hakemiston rekursiivisesti esijärjestyksessä.

rm <tiedoston tai hakemiston nimi>

Poistaa varmistamatta tiedoston tai hakemiston, jos sellainen on
nykyhakemistossa

cp <kopioitavan tiedoston nimi> <uuden tiedoston nimi>

Kopioi tiedoston annetun nimiseksi uudeksi tiedostoksi, jos nimellä
löydetään tiedosto nykyhakemistosta ja hakemistossa ei ole vielä
uuden nimistä tiedostoa.

mv <vanha nimi> <uusi nimi>

Nimeää tiedoston annetun nimiseksi uudeksi tiedostoksi, jos
nimellä löydetään tiedosto nykyhakemistosta ja hakemistossa ei ole
vielä uuden nimistä tiedostoa.

exit

Lopetaa ohjelman