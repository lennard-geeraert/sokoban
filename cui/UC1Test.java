package cui;

import java.util.Scanner;

import domein.DomeinController;
import gui.Taal;

public class UC1Test
{
	private final  DomeinController dc;
	
	public UC1Test (DomeinController dc) 
	{
		this.dc =dc;
	}
	
	public void meldAan()
	{
		Scanner input = new Scanner(System.in);
		
		String geefGebruikersnaam = Taal.getText("geefGebruikersnaam"),
			   geefWachtwoord = Taal.getText("geefWachtwoord"),
			   isAangemeld = Taal.getText("aangemeld");
		
		String gebruikersnaam = "";
		String wachtwoord = "";
		
		boolean blijvenHerhalenFlag = true;
//		blijft herhalen zolang flag = true maar indien wachtwoord en gebruikersnaam juist zijn => flag wordt false,
//		anders skipt het die stap en gaat direct naar exceptions.
        do
        {
            try
            {
                System.out.printf("\n%s ",geefGebruikersnaam);
                gebruikersnaam = input.next();

                System.out.printf("\n%s ",geefWachtwoord);
                wachtwoord = input.next();
                
               dc.meldAan(gebruikersnaam, wachtwoord);
               blijvenHerhalenFlag = false;
               
               System.out.printf("%n%s %s%n", dc.geefGebruikersnaam(),isAangemeld);
            } 
            catch (IllegalArgumentException e){
                System.out.printf("%n%s%n", e.getMessage()); 
            } 
        } while (blijvenHerhalenFlag);	
	}
	 
}