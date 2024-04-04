package gui;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Hoofdpaneel1Controller  extends GridPane
{
	@FXML
	private Button btnSpeelSpel;
	@FXML
	private Button btnAfmelden;
	private DomeinController dc;
	private HoofdSchermController hs;
	private Button btnWijzigSpel;
	private Button btnMaakNieuwSpel;
	@FXML
	private Label lblAangemeld;
	private Boolean isAangemeld = null;

	public Hoofdpaneel1Controller(DomeinController dc, HoofdSchermController hs, Boolean aangemeld) {
		this(dc, hs);
		this.isAangemeld = aangemeld;
		buildGui();
	}
	

	public Hoofdpaneel1Controller(DomeinController dc, HoofdSchermController hs)
	{
		super();
		this.dc = dc;
		this.hs = hs;
		
		try 
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Hoofdpaneel1.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (Exception ex) 
		{
			System.out.println(ex.getMessage());
		}
		buildGui();
	}
	
	private void buildGui()
	{
		if(dc.isAdmin())
		{
			
			
			btnWijzigSpel = new Button(Taal.getText("wijzigSpelGui"));
			btnWijzigSpel.setPrefHeight(25);
			btnWijzigSpel.setPrefWidth(180);
			btnWijzigSpel.setAlignment(Pos.CENTER);
			this.add(btnWijzigSpel, 0, 1);
			
			btnMaakNieuwSpel = new Button(Taal.getText("maakNieuwSpelGui"));
			btnMaakNieuwSpel.setPrefHeight(25);
			btnMaakNieuwSpel.setPrefWidth(180);
			btnMaakNieuwSpel.setAlignment(Pos.CENTER);
			this.add(btnMaakNieuwSpel, 0, 2);
			GridPane.setHalignment(btnMaakNieuwSpel, HPos.CENTER);
			GridPane.setHalignment(btnWijzigSpel, HPos.CENTER);
			btnWijzigSpel.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) 
				{
					if(dc.geefLijstSpellenSpeler(dc.geefGebruikersnaam()).length > 0)
						hs.update(new SpelwijzigenController(dc, hs));
					else {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setContentText(Taal.getText("geenSpelborden"));
						alert.showAndWait();
					}
				}
			});
			
			btnMaakNieuwSpel.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) 
				{
					hs.update(new SpelMakenSchermController(dc, hs));
				}
			});
		}
		
		btnSpeelSpel.setText(Taal.getText("speelSpelGui"));
		btnAfmelden.setText(Taal.getText("afmeldenGui"));
		if (this.isAangemeld == null)
			lblAangemeld.setVisible(false);
		else if(this.isAangemeld == true)
		{
			lblAangemeld.setVisible(true);
			lblAangemeld.setText(String.format("%s %s", dc.geefGebruikersnaam(), Taal.getText("aangemeld")));
		}
		else if(this.isAangemeld == false)
		{
			lblAangemeld.setVisible(true);
			lblAangemeld.setText(String.format("%s %s", dc.geefGebruikersnaam(), Taal.getText("geregistreerdEnAangemeld")));
		}
	}
	
	// Event Listener on Button[#btnSpeelSpel].onAction
	@FXML
	public void btnSpeelSpelAfhandeling(ActionEvent event) 
	{
		hs.update(new Hoofdpaneel2Controller(dc, hs));
	}
	// Event Listener on Button[#btnAfmelden].onAction
	@FXML
	public void btnAfmeldenAfhandeling(ActionEvent event) 
	{
		
		hs.update(new AanRegController(dc, hs));
	}
}
