package domein;

import gui.Taal;

import java.util.List;
import java.util.ArrayList;

public class Spel 
{
	private String naamSpel;
	private Spelbord huidigSpelbord;
	private SpelbordRepository spelbordRepository;
	private List<Spelbord> spelbordenLijst = new ArrayList<Spelbord>();
	
	/**
     * Constructor om een spel aan te maken
     *
     * @param naamSpel de naam van het spel
     */
	public Spel(String naamSpel) 
	{
		this.setNaamSpel(naamSpel);
		spelbordRepository = new SpelbordRepository();
		
	}	
	
	/**
	 * Methode om het spelbord in te stellen
	 * 
	 * @param spelbord huidig spelbord wordt ingesteld
	 */
	public void setSpelbord(Spelbord spelbord)
	{
		this.huidigSpelbord = spelbord;
	}
	
	/**
     * Methode om een spelbord van het spel terug te geven
     *
     * @return geeft een spelbord van het spel terug
     */
	public Spelbord getSpelbord() 
	{
		return this.huidigSpelbord;
	}
	
	/**
     * Methode om een lijst van spelborden van het spel terug te geven
     *
     * @return geeft een lijst van spelborden van het spel terug
     */
	public List<Spelbord> geefSpelbordenLijst(){
		return spelbordRepository.geefSpelbordenLijst(naamSpel);
	}
	
	 /**
     * Methode om een spelbord te selecteren met bepaald spelnam
     *
     * @param spelnaam bepaalt welk naam gekozen wordt
     */
	public void selecteerSpelbord(String spelnaam) {
		for (int i = 0; i < spelbordenLijst.size(); i++) { 
			if (!spelbordenLijst.get(i).isVoltooid()) {
				huidigSpelbord = spelbordenLijst.get(i);
				break;
			}
		}
	}
	
	/**
     * Methode om het aantal spelborden van het spel terug te geven
     *
     * @return geeft het aantal spelborden van het spel terug
     */
	public int geefAantalSpelborden() 
	{
		return spelbordRepository.geefSpelbordenLijst(naamSpel).size();
	}
	
	 /**
     * Methode om het aantal voltooide spelborden terug te geven
     *
     * @return geeft het aantal voltooide spelborden terug
     */
	public int geefAantalSpelbordenVoltooid() 
	{
		int aantalVoltooid = 0;
		for (int i = 0; i< spelbordenLijst.size(); i++) 
		{
			if (spelbordenLijst.get(i).isVoltooid()) 
			{
				aantalVoltooid += 1;
			}
		}
		return aantalVoltooid;
	}
	
	/**
     * Methode om na te gaan of het spel voltooid is
     *  
     * @return geeft aan of het spel wel/niet voltooid is
     */
	public boolean isSpelVoltooid() 
	{
		int aantalVoltooid = 0;
		boolean voltooid = false;
		for (int i = 0; i< spelbordenLijst.size(); i++) 
		{
			if (spelbordenLijst.get(i).isVoltooid()) 
			{
				aantalVoltooid += 1;
			}
		}
		if (spelbordenLijst.size() == aantalVoltooid) 
		{
			voltooid = true;
		}
		return voltooid;
	}

	/**
     * Methode om na te gaan of het spelbord is voltooid
     *
     * @return geeft aan of het spelbord wel/niet voltooid is
     */
	public boolean isSpelbordVoltooid() 
	{
		return huidigSpelbord.isVoltooid();
	}
	
	/**
     * Methode om het aantal verplaatsingen terug te geven
     *
     * @return geeft het aantal verplaatsingen van het mannetje terug
     */
	public int geefAantalVerplaatsingen()
	{
		return huidigSpelbord.getAantalVerplaatsingen();
	}
	
	/**
     * Methode om de naam van het spel terug te geven
     *
     * @return geeft de naam van het spel terug
     */
	public String getNaamSpel()
	{
		return this.naamSpel;
	}
	
