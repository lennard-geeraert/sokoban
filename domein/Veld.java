package domein;

public class Veld 
{
	private boolean isMuur;
	private boolean isDoel;
	private int x;
	private int y;
	private boolean isKist;
	private boolean isMan;
	
	

	 /**
    * Constructor om een veld aan te maken met man of kist op
    * @param x coordinaat van het veld op de x-as(rij)
    * @param y coordinaat van het veld op de y-as(kolom)
    * @param doel kijken of het veld een kist moet bevatten om het spel te voltooien
    * @param muur kijke of het veld een muur moet bevatten
    * @param man kijken of het veld een man moet bevatten
    * @param kist kijken of het veld een kist moet bevatten
    */
	public Veld(int x, int y, boolean doel, boolean muur, boolean man, boolean kist) 
	{
		setX(x);
		setY(y);
		setIsDoel(doel);
		setIsMuur(muur);
		setIsMan(man);
		setIsKist(kist);
	}
	
	/**
	 * Methode om na te gaan of het een man is of niet 
	 * 
	 * @return geeft man terug
	 */
	public boolean isMan() 
	{
		return isMan;
	}
	
	/**
	 * Methode om op het veld een object van man te plaatsen
	 * 
	 * @param man object van klasse man
	 */
	public void setIsMan(boolean man) 
	{
		this.isMan = man;
	}
	
	/**
	 * Methode om na te gaan of een kist is of niet
	 * 
	 * @return geeft kist terug
	 */
	public boolean isKist() 
	{
		return isKist; 
	}
	
	/**
	 * Methode om op het veld een object van kist te plaatsen
	 * 
	 * @param kist geeft aan of het veld al dan niet een kist bevat
	 */
	public void setIsKist(boolean kist) 
	{
		this.isKist = kist;
	}

	/**
	 * Methode om na te gaan of het een muur is of niet
	 * 
	 * @return geeft terug of er al dan niet een muur in het veld staat
	 */
	public boolean isMuur() 
	{
		return isMuur;
	}
	
	 /**
     * Methode om te kijken of een veld een kist moet bevatten om het spel te voltooien
     * @return geeft aan of het veld wel/geen doel is
     */
	public boolean isDoel() 
	{
		return isDoel;
	}
	
	/**
	 * Methode om op het veld een object van muur te plaatsen
	 * 
	 * @param isMuur object van klasse Veld
	 */
	public void setIsMuur(boolean isMuur) 
	{
		this.isMuur = isMuur;
	}
	
	/**
	 * Methode om op het veld een object van doel te plaatsen
	 * 
	 * @param isDoel object van klasse Veld
	 */
	public void setIsDoel(boolean isDoel) 
	{
		this.isDoel = isDoel;
	}
	
	/**
     * Methode om de x-coordinaat van een veld terug te geven(rij)
     * @return geeft de x-coordinaat van een veld terug
     */
	public int getX() 
	{
		return x;
	}
	
	/**
	 * Methode om de x-coordinaat van een veld in te stellen
	 * 
	 * @param x x-coordinaat(rij) in te stellen
	 */
	public void setX(int x) 
	{
		this.x = x;
	}
	

	/**
     * Methode om de y-coordinaat van een veld terug te geven(kolom)
     * @return geeft de y-coordinaat van een veld terug 
     */
	public int getY() 
	{
		return y;
	}
	
	/**
	 * Methode om de y-coordinaat van een veld in te stellen
	 * 
	 * @param y y-coordinaat(kolom) in te stellen
	 */
	public void setY(int y) 
	{
		this.y = y;
	}
}
