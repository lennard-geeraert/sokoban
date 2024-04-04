package domein;

import java.util.List;
import java.util.ResourceBundle;
import java.util.ArrayList;

import domein.Veld;
import gui.Taal;

public class Spelbord 
{
	private boolean isVoltooid;
	private int aantalVerplaatsingen;
	private int locatieManX=-1; //locatie van de rij
	private int locatieManY=-1; //locatie van de kolom
	private int volgnummer;
	
	private Veld[][] spelbord; //[[Veld(x, y)][Veld(x, y][Veld(x, y)]]
	private ArrayList<Kist> kisten = new ArrayList<Kist>();
	private Man man;
	
	 /**
     * Constructor om een spelbord aan te maken
     *
     * @param volgnummer volgnummer van het spelbord in het huidige spel
     * @param velden velden meegeven die het spelbord bevat, al dan niet
     * ingevuld
     */
	public Spelbord(int volgnummer, Veld[][] velden) 
	{
		this.volgnummer = volgnummer;
		this.isVoltooid = false;
		this.aantalVerplaatsingen = 0;
		this.spelbord = velden;

		for (int i = 0; i<10; i++) {
			for (int j = 0; j<10; j++) 
			{
				if (spelbord[i][j].isMan()) 
				{
					man = new Man(spelbord[i][j]);
				}
				else if (spelbord[i][j].isKist()) 
				{
					Kist kist = new Kist(spelbord[i][j]);
					kisten.add(kist);
				}
			}
		}
	}
	
	/**
     * Methode om het volgnummer van het spelbord terug te geven
     *
     * @return geeft het volgnummer van het spelbord terug
     */
	public int getVolgnummer()  
	{
		return this.volgnummer;
	}
	
	/**
     * Methode om het volgnummer te zetten van het spelbord dat gespeeld moet worden
     * @param volgnummer geeft aan welk spelbord er gespeeld zal worden
     */
	public void setVolgnummer(int volgnummer) 
	{
		this.volgnummer = volgnummer;
	}
	
	/**
	 * Methode om een lijst van velden van kisten aan te maken
	 * 
	 * @return geeft lijst van velden van kiste terug
	 */
	public List<Veld> maakVeldenVanKistenLijst() 
	{
		List<Veld> veldenVanKistenLijst = new ArrayList<Veld>();
		if (kisten.size() != 0) {
			for (int i = 0; i<kisten.size();i++) 
			{
		    	veldenVanKistenLijst.add(kisten.get(i).getVeld()); 
		    }
		}
	    return veldenVanKistenLijst;
	}
	
	/**
	 * Methode om locatie van man te bepalen
	 * 
	 */
	private void bepaalLocatieMan() 
	{
		locatieManX = getMan().getVeld().getX();
		locatieManY = getMan().getVeld().getY();
	}
	
	/**
	 * Methode om kisten terug te geven
	 * 
	 * @return geeft kisten terug
	 */
	public List<Kist> getKisten() 
	{
		return kisten;
	}

	/**
	 * Methode om een man terug te geven
	 * 
	 * @return geeft man terug
	 */
	public Man getMan() 
	{
		return man;	
	}
	
	/**
	 * Methode om spelborden terug te geven
	 * 
	 * @return geeft spelbord terg
	 */
	public Veld[][] getSpelbord() 
	{
		return spelbord;
	} 
	
	/**
	 * Methode om het aatal verplaatsingen terug te geven
	 * 
	 * @return geeft aantal verplaatsingen terug
	 */
	public int getAantalVerplaatsingen() 
	{
		return this.aantalVerplaatsingen;
	}
	
	/**
	 * Methode om het aantal verplaatsingen in te stellen
	 * 
	 * @param aantalVerplaatsingen  het aantalVerplaatsingen dat gekozen wordt 
	 */
	public void setAantalVerplaatsingen(int aantalVerplaatsingen) 
	{
		this.aantalVerplaatsingen = aantalVerplaatsingen;
	}

	/**
	 * Methode om na te gaan of het spelbord voltooid is
	 * 
	 * @return geeft aan of het spelbord wel/niet voltooid is
	 */
	public boolean isVoltooid()
	{
		return this.isVoltooid;
	}
	
	/**
	 * Methode om het spelbord in te stellen of het voltooid is of niet
	 * 
	 * @param isVoltooid geeft aan of het volooid is of niet
	 */
	public void setIsVoltooid(boolean isVoltooid) 
	{
		this.isVoltooid = isVoltooid;
	} 