	/**
	 * Methode om naam van het spel in te stellen
	 * 
	 * @param naam naam van het spel dat ingesteld zal zijn
	 */
	public void setNaamSpel(String naam) 
	{
		if (naam == null || naam.length() == 0)
		{ 
		    throw new IllegalArgumentException(Taal.getText("spelnaamVerplicht"));   
		} else if (bevatSpatie(naam) == true)
		{
		    throw new IllegalArgumentException(Taal.getText("spelnaamGeenSpatie"));    
		}
		else 
		{
		this.naamSpel = naam;
		}
	}
	
	/**
	 * Methode om na te ggan of de spelnaam een spatie bevat
	 * 
	 * @param spelnaam naam van het spel
	 * @return geeft aan of naam wel/geen spatie bevat
	 */
	private boolean bevatSpatie(String spelnaam)
	{
		boolean heeftSpatie = false; 
		char c;
		
		for(int i = 0; i < spelnaam.length(); i++)
		{
			c = spelnaam.charAt(i); 
			
			if(Character.isSpaceChar(c))
			{
				heeftSpatie = true;
			}
			
		}
		return heeftSpatie;
	}
	
	 /**
     * 
     * Methode om het mannetje te verplaatsen in gegeven richting
     *
     * @param richting richting waarin het mannetje moet bewegen
     */
	public void verplaatsMan(String richting) 
	{
		huidigSpelbord.verplaatsMan(richting);
	}
	
	/**
	 * Methode om het huidige spelbord terug te geven
	 * 
	 * @return geeft huidig spelbord terug
	 */
	public Veld[][] geefSpelbord() 
	{
		return huidigSpelbord.getSpelbord();
	}
	
	/**
	 * Methode om het spelbord te tonen
	 * 
	 * @return geeft een array van karakters terug die elk de inhoud van een veld voorstellen
	 */
	public char[][] geefVelden()
	{
		return huidigSpelbord.geefVelden();
	}
	
	/**
	 * Methode om kisten van het huidig spelborde terug te geven
	 * 
	 * @return geeft kisten terug
	 */
	public List<Kist> geefKisten() 
	{
		return huidigSpelbord.getKisten();
	}
	
	/**
	 * Methode om volgnummer van het huidig spelbord terug te geven
	 * 
	 * @return geeft volgnummer terug 
	 */
	public int geefVolgnummer() 
	{
		return huidigSpelbord.getVolgnummer();
	}
	
	/**
	 * Methode om man terug te geven
	 * 
	 * @return geeft man terug
	 */
	public Man geefMan()  
	{
		return huidigSpelbord.getMan();
	}
	
	/**
	 * Methode om het spel te selecteren
	 * 
	 */
	public void selecteerSpel() {
		spelbordenLijst = geefSpelbordenLijst();
	}
	
	/**
     * Methode om een nieuw spelbord aan te maken met een volgnummer
     *
     * @param volgnummer volgnummer dat het spelbord zal toegewezen krijgen
     */
	public void maakNieuwSpelbord(int volgnummer) 
	{
		Veld[][] velden = new Veld[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				velden[i][j] = new Veld(i,j, false, false, false, false);
			}
		}
		this.huidigSpelbord = new Spelbord(volgnummer, velden);
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
		this.huidigSpelbord.wijzigSpelbord(x, y, actie);
	}
	/**
	 * Methode om het huidig spelbord in te stellen
	 * 
	 * @param spelbord geeft aan welk spelbord zal ingesteld worden
	 */
	public void setHuidigSpelbord(Spelbord spelbord)
	{
		int index = 0;
		for (int i = 0; i < spelbordenLijst.size(); i++) {
			if (!spelbordenLijst.get(i).isVoltooid()) {
				index = i;
				break;
			}
		}
		spelbordenLijst.set(index, spelbord);
		huidigSpelbord = spelbord;
	}
}