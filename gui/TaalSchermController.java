package gui;

import domein.DomeinController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TaalSchermController extends GridPane
{
	@FXML
	private Button btnNederlands;
	@FXML
	private Button btnFrançais;
	@FXML
	private Button btnEnglish;
	@FXML
	private Label lblMessage;
	private DomeinController dc;
	private HoofdSchermController hs;

	// Domeincontroller toevoegen
	
	// Constructor ==> link tussen controller en FXML-file
	public TaalSchermController(DomeinController dc, HoofdSchermController hs) 
	{
		super();
		this.hs = hs;
		this.dc = dc;
		try 
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TaalScherm.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (Exception ex) 
		{
			System.out.println(ex.getMessage());
		}
	}
	
	// Event Listener on Button[#btnNederlands].onAction
	@FXML
	public void btnNederlandsAfhandeling() 
	{
		Taal taal = new Taal("NL");
		hs.update(new AanRegController(dc, hs));
	}
	
	
	// Event Listener on Button[#btnFrançais].onAction
	@FXML
	public void btnFrançaisAfhandeling() 
	{
		Taal taal = new Taal("FR");
		hs.update(new AanRegController(dc, hs));
	}
	
	// Event Listener on Button[#btnEnglish].onAction
	@FXML
	public void btnEnglishAfhandeling() 
	{
		 Taal taal = new Taal("EN");
		 hs.update(new AanRegController(dc, hs));
	}
	
}
