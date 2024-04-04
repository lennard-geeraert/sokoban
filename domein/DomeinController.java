package domein;

import gui.Taal;


/**
 * Date: 10/05/2020
 * @author lenna
 * @version 1.0
 *
 */
public class DomeinController
{
	
	/**
	 * SpelerRepository
	 */
	private final SpelerRepository spelerRepository;
	/**
	 * SpelRepository
	 */
	private final SpelRepository spelRepository;
	/**
	 * Speler
	 */
	private Speler speler;
	/**
	 * Spel
	 */
	private Spel spel;
	/**
	 * SpelbordRepository
	 */
	private final SpelbordRepository spelbordRepository;
	
	
	/**
	 * 
	 *  Constructor om een DomeinController aan te maken
	 */
	public DomeinController()   
	{
		this.spelRepository = new SpelRepository();
		spelerRepository = new SpelerRepository(); 
		spelbordRepository = new SpelbordRepository();		
	}
	
	/**
	 * Methode om de gebruikersnaam van een speler terug te gegven
	 * 
	 * @return geeft de gebruikersnaam van een speler terug
	 */
	public String geefGebruikersnaam()    
	{
		return this.speler.getGebruikersnaam();
	}
	
	/**
	 * Methode om de speler in stellen die het spel zal spelen
	 * 
	 * @param speler object van de klasse Speler die het spel zal spelen
	 */
	private void setSpeler(Speler speler)   
	{
		this.speler = speler;
	}
	
	/**
     * Methode om te kunnen aanmelden
     *
     * @param gebruikersnaam gebruikersnaam van de persson die zich wil
     * aanmelden
     * @param wachtwoord wachtwoord van de persoon die zich wil aanmelden
     */
	public void meldAan(String gebruikersnaam, String wachtwoord)    
	{
		if(gebruikersnaam == null || gebruikersnaam.length() == 0)
			throw new IllegalArgumentException(Taal.getText("gebruikersnaamVerplicht"));
		else if(wachtwoord == null || wachtwoord.length() == 0)
			throw new IllegalArgumentException(Taal.getText("wachtwoordVerplicht"));
			
        Speler gevondenSpeler = spelerRepository.geefSpeler(gebruikersnaam, wachtwoord);

        if (gevondenSpeler != null) 
        {
            setSpeler(gevondenSpeler);
        } else 
        {
            throw new IllegalArgumentException(Taal.getText("fouteLoginMelding"));
        }
	}
	
	/**
     * Methode om te kijken of de speler een admin is
     *
     * @return geeft terug of de speler wel/geen admin is
     */
    public boolean isAdmin()    
    {
        return this.speler.isAdminrechten();
    }

    /**
     * Methode om te kunnen registreren
     *
     * @param gebruikersnaam gekozen gebruikersnaam door de gebruiker
     * @param wachtwoord gekozen wachtwoord door de gebruiker
     * @param adminrechten boolean om aan te duiden of de gebruiker adminrechten
     * heeft. Standaard op false
     * @param naam achternaam van de gebruiker
     * @param voornaam voornaam van de gebruiker
     */
    public void registreer(String gebruikersnaam, String wachtwoord, boolean adminrechten, String naam, String voornaam)
    {
        Speler nieuweSpeler = new Speler(gebruikersnaam, wachtwoord, adminrechten, naam, voornaam);
        setSpeler(nieuweSpeler);
        spelerRepository.voegToe(nieuweSpeler);
    }
    
    /**
     * Methode om de namen van de spellen terug te geven
     * 
     * @return geeft de namen van de spellen terug
     */
    public String[] geefLijstSpellen()    
    {
    	String[] namen = new String[spelRepository.geefSpellenList().size()];
    	for (int i = 0; i < namen.length; i++)
    	{
    		namen[i] = spelRepository.geefSpellenList().get(i);
    	}
    	return namen;
    }
    
    /**
     * Methode om een spel te selecteren met een bepaald naam
     * 
     * @param naam naam van het spel dat gekozen wordt.
     */
    public void selecteerSpel(String naam)
    {
        this.spel = spelRepository.geefSpel(naam);      
        spel.selecteerSpel();
    }
     
    /**
     * 
     * Methode om  aantal spelborden terug te geven
     * 
     * @return geeft het aantal spelborden terug
     */
    public int geefAantalSpelborden()
    {
    	return this.spel.geefAantalSpelborden();      
    }
    
