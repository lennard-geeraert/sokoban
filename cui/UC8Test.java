package cui;

import domein.DomeinController;
import gui.Taal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UC8Test
{
	private DomeinController dc;
	
	public UC8Test(DomeinController dc)
	{
		this.dc = dc;
	}
	
	public void wijzigSpelbord(String spelnaam, int volgnummer)
	{
		Scanner input = new Scanner(System.in);
		
		String geefRij = Taal.getText("geefRij"),
			   geefKolom = Taal.getText("geefKolom"),
			   getalIngeven = Taal.getText("getalIngeven"),
			   spelbordBijgewerkt = Taal.getText("spelbordBijgewerkt");
		
		boolean blijvenHerhalenFlag = true;
		int keuze = 0;
		
		dc.selecteerSpel(spelnaam);
		
		dc.selecteerSpelbordMetVolgnummer(volgnummer, spelnaam);
		
		toonSpelbord(dc.geefVelden());
		
		do {
			try {
				keuze = toonMogelijkeActies();
				
				int x = 1;
				int y = 1;
				
				if (keuze != 6) {
					System.out.printf("%n%s ",geefRij);
					x = input.nextInt();
					
					System.out.printf("%n%s ",geefKolom);
					y = input.nextInt();
				}
				dc.wijzigSpelbord(x-1, y-1, keuze);
				
				if (keuze == 6) {
					try {
						dc.updateSpelbord(dc.geefVolgnummer(),dc.geefVelden(), dc.geefNaamSpel());
						blijvenHerhalenFlag = false;
					}catch(IllegalArgumentException e) {
						System.out.printf("%n%s%n", e.getMessage());
						int keuze2 = keuzeWijzigenOfVerwijderen();
						if(keuze2 == 1)
							blijvenHerhalenFlag = false;
					}
				}else {
					toonSpelbord(dc.geefVelden());
				}	
			}
			catch(IllegalArgumentException e){
				System.out.println("ik ben hier");
				System.out.printf("%n%s%n", e.getMessage());
			}
			catch(InputMismatchException e) {
				System.out.printf("%n%s%n",getalIngeven);
				input.next();
			}
		}while (blijvenHerhalenFlag);
		
		
		System.out.printf("%n%s%n",spelbordBijgewerkt);
	}
	
	private int toonMogelijkeActies() 
	{
		Scanner input = new Scanner(System.in);
		
		String acties = Taal.getText("mogelijkeActie"),
			   maakDoel = Taal.getText("maakDoel"),
			   maakMuur = Taal.getText("maakMuur"),
			   zetMan = Taal.getText("zetMan"),
			   zetKist = Taal.getText("zetKist"),
			   maakLeeg = Taal.getText("maakVeldLeeg"),
			   stop = Taal.getText("stopWijziging"),
			   maakKeuze = Taal.getText("keuze"),
			   keuzeOutOfBounds = Taal.getText("keuzeNietBeschikbaar"),
			   getalIngeven = Taal.getText("getalIngeven");
			   
		do {
			try {		        
				System.out.printf("%n%s%n"
						+ "%s%n"
						+ "%s%n"
						+ "%s%n"
						+ "%s%n"
						+ "%s%n"
						+ "%s%n",acties,maakDoel,maakMuur,zetMan,zetKist,maakLeeg,stop);
		        
				System.out.printf("%s",maakKeuze);
				int keuze = input.nextInt();
				
				if(keuze < 1 || keuze > 6){
					throw new IllegalArgumentException(keuzeOutOfBounds);
				}	
				return keuze;
			}catch(IllegalArgumentException e) {
				System.out.printf("%n%s%n", e.getMessage());
			}
			catch(InputMismatchException e) {
				System.out.printf("%n%s%n", getalIngeven);
				input.next();
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
				System.out.printf("%n%s", Taal.getText("wijzigenOfOngedaanMaken"));
				keuze = input.nextInt();
				System.out.println();
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
