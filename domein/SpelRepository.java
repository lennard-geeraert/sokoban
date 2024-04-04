package domein;

import java.util.ArrayList;

import java.util.List;

import gui.Taal;
import persistentie.SpelMapper;

public class SpelRepository
{
	private SpelMapper spelMapper;	
    
	
	/**
	 * Conctructor om SpelRepository aan te maken
	 * 
	 */
    public SpelRepository()
    {
        spelMapper = new SpelMapper();
    }
    
    /**
     * Methode om een spel met bepaalde naam terug te geven
     *
     * @param naam  van het spel
     * @return geeft het spel met bepaalde naam terug
     */
    public Spel geefSpel(String naam)
    { 
        Spel spel = spelMapper.geefSpel(naam);
        if(spel != null) 
        {
            return spel;
        }
        return null;
    }
    
    /**
     * Methode om na te gaan of het spel bestaat of niet
     * 
     * @param spelnaam naam van het spel
     * @return geeft het spel terug
     */
    private boolean bestaatSpel(String spelnaam) 
    {
    	return spelMapper.geefSpel(spelnaam) != null;
    }
    
    /**
     * Methode om een spel toe te voegen aan het attribuut spellen
     * @param naamSpel de naam van het spel dat wordt aangemaakt
     * @param gebruikersnaam de gebruikersnaam van de persoon die het spel aanmaakt
     */
    public void voegSpelToe(String naamSpel, String gebruikersnaam)    
    {
		if (bestaatSpel(naamSpel))
		{
		    throw new IllegalArgumentException(Taal.getText("spelBestaat"));
		}
		spelMapper.voegSpelToe(naamSpel, gebruikersnaam);
    }

    /**
     * Methode om een lijst met spellen terug te geven
     *
     * @return geeft een lijst met alle spellen terug
     */
    public List<String> geefSpellenList()      
    {
    	return spelMapper.geefSpellen();
    }
    
    
    /**
     * Methode om lijst van spellen van de speler van bepaalde gebruikersnaam terug te geven
     * 
     * @param gebruikersnaam naam van de gebuiker die ingelogd is
     * @return geeft de lijst van namen van spellen terug
     */
    public List<String> geefLijstSpellenSpeler(String gebruikersnaam)
    {
    	return spelMapper.geefSpellenSpeler(gebruikersnaam);
    }
    
    
    /**
     * Methode om het spel te verwijderen met bepaalde naam
     * 
     * @param naamSpel gekozen naam voor het verwijderen van het spel
     */
    public void verwijderSpel(String naamSpel)
    {
    	spelMapper.verwijderSpel(naamSpel);
    }
    	
}