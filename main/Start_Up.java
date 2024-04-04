package main;

import java.util.InputMismatchException;
import java.util.Scanner;

import cui.SokobanApplicatie;

import domein.DomeinController;
import gui.HoofdSchermController;
import gui.Taal;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Start_Up extends Application
{
	@Override
	public void start(Stage primaryStage) 
	{
		try {
			DomeinController dc = new DomeinController();
			HoofdSchermController hs = new HoofdSchermController(dc);
			Scene scene = new Scene(hs, 400, 300);
			Image icon = new Image(getClass().getResourceAsStream("/images/icon.png"));
			primaryStage.getIcons().add(icon);
			primaryStage.setScene(scene);
			primaryStage.setTitle("SOKOBAN");
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		int keuze = maakKeuze();
		if (keuze == 1) {
			Taal taal = new Taal();
			DomeinController dc = new DomeinController(); 
			SokobanApplicatie sa = new SokobanApplicatie(dc);
			
			sa.run();    //run methode van sokobanapplicatie wordt uitgevoerd
		}
		else
			launch(args);
	}
	
	private static int maakKeuze() {
		int keuze = 0;
		boolean blijvenHerhalenFlag = true;
		Scanner sc = new Scanner(System.in);
		do {
			try {
				System.out.printf("1. CUI%n"
						+ "2. GUI%n"
						+ "Keuze/Choix/Choice: ");
				keuze = sc.nextInt();
				if (keuze < 1 || keuze > 2)
					throw new IllegalArgumentException("\nGeen geldige keuze/No valid choice/Choix incorrecte\n");
				blijvenHerhalenFlag = false;
			}
			catch(InputMismatchException e) {
				System.out.println("\nGeef een getal in/Give a number/Donnez un chiffre!\n");
				sc.next();
			}
			catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}while(blijvenHerhalenFlag);
		return keuze;
	}
}