# Dokumentacija
## Opis rada aplikacije

Aplikacija Zbrajalica je interaktivna edukativna igra za Android platformu koja ima za cilj poboljšati matematičke vještine kroz zabavno iskustvo. Korisnicima omogućuje da vježbaju zbrajanje i oduzimanje kroz seriju brzinskih izazova. Glavna funkcionalnost aplikacije je generiranje matematičkih izraza sa jednocifrenim brojevima, na koje korisnici trebaju odgovoriti.

### Početni ekran
Pri otvaranju aplikacije, korisnik će biti upućen na početni ekran. Početni ekran sadrži dva dugmića i navigaciju. 
- Klikom na dugme **"Započni igru"**, korisnik će biti upućen na glavni ekran igre.
- Klikom na dugme **"Pravila igre"**, korisnik će biti upućen na ekran o pravilima igre.
- Navigacija pored naziva aplikacije sadrži dva dugmića koja omogućavaju mijenjanje jezika aplikacije (engleski i bosanski jezik).
  
![home](https://github.com/esmaagic/MiniMath/assets/108765837/14654e8f-a38b-4b51-b100-d770686f0c55)

### Ekran "Pravila igre"
Sadrži opis i pravila igre. Klikom na strelicu korisnik biva upućen na početnu stranicu aplikacije.
![rules](https://github.com/esmaagic/MiniMath/assets/108765837/22db19dc-1245-4828-b3af-0b484d492f91)

### Ekran "Započni igru"
Korisniku je prikazan **layout** igre. 

- **Prvi red**: 
  - Dugmić sa strelicom omogućuje korisniku da se vrati na početni ekran igre.
  - Dugmić sa ikonom "podijeli" omogućuje korisniku da pošalje predefinisanu poruku izvan aplikacije, nekoj drugoj aplikaciji.
  - Na kraju reda je podatak o broju odigranih rundi.

- **Drugi red**: 
  - Vrijeme.
  - Bodovi. 
  - Čim korisnik dođe na ovaj ekran, pokrene se tajmer. Tajmer se zaustavi kada korisnik da tačan odgovor. Tajmer se ponovo pokrene kada korisnik klikne dugme "sljedeća runda". Tajmer se restartuje kada korisnik napusti ovaj ekran ili klikne dugme "Igraj ponovo". Kada korisnik dadne tačan odgovor bodovi se povećavaju za jedan. Ako korisnik pređe na sljedeću rundu, bez da je dao tačan odgovor, broj rundi se povećava za jedan, a broj bodova ostaje isti.

- **Treći red**: 
  - Prikazuje trenutni matematički izraz koji korisnik treba da izračuna.

- **Četvrti red**: 
  - Polje za unos odgovora.

- **Peti red**: 
  - Nakon proslijeđenog odgovora ispod polja za unos se ispisuje poruka adekvatna tačnosti odgovora.

- **Dno ekrana**: 
  - Dugmić **"Sljedeća runda"**: generiše se novi matematički izraz.
  - Dugmić **"Igraj ponovo"**: svi dosadašnji podaci o igri se brišu i igra se igra ispočetka.


![correct](https://github.com/esmaagic/MiniMath/assets/108765837/44216dfe-682d-4d3e-bc20-7c583ab43085)




![landscape](https://github.com/esmaagic/MiniMath/assets/108765837/4a4302af-8ca3-4672-8386-9b3815725521)

## Opis arhitekture aplikacije

Aplikacija je razvijena koristeći Kotlin programski jezik i Jetpack Compose za izradu korisničkog layout-a. Arhitektura aplikacije slijedi Model-View-ViewModel (MVVM) arhitekturni obrazac. Glavni dijelovi arhitekture uključuju:

- **Model**: 
  - Predstavljen klasom `GameUiState` koja sadrži podatke o trenutnom stanju igre, uključujući brojeve za zbrajanje, operaciju, korisnički odgovor, trenutno vrijeme, rezultat itd.

- **View**: 
  - Definiran pomoću komponenata Jetpack Compose-a, koje uključuju različite ekrane i korisničke layout-ove poput `GameScreen`, `HomeScreen` i `GameRulesScreen`.

- **ViewModel**: 
  - `GameViewModel` je odgovoran za upravljanje poslovnim logikama i interakcijama između modela i pogleda. Sadrži logiku za generiranje matematičkih izraza, provjeru odgovora korisnika, održavanje stanja igre i upravljanje vremenom.

## Opis funkcionalnosti pojedinačnih klasa i funkcija

### `ZbrajalicaScreen` (enum)
Predstavlja moguće ekrane u aplikaciji, uključujući Početni zaslon (`Home`), Zaslon igre (`Game`) i Pravila (`Rules`).

### `ZbrajalicaAppBar` (Composable funkcija)
Komponenta koja prikazuje gornju traku aplikacije. Omogućuje korisniku odabir jezika (engleski ili bosanski). Prikazuje naziv aplikacije.

### `ZbrajalicaApp` (Composable funkcija)
Glavna komponenta aplikacije koja upravlja navigacijom između ekrana. Koristi `NavHost` za prikazivanje različitih ekrana ovisno o stanju navigacije.

### `GameViewModel`
ViewModel koji upravlja poslovnim logikama i stanjem igre. Generira matematičke izraze, provjerava korisničke odgovore, održava stanje igre i upravlja vremenom.

### `GameScreen` (Composable funkcija)
Prikazuje glavni zaslon igre. Sadrži logiku za različito postavljanje layout-a ovisno o veličini ekrana.

### `GameLayout` (Composable funkcija)
Prikazuje raspored igre koji uključuje matematički izraz, polje za unos odgovora i povratne informacije o ispravnom/neispravnom odgovoru.

### `GameButtons` (Composable funkcija)
Prikazuje dugmiće za ponovno pokretanje igre i restartovanje igre.

### `GameData` (Composable funkcija)
Prikazuje informacije o trenutnom stanju igre poput broja runde, vremena i rezultata.

### `LanguagePreference` (objekat)
#### Konstante:
- `PREFS_NAME`: Naziv datoteke za spremanje postavki jezika.
- `KEY_LANGUAGE`: Ključ pod kojim će se spremiti odabrani jezik.

#### Funkcija `setLanguage`
Omogućuje postavljanje odabranog jezika u postavkama aplikacije.

**Parametri:**
- `context`: Objekt `Context` koji se koristi za pristup dijeljenju postavki aplikacije.
- `language`: String koji predstavlja odabrani jezik.

Metoda `getSharedPreferences` dobavlja objekt `SharedPreferences` pomoću kojeg se mogu spremiti postavke aplikacije. `PREFS_NAME` predstavlja ime datoteke u kojoj će se postavke spremiti. Nakon toga, poziva se `edit()` metoda na `SharedPreferences` objektu kako bi se započelo uređivanje. Metodom `putString` postavljamo vrijednost jezika pod ključem `KEY_LANGUAGE`. Konačno, pozivom metode `apply()` promjene se primjenjuju i spremaju.

#### Funkcija `getLanguage`
Vraća odabrani jezik spremljen u postavkama aplikacije.

**Parametar:**
- `context`: Objekt `Context` koji se koristi za pristup dijeljenju postavki aplikacije.

Metoda `getSharedPreferences` dobavlja `SharedPreferences` objekt. Metodom `getString` dobavlja se vrijednost spremljena pod ključem `KEY_LANGUAGE`. Ako vrijednost nije pronađena, vraća se zadani jezik, u ovom slučaju "en" (engleski).

Ovaj objekt olakšava postavljanje i dobavljanje odabranog jezika unutar aplikacije, čime omogućuje korisnicima da prilagode jezik sučelja prema svojim željama.

**Interakcija sa životnim ciklusom aktivnosti**
Interakcija se prije svega događa prilikom dobijanja `Context` objekta. `Context` se može dobiti unutar različitih metoda životnog ciklusa aktivnosti, kao što su `onCreate`, `onResume` ili `onStart`, u zavisnosti od toga kada je potrebno postaviti ili dohvatiti jezičku preferenciju. Korištenjem `Context`-a dobijenog iz aktivnosti, objekat `LanguagePreference` osigurava da operiše unutar životnog ciklusa aktivnosti, održavajući dosljednost i izbjegavajući curenje memorije.

### `shareOrder` (funkcija)

#### Parametri funkcije:
- `context`: Objekt `Context` koji se koristi za pokretanje aktivnosti.
- `rounds`: Broj odigranih rundi u igri.
- `currentTime`: Trenutno vrijeme provedeno u igri, izraženo u sekundama.
- `score`: Rezultat igrača.

#### Priprema podataka za dijeljenje:
Definira se varijabla `subject` koja sadrži naslov poruke koja će se dijeliti. Naslov se dohvaća iz resursa aplikacije koristeći `context.getString(R.string.new_game_stats)`. Stvara se `summary` koristeći funkciju `buildString`. U `summary` se dodaju informacije o odigranim rundama, provedenom vremenu i rezultatu. Ove informacije se formatiraju i dodaju na kraj `summary` stringa.

#### Kreiranje Intenta za dijeljenje:
Stvara se objekt `Intent` s akcijom `ACTION_SEND`, što označava da korisnik želi dijeliti podatke. Tip podataka koji će se dijeliti postavljen je na "text/plain" pomoću metode `type`. U `Intent` se dodaju podaci koji će biti dijeljeni:
- `EXTRA_SUBJECT`: Naslov poruke.
- `EXTRA_TEXT`: Sadržaj poruke, odnosno `summary`.

#### Pokretanje aktivnosti za dijeljenje:
Kako bi se omogućilo korisniku odabir aplikacije za dijeljenje, koristi se `Intent.createChooser` metoda koja stvara dijaloški okvir s popisom aplikacija koje podržavaju dijeljenje teksta. Nakon odabira aplikacije, `context.startActivity` pokreće aktivnost za dijeljenje s pripremljenim podacima.

Ova funkcija omogućuje korisnicima da podijele svoje rezultate igre s drugima putem različitih aplikacija kao što su poruke, društvene mreže ili e-pošta. Na ovaj način, funkcija `shareOrder` interagira sa životnim ciklusom aktivnosti tako što koristi `Context` dobavljen iz aktivnosti kako bi pokrenula aktivnost za dijeljenje, prateći tok životnog ciklusa aplikacije i osiguravajući ispravno funkcioniranje.

## Opis opštih koncepata Android frameworka

### Aktivnost (Activity)
Predstavlja jedan zaslon korisničkog layout-a s kojim korisnici mogu interaktivno komunicirati.

### Životni ciklus (Lifecycle)
Definira različite faze u životnom vijeku aktivnosti, od stvaranja do uništenja, omogućujući programerima da upravljaju resursima i ponašanjem aplikacije.

### Intent
Objekt koji predstavlja namjeru za obavljanje određene akcije, kao što je pokretanje nove aktivnosti ili slanje poruke sustavu.

### Rasporedi (Layouts)
Definiraju strukturu i raspored elemenata korisničkog sučelja u aktivnosti ili fragmentu.

### Resursi (Resources)
Statički podaci kao što su slike, nizovi, boje, nacrti i stringovi koji se koriste u aplikaciji.

### Internacionalizacija (Internationalization)
Proces prilagodbe aplikacije za različite jezike i lokalizacije.

## Zaključak

Aplikacija Zbrajalica pruža korisnicima zabavan način za vježbanje matematičkih vještina putem Android platforme. Kroz pažljivo dizajniranu arhitekturu i korištenje suvremenih alata poput Jetpack Compose-a, aplikacija pruža intuitivno korisničko iskustvo i efikasno upravljanje poslovnim logikama. Uz jasno definirane funkcionalnosti i opće koncepte Android frameworka, aplikacija je spremna za široku upotrebu i daljnje unaprjeđenje.
