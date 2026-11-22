# \# Posao Portal

# 

# Jednostavna Java aplikacija za pronalaženje poslova, inspirisana LinkedIn-om. Napravljena u Eclipse-u kao projekat iz predmeta Objektno orijentisano programiranje.

# 

# \## Opis projekta

# 

# Aplikacija povezuje kandidate i poslodavce. Kandidat može da pregleda oglase, pretražuje ih po naslovu, lokaciji ili firmi, prijavi se na oglas i vidi svoje prijave. Poslodavac može da objavi novi oglas.

# 

# Podržana su tri tipa oglasa:

# \- \*\*Puno radno vreme\*\*

# \- \*\*Skraćeno radno vreme\*\* (plata se srazmerno računa prema broju sati nedeljno)

# \- \*\*Praksa\*\* (stipendija i trajanje u mesecima)

# 

# Aplikacija postoji u dve verzije: sa grafičkim interfejsom (Swing) i kao konzolna aplikacija.

# 

# \## Kako se pokreće

# 

# 1\. Klonirajte repozitorijum ili ga preuzmite kao ZIP.

# 2\. U Eclipse-u napravite novi Java projekat (File → New → Java Project) i kopirajte folder `src` u njega.

# 3\. Pokrenite jednu od klasa (desni klik → Run As → Java Application):

# &#x20;  - `posao.PosaoGUI` – verzija sa grafičkim interfejsom

# &#x20;  - `posao.Main` – konzolna verzija

# 

# Potrebna je Java 8 ili novija.

# 

# \## Struktura projekta

# 

# | Klasa | Opis |

# |---|---|

# | `Korisnik` (apstraktna) | Zajednički podaci: ime i email |

# | `Kandidat`, `Poslodavac` | Nasleđuju `Korisnik` |

# | `Oglas` (apstraktna) | Zajednički podaci oglasa, implementira `Pretrazivo` |

# | `FullTimeOglas`, `PartTimeOglas`, `PraksaOglas` | Nasleđuju `Oglas` |

# | `Pretrazivo` (interfejs) | Pretraga oglasa po upitu |

# | `Prijava` | Prijava kandidata na oglas sa statusom |

# | `Portal` | Čuva oglase i prijave, pretraga i prijavljivanje |

# | `Main`, `PosaoGUI` | Konzolni i grafički korisnički interfejs |

# 

# \## Primenjeni OOP principi

# 

# \- \*\*Enkapsulacija:\*\* svi atributi su `private`, a pristup ide kroz gettere i settere koji proveravaju ispravnost podataka (npr. prazno ime, email bez `@`, negativna plata).

# \- \*\*Nasleđivanje:\*\* `Korisnik` → `Kandidat`, `Poslodavac` i `Oglas` → `FullTimeOglas`, `PartTimeOglas`, `PraksaOglas`.

# \- \*\*Polimorfizam:\*\* metode `mesecnaPlata()` i `tipPosla()` su redefinisane (`@Override`) u svakom tipu oglasa i pozivaju se preko `ArrayList<Oglas>`.

# \- \*\*Apstrakcija:\*\* apstraktne klase `Korisnik` i `Oglas` i interfejs `Pretrazivo`.

# \- \*\*Kolekcije:\*\* `ArrayList` se koristi u klasi `Portal` za čuvanje oglasa i prijava.

