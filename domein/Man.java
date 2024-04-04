package domein;

public class Man 
{

	private Veld veld;
	/**
	 * Contructor om een Man aan te maken
	 * 
	 * @param veld parameter van de contructor
	 */
	public Man(Veld veld)
	{
		setVeld(veld);
	}
	
	/**
     * Methode om het veld voor een man in te stellen
     * @param veld object van de klasse veld waarop de man moet staan
     */
	public void setVeld(Veld veld)  
	{
		this.veld = veld;
	}
	
	/**
     * Methode om het veld waarop een man staat terug te geven
     * @return geeft het veld terug waarop een man staat
     */ 
	public Veld getVeld()   
	{
		return veld;
	}

}