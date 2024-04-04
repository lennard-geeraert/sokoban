package cui;

import java.util.Scanner;
import java.util.InputMismatchException;
import domein.DomeinController;
import gui.Taal;

public class UC7Test 
{
	private DomeinController dc;
	private UC8Test uc8test;
	
	public UC7Test(DomeinController dc) 
	{
		this.dc = dc;
		uc8test = new UC8Test(dc);
	}
	
	public void wijzigSpel() 
	{ 	
		Scanner input = new Scanner(System.in);
		
		String spelletje = Taal.getText("spelletje"),
			   keuzeMaken = Taal.getText("keuze"),
			   spelbord1 = Taal.getText("spelbord1"),
			   hetSpel = Taal.getText("hetSpel"),
			   spelGewijzigdEnBevat = Taal.getText("spelGewijzigdEnBevat"),
			   spelbord = Taal.getText("spelbord"),
			   spelborden = Taal.getText("spelborden"),
			   getalIngeven = Taal.getText("getalIngeven"),
			   keuzeNietBeschikbaar = Taal.getText("keuzeNietBeschikbaar");
		
		int gekozenSpel;
		String spelnaam = null;
		int gekozenVolgnummerSpelbord;
		
		String [] spelletjes = dc.geefLijstSpellenSpeler(dc.geefGebruikersnaam());
		
		boolean flag = true;
		do {
			try {
					for(int i = 0; i < spelletjes.length; i++)
					{
						System.out.printf("%n%s %d: %s%n", spelletje,i+1, spelletjes[i]);      //i+1 want getal ingeven is niet gelijk aan index
					}
				System.out.printf("%n%s",keuzeMaken);
				gekozenSpel = input.nextInt();    //gekozen spel wordt ingegeven aan de hand van een getal
				if(gekozenSpel <= 0 && gekozenSpel > spelletjes.length) {
					throw new IllegalArgumentException(keuzeNietBeschikbaar);
				}
				spelnaam = spelletjes[gekozenSpel - 1];
				flag = false;
			}catch(InputMismatchException e) {
				System.out.printf("%n%s%n", getalIngeven);
				input.next();
			}catch(IllegalArgumentException e) {
				System.out.printf("%n%s%n", e.getMessage());
			}catch(ArrayIndexOutOfBoundsException e) {
				System.out.printf("%n%s%n", keuzeNietBeschikbaar);
			}
		}
		while(flag);   //indien nummer ingegeven niet overeenkomend met een nummer uit de lijst => do opnieuw
		
		dc.selecteerSpel(spelnaam);
		
		int[] volgnummersSpelborden = dc.geefVolgnummerSpelborden(spelnaam);
		
		int keuze = 0;
		boolean blijvenHerhalenFlag = true;
		do {
			do {
				try {

					for(int i = 0; i < volgnummersSpelborden.length; i++)
					{
						System.out.printf("%n%s %d%n", spelbord1, i + 1);      //i+1 want getal ingeven is niet gelijk aan index
					}
					System.out.printf("%n%s",keuzeMaken);
					
					gekozenVolgnummerSpelbord = input.nextInt();    //gekozen spelbord wordt ingegeven aan de hand van een getal
					int volgnummer = volgnummersSpelborden[gekozenVolgnummerSpelbord - 1];
					
					if(gekozenVolgnummerSpelbord < 1 || gekozenVolgnummerSpelbord > volgnummersSpelborden.length)
						throw new IllegalArgumentException(keuzeNietBeschikbaar);
					
					
					int keuzeVerwijderen = verwijderenOfWijzigen();
					if (keuzeVerwijderen == 2)
						uc8test.wijzigSpelbord(spelnaam, volgnummer);
					else {
						if (dc.geefAantalSpelborden() == 1){
							System.out.printf("%n%s%n", Taal.getText("minstens2Spelborden"));	
						}
						else{
							dc.verwijderSpelbord(volgnummer, spelnaam);
							System.out.println(Taal.getText("spelbordVerwijderd"));
						}
					}
					blijvenHerhalenFlag = false;
				}catch(IllegalArgumentException e) {
					System.out.printf("%n%s%n", e.getMessage());
				}catch(InputMismatchException e) { 
					System.out.printf("%n%s%n", getalIngeven);
					input.next();
				}catch(ArrayIndexOutOfBoundsException e) {
					System.out.printf("%n%s%n", keuzeNietBeschikbaar);
				}
			}while(blijvenHerhalenFlag);
			
			keuze = toonActies();
		}while(keuze != 2);
		
		System.out.printf("%n%s '%s' %s %d %s%n",hetSpel,spelnaam, spelGewijzigdEnBevat,
				dc.geefAantalSpelborden(), dc.geefAantalSpelborden() ==1 ? spelbord : spelborden);
	}
	
	private int verwijderenOfWijzigen()
	{
		Scanner sc = new Scanner(System.in);
		int keuze = 0;
		boolean blijvenHerhalenFlag = true;
		do {
			try {
				System.out.printf("%n%s",Taal.getText("verwijderenOfWijzigenSpelbord"));
				keuze = sc.nextInt();
				if (keuze < 1 || keuze > 2)
					throw new IllegalArgumentException(Taal.getText("keuzeNietBeschikbaar"));
				blijvenHerhalenFlag = false;
			}catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			catch(InputMismatchException e) {
				System.out.printf("%n%s%n", Taal.getText("getalIngeven"));
				sc.next();
			}
		}while(blijvenHerhalenFlag);
		return keuze;
	}
	
	private int toonActies() 
	{
		Scanner input = new Scanner(System.in);
		
		String wijzigSpelbord = Taal.getText("wijzigSpelbord"),
		       keuzeMaken = Taal.getText("keuze"),
		       ongeldigeActie = Taal.getText("ongeldigActie"),
		       getalIngeven = Taal.getText("getalIngeven"),
		       spelVerlatenNr = Taal.getText("spelVerlatenO");
		
		boolean flag2 = true;
		int keuze = 0;
		do {
			try {
				System.out.printf("%n-----------------------------%n %s%n %s%n-----------------------------%n%s ",wijzigSpelbord,spelVerlatenNr,keuzeMaken);
				keuze = input.nextInt();
				
				if(keuze < 1 || keuze > 2){
					throw new IllegalArgumentException(ongeldigeActie);
				}	
				flag2 = false;
			}
			catch(InputMismatchException e){
				System.out.printf("%n%s%n", getalIngeven);
				input.next();
			}catch(IllegalArgumentException e) {
				System.out.printf("%n%s%n", e.getMessage());
			}
		}while(flag2);
		return keuze;
	}
	
}