	/**
     * Methode om het spelbord weer te geven
     *
     *
     *@return geeft een array van verschillende karakters aan de hand van de inhoud van de velden
     */
	public char[][] geefVelden() 
    {
        char[][] velden = new char[10][10];
        
        for (int i = 0; i < 10; i++) 
        {
            for (int j = 0; j < 10; j++) 
            {
                if (spelbord[i][j].isMuur()) 
                {
                	velden[i][j] = 'X'; //veld met een muur op is W (wall)
                } 
                else if (man != null && spelbord[i][j] == man.getVeld()) 
                {
                	velden[i][j] = 'M';//veld dat een man bevat (Man)
                } 
                else if (spelbord[i][j].isDoel() && maakVeldenVanKistenLijst().contains(spelbord[i][j])) 
                {
                	velden[i][j] = 'O'; //veld met een doel en een kist op is F (Finished)
                } 
                else if (spelbord[i][j].isDoel()) 
                {
                	velden[i][j] = '?';//veld met doel(Goal)
                }
                else if (!spelbord[i][j].isDoel() && !maakVeldenVanKistenLijst().contains(spelbord[i][j])) 
                {
                	velden[i][j] = '.';//Leeg veld(Nothing)
                } 
                else if (maakVeldenVanKistenLijst().contains(spelbord[i][j])) 
                {
                	velden[i][j] = 'K';//veld met een kist (Kist)
                }    
            }            
        }
        return velden;
    }
	
	/**
     * Methode om de man te verplaatsen in een richting
     *
     * @param richting geeft de richting aan waarnaar de man zich verplaatst
     */
	public void verplaatsMan(String richting) 
	{
		bepaalLocatieMan();
		
    	if (isVerplaatsingOK(richting)) 
    	{
    		aantalVerplaatsingen += 1;
    		int verplaatsingX = 0;
    		int verplaatsingY = 0;
    		if (richting.equals("links")) 
    		{
    			verplaatsingY = -1;
    		} 
    		else if (richting.equals("rechts")) 
    		{
    			verplaatsingY = +1;
    		}
    		else if (richting.equals("omhoog")) 
    		{
    			verplaatsingX = -1;
    		}
    		else if (richting.equals("omlaag")) 
    		{
    			verplaatsingX = +1;
    		}
    		getMan().setVeld(spelbord[locatieManX+verplaatsingX][locatieManY+verplaatsingY]);
			if (maakVeldenVanKistenLijst().contains(spelbord[locatieManX+verplaatsingX][locatieManY+verplaatsingY])) 
			{
				for (int i = 0; i < kisten.size(); i++) 
				{
					if (spelbord[locatieManX+verplaatsingX][locatieManY+verplaatsingY] == kisten.get(i).getVeld()) 
					{
						kisten.get(i).setVeld(spelbord[locatieManX+verplaatsingX*2][locatieManY+verplaatsingY*2]);
						break;
					}
				}
			}
    	}	
    	isEindeSpelbordBereikt();
    }
		
