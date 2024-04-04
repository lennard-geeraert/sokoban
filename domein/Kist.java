package domein;

public class Kist 
{
	private Veld veld;
	
	/**
	 * Constructor  om een Kist aan te maken
	 * @param veld parameter van de contstructor
	 */
	public Kist(Veld veld) 
	{
		setVeld(veld);
	}
	
	 /**
     * Methode om het veld voor een kist in te stellen
     * @param veld object van de klasse veld waarop de kist moet staan
     */
	public void setVeld(Veld veld)   
	{
		this.veld = veld;
	}
	
	/**
     * Methode om het veld waarop een kist staat terug te geven
     * @return geeft het veld terug waarop een kist staat
     */
	public Veld getVeld()  
	{
		return veld;
	}

}