package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import domein.DomeinController;
import javafx.event.ActionEvent;

public class LoginRegistreerSchermController  extends GridPane
{
	@FXML
	private Button btnLoginRegistreer;
	private DomeinController dc;
	private HoofdSchermController hs;
	private int aantalRijen;
	private Label lblGebruikersnaam, lblWachtwoord, lblNaam,lblVoornaam;
	private TextField[] arrayTextField;
	
	public LoginRegistreerSchermController(DomeinController dc, HoofdSchermController hs, int aantalRijen)
	{	
		super();
		this.dc = dc;
		this.hs = hs;
		this.aantalRijen = aantalRijen;
		try 
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginRegistreerScherm.fxml"));
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
		Label[] arrayLabel = {lblGebruikersnaam,lblWachtwoord,lblNaam,lblVoornaam};
		String[] waardeLabel = {Taal.getText("geefGebruikersnaam"), Taal.getText("geefWachtwoord"), Taal.getText("naam"), Taal.getText("voornaam")};
		arrayTextField = new TextField[aantalRijen];
		
		for(int i = 0; i < aantalRijen; i++)
		{
			arrayLabel[i] = new Label(waardeLabel[i]);
			this.add(arrayLabel[i], 0, i);
			
			if (i != 1)
			{
				arrayTextField[i] = new TextField();
				this.add(arrayTextField[i], 1, i);
			}
			else 
			{
				arrayTextField[i] = new PasswordField();
				this.add(arrayTextField[i], 1, i);
			}
			
		}
		if(aantalRijen == 2)
			btnLoginRegistreer.setText(Taal.getText("meldAanGui"));
			
		else
			btnLoginRegistreer.setText(Taal.getText("registreerGui"));
	}

	// Event Listener on Button[#btnLoginRegistreer].onAction
	@FXML
	public void btnLoginRegistreerAfhandeling(ActionEvent event) 
	{
		try {
			if (aantalRijen == 2)
			{
				dc.meldAan(arrayTextField[0].getText(), arrayTextField[1].getText());
				hs.update(new Hoofdpaneel1Controller(dc, hs, true));
			}

			else if (aantalRijen == 4)
			{
				dc.registreer(arrayTextField[0].getText(), arrayTextField[1].getText(), false, arrayTextField[2].getText(), arrayTextField[3].getText());
				hs.update(new Hoofdpaneel1Controller(dc, hs, false));
			}
		}
		catch(IllegalArgumentException e)
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle(Taal.getText("fouteLogin"));
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
		
		
	}
}
