	/**
	 * Methode om na te gaan of het spelbord wel/niet bereikt is
	 * 
	 */
	private void isEindeSpelbordBereikt() 
	{
		int aantalDoelen = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++ ) {
				if(spelbord[i][j].isDoel()) 
				{
					aantalDoelen += 1;
				}
			}
		}
		int aantalKistenOpDoel = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if(maakVeldenVanKistenLijst().contains(spelbord[i][j]) && spelbord[i][j].isDoel()) 
				{
					aantalKistenOpDoel += 1;
				}
			}
		}
		if (aantalKistenOpDoel == aantalDoelen) {
			this.isVoltooid = true;
		}
	}
	
	/**
	 * Methode om na te gaan of de verplaatsing van de man ok is of niet
	 * 
	 * @param richting geeft de richting waar de man naar toe verplaatst wordt
	 * @return geeft of de verplaatsing wel/niet ok is
	 */
	public boolean isVerplaatsingOK(String richting) 
	{
		bepaalLocatieMan();
		boolean verplaatsingOk = false;
		int verplaatsingX = 0;
		int verplaatsingY = 0;
		int grens = 0;
	
    	if (richting.equals("links")) 
    	{
    		verplaatsingY = -1;
    		grens = 0;
    	}
    	
    	if (richting.equals("rechts")) 
    	{
    		verplaatsingY = 1;
    		grens = 9;
    	}
    	if (richting.equals("omhoog")) 
    	{
    		verplaatsingX = -1;
    		grens = 0;
    	}
    	if (richting.equals("omlaag")) 
    	{
    		verplaatsingX = 1;
    		grens = 9;
    	}
    	if (richting.equals("omhoog") || richting.equals("omlaag")) {
	    	if ( !(locatieManX == grens || spelbord[locatieManX + verplaatsingX][locatieManY + verplaatsingY].isMuur()
					|| (maakVeldenVanKistenLijst().contains(spelbord[locatieManX + verplaatsingX][locatieManY + verplaatsingY]) && maakVeldenVanKistenLijst().contains(spelbord[locatieManX + verplaatsingX*2][locatieManY + verplaatsingY*2]) ) 
					|| (maakVeldenVanKistenLijst().contains(spelbord[locatieManX + verplaatsingX][locatieManY + verplaatsingY]) && spelbord[locatieManX + verplaatsingX*2][locatieManY + verplaatsingY*2].isMuur()) 
					|| (maakVeldenVanKistenLijst().contains(spelbord[locatieManX + verplaatsingX][locatieManY + verplaatsingY]) && locatieManX == grens-verplaatsingX) ) )
			{
				verplaatsingOk = true;
			}
    	}
    	if (richting.equals("links") || richting.equals("rechts")) {
	    	if ( !(locatieManY == grens || spelbord[locatieManX + verplaatsingX][locatieManY + verplaatsingY].isMuur()
					|| (maakVeldenVanKistenLijst().contains(spelbord[locatieManX + verplaatsingX][locatieManY + verplaatsingY]) && maakVeldenVanKistenLijst().contains(spelbord[locatieManX + verplaatsingX*2][locatieManY + verplaatsingY*2]) ) 
					|| (maakVeldenVanKistenLijst().contains(spelbord[locatieManX + verplaatsingX][locatieManY + verplaatsingY]) && spelbord[locatieManX + verplaatsingX*2][locatieManY + verplaatsingY*2].isMuur()) 
					|| (maakVeldenVanKistenLijst().contains(spelbord[locatieManX + verplaatsingX][locatieManY + verplaatsingY]) && locatieManY == grens-verplaatsingY) ) )
			{
				verplaatsingOk = true;
			}
    	}
    	if (!verplaatsingOk)
    		throw new IllegalArgumentException(Taal.getText("ongeldigeRichting"));
    	return verplaatsingOk;
    }
	
	 /**
     * Methode om het spelbord te wijzigen 
     * 
     * @param x x-coordinaat(rij) dat zal wijzigen
     * @param y y-coordinaat(kolom) dat zal wijzigen
     * @param actie beschikbare keuze om het spelbord te wijzigen(doel,man,muur,kist,veld)
     */
	public void wijzigSpelbord(int x, int y, int actie) 
	{
		if(actie < 1 || actie > 6)
		{
			throw new IllegalArgumentException(Taal.getText("ongeldigActie"));
		}
		if(x < 0 || x > 9)
		{
			throw new IllegalArgumentException(Taal.getText("rijBuitenGrens"));
		}
		if(y < 0 || y > 9)
		{
			throw new IllegalArgumentException(Taal.getText("kolomBuitenGrens"));
		}
		else if(actie == 1) 
		{
			spelbord[x][y].setIsDoel(true);
		}
		else if (actie == 2) 
		{
			spelbord[x][y].setIsMuur(true);
		}
		else if (actie == 3) 
		{	
			this.man = new Man(spelbord[x][y]);
            spelbord[x][y].setIsMan(true);
		}
		else if (actie == 4) 
		{
			Kist kist = new Kist(spelbord[x][y]);
			kisten.add(kist);
			spelbord[x][y].setIsKist(true);
		}
		else if (actie == 5) 
		{
			if (maakVeldenVanKistenLijst().contains(spelbord[x][y])) {
				for(int i = 0; i< kisten.size(); i++) {
					if(spelbord[x][y] == kisten.get(i).getVeld()) {
						kisten.remove(kisten.get(i));
					}
				}
			}
			if (man != null && man.getVeld() ==  spelbord[x][y]) {
				man = null;
			}
			spelbord[x][y] = new Veld(x, y, false, false, false, false);
		}
	}
}