    /**
     * Methode om het aantal voltooide spelborden weer te geven
     *
     * @return geeft het aantal voltooide spelborden terug
     */
    public int geefAantalSpelbordenVoltooid()
    {
    	return this.spel.geefAantalSpelbordenVoltooid();       
    }      
    /**
     * Methode om de velden te tonen
     * 
     * @return geeft de soort van de velden terug aan de hand van karakters
     */
    public char[][] geefVelden() 
    {
    	return this.spel.geefVelden();
    }
    /**
     * Methode om het eerste niet-voltooide spelbord te selecteren van een bepaald spel
     * 
     * @param spelnaam naam van het spelbord dat gekozen wordt
     */
    public void selecteerSpelbord(String spelnaam) {
    	this.spel.selecteerSpelbord(spelnaam);
    }
    
    /**
     * Methode om de man te verplaatsen in een bepaalde richting
     * 
     * @param richting geeft de richting waarnaar de man zicht moet verplaatsen
     */
    public void verplaatsMan(String richting) 
    {
    	this.spel.verplaatsMan(richting);
    }
    
    /**
     * Methode om het spelbord te resetten
     * 
     * @param spelnaam de naam van het spel
     * @param volgnummer het volgnummer van het te resetten spelbord
     */
    public void resetSpelbord(String spelnaam, int volgnummer) 
    {
		Spelbord huidigSpelbord = spelbordRepository.geefSpelbordMetVolgnummer(volgnummer, spelnaam);
		spel.setHuidigSpelbord(huidigSpelbord);
    }
    
    /**
     * Methode om het aantal verplaatsingen terug te geven
     * 
     * @return geeft het aantal verplaatsingen van het mannetje terug
     */
    public int geefAantalVerplaatsingen()   //methode om het aantal verplaatsingen terug te geven
    {
    	return this.spel.geefAantalVerplaatsingen();
    }
    
    /**
     * Methode om na te gaan of het spelbord voltooid is
     * 
     * @return isVoltooid geeft aan of het spelbord wel/niet voltooid is
     */
    public boolean eindeSpelbordBereikt()  //methode om te kijken of het einde van het spelbord bereikt is
    {
    	if(spel.isSpelbordVoltooid() == true)
    	{
    		return true;
    	}
    	return false;
    }
    
    /**
     * Methode om een nieuw spel aan te maken met een bepaalde naam
     * 
     * @param naamSpel naam dat het spel zal krijgen
     * @param gebruikersnaam de gebruiker die het spel aanmaakt
     */
    public void maakNieuwSpel(String naamSpel, String gebruikersnaam) 
    {
    	Spel nieuwSpel = new Spel(naamSpel);
    	spelRepository.voegSpelToe(naamSpel, gebruikersnaam);
    	this.spel = nieuwSpel;
    } 
    /**
     * Methode die het huidig spel terug geeft
     * 
     * @return geeft het huidig spel terug
     */
    public Spel geefHuidigSpel()
    {
    	return this.spel;
    }
    
    /**
     * Methode om het spel te verwijderen met bepaalde naam
     * 
     * @param naamSpel gekozen naam voor het verwijderen van het spel
     */
    public void verwijderSpel(String naamSpel)
    {
    	this.spelRepository.verwijderSpel(naamSpel);
    }
    
    /**
     * Methode om het spelbord te verwijderen met bepaalde naam en volgnummer
     * 
     * @param volgnummer volgnummer van het spel dat verwijdert moet worden
     * @param naamSpel gekozen naam van het spel dat verwijdert moet worden
     */
    public void verwijderSpelbord(int volgnummer, String naamSpel)
    {
    	this.selecteerSpel(naamSpel);
    	if (this.geefAantalSpelborden() > 1)
    		this.spelbordRepository.verwijderSpelbord(volgnummer, naamSpel);
    	else
    		throw new IllegalArgumentException(Taal.getText("minstens1Spelbord"));
    }
    /**
     * Methode om naam van het spel terug te geven
     * 
     * @return geeft naam van spel terug
     */
    public String geefNaamSpel() 
    {
    	return this.spel.getNaamSpel();
    }
    
    /**
     * Methode om na te gaan of het spel voltooid is
     *  
     * @return geeft aan of het spel wel/niet voltooid is
     */
    public boolean isSpelVoltooid() 
    {
    	return this.spel.isSpelVoltooid();
    }
    
    /**
     * Methode om een nieuw spelbord aan te maken met een bepaald volgnummer
     * 
     * @param volgnummer volgnummer dat het spelbord zal toegewezen krijgen
     */
    public void maakNieuwSpelbord(int volgnummer) 
    {
    	this.spel.maakNieuwSpelbord(volgnummer);	
    }
    
    /**
     * Methode om het spelbord te wijzigen met het aantal rijen en kolommen 
     * 
     * @param x x-coordinaat(rij) dat zal wijzigen
     * @param y y-coordinaat(kolom) dat zal wijzigen
     * @param actie beschikbare keuze om het spelbord te wijzigen(doel,man,muur,kist,veld)
     */
    public void wijzigSpelbord(int x, int y, int actie) 
    {
    	this.spel.wijzigSpelbord(x, y, actie);
    }
    
