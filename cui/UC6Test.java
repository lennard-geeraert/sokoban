package cui;

import domein.DomeinController;
import gui.Taal;

import java.util.Scanner;
import java.util.InputMismatchException;

public class UC6Test 
{
	private DomeinController dc;

	public UC6Test(DomeinController dc) 
	{	
		this.dc = dc;
	}
	
	public void maakNieuwSpelbord(int volgnummer) 
	{
		Scanner input = new Scanner(System.in);
		
		String geefRij = Taal.getText("geefRij"),
			   geefKolom = Taal.getText("geefKolom");
		boolean blijvenHerhalenFlag = true;
		
		dc.maakNieuwSpelbord(volgnummer); 
		
		toonSpelbord(dc.geefVelden());
		
		int keuze = 0;

		do {
			try{ 
				keuze = toonMogelijkeActies();
				if(keuze != 6) {
					System.out.printf("%n%s ",geefRij);
					int x = input.nextInt();
					
					System.out.printf("%n%s ",geefKolom);
					int y = input.nextInt();
					System.out.println();
					
					dc.wijzigSpelbord(x-1, y-1, keuze);	
					
					toonSpelbord(dc.geefVelden());
				}else{
					try {
						dc.voegSpelbordToe(dc.geefVelden(),dc.geefVolgnummer(),dc.geefNaamSpel());
						blijvenHerhalenFlag = false;
					}
					catch(IllegalArgumentException e){
						System.out.printf("%n%s%n", e.getMessage());
						int keuze2 = keuzeWijzigenOfVerwijderen();
						if (keuze2 == 2){
							blijvenHerhalenFlag = false;
						}
					}
				}
			}
			catch(IllegalArgumentException e){
				System.out.printf("%n%s%n", e.getMessage());
			}catch(InputMismatchException e) {
				System.out.printf("%n%s%n", e.getMessage());
				input.next();
			}catch(NullPointerException e){
				System.out.printf("%n%s%n", Taal.getText("manVerplicht"));	
			}
		}while(blijvenHerhalenFlag);
		
	}
	
	private int toonMogelijkeActies() 
	{
		Scanner input = new Scanner(System.in);
		
		String mogelijkeActie = Taal.getText("mogelijkeActie"),
				maakDoel = Taal.getText("maakDoel"),
				maakMuur = Taal.getText("maakMuur"),
				zetMan = Taal.getText("zetMan"),
				zetKist = Taal.getText("zetKist"),
				maakVeldLeeg = Taal.getText("maakVeldLeeg"),
				stopWijzigen = Taal.getText("stopWijziging"),
				keuzeMaken = Taal.getText("keuze"),
						keuzeOutOfBounds = Taal.getText("keuzeNietBeschikbaar"),
				getalINgeven = Taal.getText("getalIngeven");
		do {
			try {
				System.out.printf("%n%s%n"
						+ "%s%n"
						+ "%s%n"
						+ "%s%n"
						+ "%s%n"
						+ "%s%n"
						+ "%s%n",mogelijkeActie,maakDoel,maakMuur,zetMan,zetKist,maakVeldLeeg,stopWijzigen);

				System.out.printf("%s",keuzeMaken);
				int keuze = input.nextInt();
				
				if(keuze < 1 || keuze > 6)
				{
					throw new IllegalArgumentException(keuzeOutOfBounds);
				}	
				return keuze;
			}
			catch(InputMismatchException e){
				System.out.printf("%n%s%n",getalINgeven);
				input.next();
			}catch(IllegalArgumentException e) {
				System.out.printf("%n%s%n", e.getMessage());
			}
		}while(true);
		
	}
	
	private void toonSpelbord(char[][] spelbord)
	{
		for(int i = 0; i < spelbord.length; i++) {
			for(int j = 0; j < spelbord[i].length; j++) {
				System.out.printf(" %s ",spelbord[i][j]);
			}
			System.out.printf("%n");
		}
	}
	
	private int keuzeWijzigenOfVerwijderen()
	{
		Scanner input = new Scanner(System.in);
		boolean flag = true;
		int keuze = 0;
		do {
			try {
				System.out.printf("%n%s", Taal.getText("wijzigenOfVerwijderen"));
				keuze = input.nextInt();
				if(keuze > 2 || keuze < 0) {
					throw new IllegalArgumentException(Taal.getText("keuzeNietBeschikbaar"));
				}
				flag = false;
			}catch(InputMismatchException e){
				System.out.printf("%n%s%n",Taal.getText("getalIngeven"));
				input.next();
			}catch(IllegalArgumentException e) {
				System.out.printf("%n%s%n", e.getMessage());
			}
			
		}while(flag);
		return keuze;
	}

}
