package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.util.Arrays;
import java.util.List;

import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Hoofdpaneel2Controller extends GridPane
{
	@FXML
	private ComboBox<String> cmbSpelltjes;
	@FXML
	private Button btnVerlaten;
	@FXML
	private Button btnSpeelSpel;
	private DomeinController dc;
	private HoofdSchermController hs;
	//fazkhvfa"
	public Hoofdpaneel2Controller(DomeinController dc, HoofdSchermController hs)
	{
		super();
		this.dc = dc;
		this.hs = hs;
		try 
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Hoofdpaneel2.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (Exception ex) 
		{
			System.out.println(ex.getMessage());
		}
		btnVerlaten.setText(Taal.getText("terugGui"));
		cmbSpelltjes.setPromptText(Taal.getText("promptCboSpelletjes"));
		btnSpeelSpel.setText(Taal.getText("speelSpelGui"));
		
		String[] namen = dc.geefLijstSpellen();
		List<String> namenList = Arrays.asList(namen);
		cmbSpelltjes.setItems(FXCollections.observableList(namenList));
	}

	// Event Listener on Button[#btnVerlaten].onAction
	@FXML
	public void btnVerlatenAfhandeling(ActionEvent event) 
	{
		hs.update(new Hoofdpaneel1Controller(dc, hs));
	}
	@FXML
	public void btnSpeelSpelAfhandeling(ActionEvent event) {
		try {
			String gekozenSpel = cmbSpelltjes.getSelectionModel().getSelectedItem();
			dc.selecteerSpel(gekozenSpel);
			dc.selecteerSpelbord(gekozenSpel);
			VoltooiSpelbordSchermController vssc = new VoltooiSpelbordSchermController(dc, hs);
			Scene scene = new Scene(vssc, 1000, 700);
			Stage secondaryStage = (Stage) this.getScene().getWindow();
			secondaryStage.setScene(scene);
			secondaryStage.show();
		}
		catch(NullPointerException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(Taal.getText("spelSelecterenGui"));
			alert.showAndWait();
		}
		
	}
}
