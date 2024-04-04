package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domein.Spelbord;
import domein.Veld;

public class SpelbordMapper
{
	private static final String INSERT_SPELBORD = "INSERT INTO ID222177_g39.Spelbord (volgnummer, naamSpel) VALUES(?,?)";
	private Veldmapper vm = new Veldmapper();	
	
	
	//Methode om een bepaald spelbord uit de databank te halen
	//spelnaam unieke identeit van het spel waartoe het spelbord behoort
	//volgnummer unieke identiteit van het spelbord dat uit de databank
	
    public Spelbord geefSpelbord(String spelnaam)   
    {
        Spelbord spelbord = null;
        Veld[][] velden;
        
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
        		PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g39.Spelbord WHERE naamSpel = ?")) {
            
            query.setString(1, spelnaam);
            try (ResultSet rs = query.executeQuery()) 
            {
                while (rs.next()) {
                    int volgnummer = rs.getInt("volgnummer");
                    velden = vm.geefVelden(volgnummer, spelnaam);
                    spelbord = new Spelbord(volgnummer, velden);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return spelbord;
    }
    
    
    public Spelbord geefSpelbordMetVolgnummer(int volgnummer, String spelnaam)   
    {
        Spelbord spelbord = null;
        Veld[][] velden;
        
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
        		PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g39.Spelbord WHERE volgnummer = ? AND naamSpel = ?")) {
            
        	query.setInt(1, volgnummer);
            query.setString(2, spelnaam);
            try (ResultSet rs = query.executeQuery()) 
            {
                while (rs.next()) 
                {
                    velden = vm.geefVelden(volgnummer, spelnaam);
                    spelbord = new Spelbord(volgnummer, velden);
                }
            }
        } catch (SQLException ex) 
        {
            throw new RuntimeException(ex);
        }
        return spelbord;
    }

    
    //Methode om een lijst van spelborden uit de databank te halen die horen bij een bepaald spel
    //spelnaam unieke identiteit van het spel waartoe de spelborden behoren
     
    public List<Spelbord> geefSpelborden(String spelnaam) 
    {
        List<Spelbord> spelborden = new ArrayList<>();
        Veld[][] velden;

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
        		PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g39.Spelbord WHERE naamSpel = ?")) {
            query.setString(1, spelnaam);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    int volgnummer = rs.getInt("volgnummer");

                    velden = vm.geefVelden(volgnummer, spelnaam);
                    spelborden.add(new Spelbord(volgnummer, velden));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return spelborden;
    }

    
    //Methode om een spelbord toe te voegen aan een bepaald spel in een databank
    //spenlnaam unieke identiteit van het spelbord dat wordt toegevoegd in de databank
    //spelnaam unieke identiteit van het spel waartoe het spelbord zal behoren
   
    public void voegSpelbordToe(int volgnummer, char[][] velden, String spelnaam) 
    {
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
        		PreparedStatement query = conn.prepareStatement(INSERT_SPELBORD)) 
        {      
            query.setInt(1, volgnummer);
            query.setString(2, spelnaam);
            query.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            throw new RuntimeException(ex);
        }
        vm.voegVeldenToe(velden, volgnummer, spelnaam);

    }
    
    
    public void updateSpelbord(int volgnummer, char[][] velden, String spelnaam)
    {
    	vm.updateVelden(velden, volgnummer, spelnaam);
    }
    
    public void verwijderSpelbord(int volgnummer, String naamSpel)
    {
    	try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
        		PreparedStatement query = conn.prepareStatement("DELETE FROM ID222177_g39.Spelbord WHERE volgnummer = ? AND naamSpel = ?"))
        {      
    		query.setInt(1, volgnummer);
    		query.setString(2, naamSpel);
    		query.executeUpdate();
        } catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }
    }
    
}
