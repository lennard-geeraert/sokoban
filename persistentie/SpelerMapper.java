package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import domein.Speler;

public class SpelerMapper
{
	private static final String INSERT_SPELER = "INSERT INTO ID222177_g39.Speler (naam, voornaam, wachtwoord, beheerder, gebruikersnaam)"
            + "VALUES (?, ?, ?, ?, ?)";
	

	public Speler geefSpeler(String gebruikersnaam)    //methode om een speler op te halen uit de databank
	{
		Speler speler = null;
	
	    try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
	            PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g39.Speler WHERE gebruikersnaam = ?")) {
	        query.setString(1, gebruikersnaam);
	        try (ResultSet rs = query.executeQuery()) {
	            if (rs.next()) 
	            {
	                String naam = rs.getString("naam");
	                String voornaam = rs.getString("voornaam");
	                String wachtwoord = rs.getString("wachtwoord");
	                boolean beheerder = rs.getBoolean("beheerder");
	                
	
	                speler = new Speler(gebruikersnaam, wachtwoord, beheerder, naam, voornaam);
	            }
	        }
	    } catch (SQLException ex) 
	    {
	        throw new RuntimeException(ex);
	    }
	    return speler;
	}

	
	public void voegToe(Speler speler)     //methode om een speler toe te voegen in de databank
	{
		 try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
	                PreparedStatement query = conn.prepareStatement(INSERT_SPELER)) 
		 {
	            query.setString(1, speler.getNaam());
	            query.setString(2, speler.getVoornaam());
	            query.setString(3, speler.getWachtwoord());
	            query.setBoolean(4, speler.isAdminrechten());
	            query.setString(5, speler.getGebruikersnaam());
	            query.executeUpdate();
	     } 
		 catch (SQLException ex) 
		 {
	            throw new RuntimeException(ex);
	     }
	}
		

}
