package domein;

import gui.Taal;
import persistentie.SpelerMapper;

public class SpelerRepository
{
	private final SpelerMapper spelerMapper;	
	
	/**
     * Constructor om een SpelerRepository aan te maken
     */
	public SpelerRepository()
	{
		spelerMapper = new SpelerMapper();
	}
	
	/**
     * Methode om de huidige speler terug te geven
     * @param gebruikersnaam gebruikersnaam van persoon die zich wil aanmelden
     * @param wachtwoord wachtwoord van de persoon die zich wil aanmelden
     * @return geeft een speler object terug als speler niet null is en wachtwoord gelijk is aan wachtwoord van databank
     */
    public Speler geefSpeler(String gebruikersnaam, String wachtwoord)  
    {
    	Speler speler = spelerMapper.geefSpeler(gebruikersnaam);    

    	if (speler != null)    
    	{
    	    if (speler.getWachtwoord().equals(wachtwoord))
    	    {
    		return speler;
    	    }
    	}
    	return null;
    }
    
    /**
     * Methode om na te kijken of de speler niet al reeds bestaat in de databank
     * @param gebruikersnaam gebruikersnaam waarvan we willen weten of de speler al bestaat
     * @return geeft een boolean terug om aan te duiden of de speler al dan niet bestaat
     */
    private boolean bestaatSpeler(String gebruikersnaam)    
    {
    	return spelerMapper.geefSpeler(gebruikersnaam) != null;
    }

    /**
     * Methode om een speler toe te voegen in de databank
     * @param speler spelerobject dat aangemaakt moet worden in de databank
     */
    public void voegToe(Speler speler)    
    {
		if (bestaatSpeler(speler.getGebruikersnaam()))
		{
		    throw new IllegalArgumentException(Taal.getText("spelerBestaat"));
		}
		spelerMapper.voegToe(speler);
    }
    
    
}