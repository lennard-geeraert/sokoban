package cui;

import domein.DomeinController;
import gui.Taal;

import java.util.Scanner;
import java.util.InputMismatchException;

public class UC5Test 
{
	private DomeinController dc;
	private UC6Test uc6test;
	
	public UC5Test(DomeinController dc) 
	{
		this.dc = dc;
		this.uc6test = new UC6Test(dc);
	}
	
	public void maakNieuwSpel() 
	{
		Scanner input = new Scanner(System.in);
		
        String spelnaam = "";
        
        String geefSpelnaam = Taal.getText("geefSpelnaamNieuweSpel"),
        	   stopAanmaken = Taal.getText("stopMetSpelbordenAanmaken"),
        	   aangemaakt = Taal.getText("isAangemaakt"),
        	   spelbord = Taal.getText("spelbord"),
        	   spelborden = Taal.getText("spelborden");
        
        boolean flag = true;
        do {
        	try {
            	System.out.printf("%n%s",geefSpelnaam);
    			spelnaam = input.nextLine();
    			
    			dc.maakNieuwSpel(spelnaam, dc.geefGebruikersnaam());				
    			
    			flag = false;
            }catch (IllegalArgumentException e) {
    			System.out.printf("%n%s%n", e.getMessage());
    		}
        }while(flag);
        
        
		boolean blijvenHerhalenFlag = true;
		do {
			uc6test.maakNieuwSpelbord(dc.geefHuidigSpel().geefAantalSpelborden()+1);
			
			int actie = toonActies(true);
			
			if (actie == 2) 
			{
				if (dc.geefHuidigSpel().geefAantalSpelborden() == 0)
			    {
					System.out.printf("%n%s%n", Taal.getText("minstens1Spelbord"));
					actie = toonActies(false);
					if(actie == 2) {
						dc.verwijderSpel(spelnaam);
						System.out.printf("%n%s%n", Taal.getText("spelVerwijderd"));
						blijvenHerhalenFlag = false;
					}
			    }
				else
				{
					System.out.printf("%n%s %s%n", dc.geefGebruikersnaam(),stopAanmaken);
					System.out.printf("%n%s %s %d %s%n", dc.geefNaamSpel(),aangemaakt,dc.geefAantalSpelborden(), dc.geefAantalSpelborden() == 1 ? spelbord: spelborden);
					blijvenHerhalenFlag = false;
				}
			}
				
		}while (blijvenHerhalenFlag);		
	}
	
	
	private int toonActies(boolean stoppenBool) 
	{	
		Scanner input = new Scanner(System.in);
		
		String nieuwSpelbord = Taal.getText("nieuwSpelbord"),
			   stoppen = Taal.getText("stoppen"),
			   stoppen2 = Taal.getText("stoppenEnVerwijderen"),
			   keuze = Taal.getText("keuze"),
			   keuzeOutOfBounds = Taal.getText("keuzeNietBeschikbaar"),
			   getalIngeven = Taal.getText("getalIngeven");
		do
		{
			try {
				System.out.printf("%n-----------------------------%n %s%n %s%n-----------------------------%n%s ",
						nieuwSpelbord, stoppenBool ? stoppen : stoppen2 ,keuze);
				int actie = input.nextInt();
				
				if(actie < 1 || actie > 2){
					throw new IllegalArgumentException(keuzeOutOfBounds);
				}	
				return actie;
			}catch (IllegalArgumentException e) {
    			System.out.printf("%n%s%n", e.getMessage());
    		}
			catch(InputMismatchException e) {
				System.out.printf("%n%s%n",getalIngeven);
				input.next();
			}
		}while(true);
	}
	
}