    /**
     * Methode om een spelbord toe te voegen aan een spel.
     *
     * @param velden de karakters die per veld voorstellen wat er in staat
     * @param volgnummer het volgnummer van het spelbord
     * @param spelnaam de spelnaam van het spel
     */
    public void voegSpelbordToe(char[][] velden, int volgnummer, String spelnaam) 
    {
    	controleerSpelbord(velden, volgnummer, spelnaam);
    	this.spel.geefSpelbordenLijst().add(this.spel.getSpelbord());
    	this.spelbordRepository.voegSpelbordToe(velden, volgnummer,spelnaam);
    }
    
    
    /**
     * 
     * Methode om het spelbord te controleren
     *
     * @param velden de karakters die per veld voorstellen wat er in staat
     * @param volgnummer het volgnummer van het spelbord
     * @param spelnaam de spelnaam van het spel
     */
    private void controleerSpelbord(char[][] velden, int volgnummer, String spelnaam)
    {
    	int aantalKisten = 0;
    	int aantalDoelen = 0;
    	int aantalMannen = 0;
    	for (int i = 0; i < 10; i++)
    	{
    		for (int j = 0; j < 10; j++)
    		{
    			if (velden[i][j] == 'K')
    				aantalKisten++;
    			if (velden[i][j] == '?')
    				aantalDoelen++;
    			if (velden[i][j] == 'M')
    				aantalMannen++;
    		}
    	}
    	if (aantalMannen == 0)
    		throw new IllegalArgumentException(Taal.getText("manVerplicht"));
    	else if (aantalMannen > 1)
    		throw new IllegalArgumentException(Taal.getText("max1Man"));
    	if (aantalKisten != aantalDoelen)
    		throw new IllegalArgumentException(Taal.getText("aantalKisten"));
    	else if (aantalKisten < 1 || aantalDoelen < 1)
    		throw new IllegalArgumentException(Taal.getText("minstens1KistEnDoel"));
    }
    
    /**
     * Methode om het volgnummer van het spelbord terug te geven
     * 
     * @return geeft volgnummer terug
     */
    public int geefVolgnummer() 
    {
    	return this.spel.geefVolgnummer();
    }
    
    /**
     * Methode om volgnummer van een spelbord met een bepaalde spelnaam terug te geven
     * 
     * @param spelnaam naam dat gekozen wordt
     * @return geeft de volgnummers van van spelborden terug
     */
    public int[] geefVolgnummerSpelborden(String spelnaam)    //array maken van namen van spellen
    {
    	
          int[] namen = new int[spelbordRepository.geefSpelbordenLijst(spelnaam).size()];      
          for(int i = 0; i < spelbordRepository.geefSpelbordenLijst(spelnaam).size(); i++) 
          {
        	  namen [i] = spelbordRepository.geefSpelbordenLijst(spelnaam).get(i).getVolgnummer();    
          }
          
          return namen;
    }
    
    /**
     * Methode om een spelborde met volgnummer te selecteren met een bepaalde volgnummer en spelnaam
     * 
     * @param volgnummer volgnummer dat gekozen wordt voor het selecteren van het spelbord
     * @param spelnaam naam dat gekozen wordt voor het selecteren van het spelbord
     */
    public void selecteerSpelbordMetVolgnummer(int volgnummer, String spelnaam)
    {
    	Spelbord spelbord = spelbordRepository.geefSpelbordMetVolgnummer(volgnummer, spelnaam);
    	spel.setHuidigSpelbord(spelbord);
    }
    
    /**
     * Methode om het spelbord up te daten nadat een wijziging is toegebracht
     * 
     * @param velden de karakters die per veld voorstellen wat er in staat
     * @param volgnummer het volgnummer van het spelbord
     * @param spelnaam de spelnaam van het spel
     */
    public void updateSpelbord(int volgnummer, char[][] velden, String spelnaam)
    {
    	controleerSpelbord(velden, volgnummer, spelnaam);
    	spelbordRepository.updateSpelbord(volgnummer, velden, spelnaam);
    }
    
    /**
     * Methode om lijst van spellen van de speler van bepaalde gebruikersnaam terug te geven
     * 
     * @param gebruikersnaam naam van de gebuiker die ingelogd is
     * @return geeft de lijst van namen van spellen terug
     */
    public String[] geefLijstSpellenSpeler(String gebruikersnaam)
    {
    	String[] namen = new String[spelRepository.geefLijstSpellenSpeler(gebruikersnaam).size()];
    	for (int i = 0; i < namen.length; i++)
    	{
    		namen[i] = spelRepository.geefLijstSpellenSpeler(gebruikersnaam).get(i);
    	}
    	return namen;
    }
}