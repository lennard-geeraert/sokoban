package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import domein.DomeinController;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class SpelMakenSchermController extends GridPane {
	@FXML
	private Button btnMaakSpel;
	@FXML
	private TextField txfSpelMaken;
	@FXML
	private Label lblSpelMaken;
	@FXML
	private Button btnTerug;

	private DomeinController dc;
	private HoofdSchermController hs;

	
	public SpelMakenSchermController(DomeinController dc, HoofdSchermController hs)
	{
		this.dc = dc;
		this.hs = hs;
		try 
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("SpelMakenScherm.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (Exception ex) 
		{
			//System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		btnMaakSpel.setText(Taal.getText("maakSpelGui"));
		lblSpelMaken.setText(Taal.getText("spelnaamGui"));
		btnTerug.setText(Taal.getText("terugGui"));
		
	}
	
	// Event Listener on Button[#btnMaakSpel].onAction
	@FXML
	public void btnSpelMakenAfhandeling(ActionEvent event) 
	{
		try {
			dc.maakNieuwSpel(txfSpelMaken.getText(), dc.geefGebruikersnaam());
			dc.selecteerSpel(txfSpelMaken.getText());
			Scene scene = new Scene(new maakNieuwSpelbordSchermController(dc, hs, 1, 1, 2), 1200, 700);
			Stage stage = (Stage) this.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		}
		catch(IllegalArgumentException e){
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle(Taal.getText("fouteSpelnaamGui"));
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}
	@FXML
	public void btnTerugAfhandeling(ActionEvent event) 
	{
		hs.update(new Hoofdpaneel1Controller(dc, hs));
	}
}
