package cui;

import java.util.Scanner;

import domein.DomeinController;
import gui.Taal;

public class UC2Test 
{
	private DomeinController dc;
	
	public UC2Test (DomeinController dc) 
	{
		this.dc =dc;
	}
	
	public  void registreer()
	{
		Scanner input = new Scanner(System.in);
		
		String geefGebruikersnaam = Taal.getText("geefGebruikersnaam"),
			   geefWw = Taal.getText("geefWachtwoord"),
			   geefNaam = Taal.getText("naam"),
			   geefVoornaam = Taal.getText("voornaam"),
			   inDatabank = Taal.getText("geregistreerdEnAangemeld");
		
		String gebruikersnaam = "";
		String wachtwoord = "";
		String naam = "";
		String voornaam = "";
		boolean blijvenHerhalenFlag = true;
//		blijft herhalen zolang flag = true maar indien wachtwoord en gebruikersnaam juist zijn => flag wordt false,
//		anders skipt het die stap en gaat direct naar exceptions.
            do
            {
                try
                {
                    System.out.printf("\n%s",geefGebruikersnaam);
                    gebruikersnaam = input.nextLine();

                    System.out.printf("\n%s",geefWw);
                    wachtwoord = input.nextLine();
                    
                    System.out.printf("\n%s",geefNaam);
                    naam = input.nextLine();
                    
                    System.out.printf("\n%s",geefVoornaam);
                    voornaam = input.nextLine();
                    
                    dc.registreer(gebruikersnaam, wachtwoord, false, naam, voornaam);
                    blijvenHerhalenFlag = false;
                    
                    System.out.printf("%n%s %s%n", dc.geefGebruikersnaam(),inDatabank);
                } 
                catch (IllegalArgumentException e)
                {
                    System.out.printf("%n%s%n", e.getMessage());
                } 
                
            } while (blijvenHerhalenFlag);
	}
	
}