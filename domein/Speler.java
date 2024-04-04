package domein;

import gui.Taal;

public class Speler
{
	private String gebruikersnaam;
    private String wachtwoord;
    private boolean adminrechten;
    private String naam;
    private String voornaam;
    
    
    /**
     * Constructor om een speler object aan te maken met of zonder adminrechten
     * @param gebruikersnaam gebruikersnaam die de speler kiest
     * @param wachtwoord wachtwoord die de speler kiest
     * @param adminrechten kijken of de speler wel/geen adminrechten krijgt
     * @param naam familienaam van de speler 
     * @param voornaam voornaam van de speler
     */
    public Speler(String gebruikersnaam, String wachtwoord, boolean adminrechten, String naam, String voornaam)   
    {
	this.setGebruikersnaam(gebruikersnaam);
	this.setWachtwoord(wachtwoord);
	this.adminrechten = adminrechten;
	this.naam = naam;
	this.voornaam = voornaam;
    }
    
    
    public Speler(String naam, String voornaam, String wachtwoord, String gebruikersnaam, boolean adminrechten) 
    {
        this(gebruikersnaam, wachtwoord, false, naam, voornaam);
    }
    
    /**
     * Methode om de gebruikersnaam van een speler terug te geven
     * @return geeft de gebruikersnaam van de speler terug
     */
    public String getGebruikersnaam()    
    {
	return this.gebruikersnaam;
    }

    /**
     * Methode om het wachtwoord van een speler terug te geven
     * @return geeft het wachtwoord van een speler terug
     */
    public String getWachtwoord()   
    {
	return this.wachtwoord;
    }

    /**
     * Methode om te kijken of een speler adminrechten heeft
     * @return geeft terug of de speler wel/geen adminrechten heeft
     */
    public boolean isAdminrechten()    
    {
	return this.adminrechten;
    }

    /**
     * Methode om de familienaam van een speler terug te krijgen
     * @return geeft de familienaam van de speler terug
     */
    public String getNaam()    
    {
	return this.naam;
    }

    /**
     * Methode om de voornaam van een speler terug te krijgen
     * @return geeft de voornaam van de speler terug
     */
    public String getVoornaam()    
    {
	return this.voornaam;
    }

    /**
     * Methode om de gebruikersnaam van een speler in te stellen
     * @param gebruikersnaam gebruikersnaam die de speler kiest
     */
    public void setGebruikersnaam(String gebruikersnaam)   
    {
		if (gebruikersnaam == null || gebruikersnaam.length() == 0)
		{
		    throw new IllegalArgumentException(Taal.getText("gebruikersnaamVerplicht"));  
		} else if (gebruikersnaam.length() < 8)
		{
		    throw new IllegalArgumentException(Taal.getText("gebruikersnaamMinstens"));    
		}else 
		{
		this.gebruikersnaam = gebruikersnaam;
		}
    }

    /**
     * Methode om het wachtwoord van de speler in te stellen
     * @param wachtwoord wachtwoord die de speler kiest 
     */
    public void setWachtwoord(String wachtwoord)   
    {
		if (wachtwoord == null || wachtwoord.length() == 0)
		{
		    throw new IllegalArgumentException(Taal.getText("wachtwoordVerplicht"));   
		} else if (isCorrectWachtwoord(wachtwoord) == false)
		{
		    throw new IllegalArgumentException(Taal.getText("wachtwoordMinstens"));   
		} else 
		{
		this.wachtwoord = wachtwoord;
	    }
	}

    /**
     * @param wachtwoord ingegeven wachtwoord dat moet gecontroleerd worden op geldigheid
     * @return geeft boolean terug of het wachtwoord geldig is voor de gewenste parameters.
     */
    private boolean isCorrectWachtwoord(String wachtwoord)   
    {
    	if(wachtwoord.length() > 7)   
    	{
    		if(checkWachtwoord(wachtwoord))   
    		{
    			return true;
    		}
    		else
    		{
    			return false;
    		}
    	}
    	else 
    	{
    		return false;
    	}
    }
    
    /**
     * Methode om na te gaan of het wachtwoord een kleine, grote letter heeft en een cijfer.
     * 
     * @param wachtwoord ingegeven dat moet gecontroleerd worden op geldigheid
     * @return geeft boolean terug of het wachtwoord geldig is voor de gewenste parameters
     */
    private static boolean checkWachtwoord(String wachtwoord)     
    {
    	boolean heeftNummer = false; 
    	boolean heeftHoofdletter = false; 
    	boolean heeftKleineLetter = false; 
    	char c;
    	
    	for(int i = 0; i < wachtwoord.length(); i++)
    	{
    		c = wachtwoord.charAt(i);    
    		
    		if(Character.isDigit(c))    
    		{
    			heeftNummer = true;
    		}
    		else if(Character.isUpperCase(c))   
    		{
    			heeftHoofdletter = true;
    		}
    		else if(Character.isLowerCase(c))   
    		{
    			heeftKleineLetter = true;
    		}
    		if(heeftNummer && heeftHoofdletter && heeftKleineLetter)   
    		{
    			return true;
    		}
    	}
    	return false;
    }
}