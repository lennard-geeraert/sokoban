package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import domein.Veld;

public class Veldmapper
{
	private static final String INSERT_VELDEN = "INSERT INTO ID222177_g39.Veld (naamSpel, volgnummer, x, "
			+ "y, isDoel, isMuur, isMan, isKist) VALUES(?,?,?,?,?,?,?,?)";
	 // Methode om de velden die bij een spelbord horen uit de databank te kunnen halen
     //volgnummer unieke identiteit van het spelbord waartoe de velden behore
	
	public Veld[][] geefVelden(int volgnummer, String spelnaam) 
	{
        Veld[][] velden = new Veld[10][10];
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
        		PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g39.Veld WHERE (naamSpel = ? AND volgnummer = ?)"))
        {
            query.setString(1, spelnaam);
            query.setInt(2, volgnummer);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) 
                {
            		int x = rs.getInt("x");
                    int y = rs.getInt("y");
                    boolean isDoel = rs.getBoolean("isMuur");
                    boolean isMuur = rs.getBoolean("isDoel");
                    boolean isMan = rs.getBoolean("isMan");
                    boolean isKist = rs.getBoolean("isKist");
                    velden[x][y] = new Veld(x, y, isMuur, isDoel, isMan, isKist);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return velden;
    }
	
    public void updateVelden(char[][] velden, int volgnummer, String spelnaam) 
    {
            try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
            		PreparedStatement query = conn.prepareStatement("UPDATE ID222177_g39.Veld SET isDoel = ?, isMuur = ?, isMan = ?, isKist = ? WHERE (naamSpel = ? AND volgnummer = ? AND x = ? AND y = ?)")) {
            
            for (int i = 0; i < velden.length; i++) {
                for (int j = 0; j < velden[i].length; j++) {
                	boolean isDoel = false;
                	boolean isMuur = false;
                	boolean isMan = false;
                	boolean isKist = false;
                    if (velden[i][j] == 'X') {
                        isMuur = true;
                    } else if (velden[i][j] == '?') {
                        isDoel = true;
                    } else if (velden[i][j] == 'M') {
                        isMan = true;
                    } else if (velden[i][j] == 'K') {
                        isKist = true;
                    }  
                    query.setBoolean(1, isDoel);
                    query.setBoolean(2, isMuur);
                    query.setBoolean(3, isMan);
                    query.setBoolean(4, isKist);
                    query.setString(5, spelnaam);
                    query.setInt(6, volgnummer);
                    query.setInt(7, i);
                    query.setInt(8, j);
                    query.executeUpdate(); 
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    //Methode om velden in de databank toe te voegen die horen bij een spelbord
    //velden object van de klasse Veld
    //spelnaam unieke identiteit van het spel waarbij de velden wordentoegevoegd
    //volgnummer unieke identiteit van het spelbord waarbij de veldenworden toegevoegd
    
    public void voegVeldenToe(char[][] velden, int volgnummer, String spelnaam) 
    {
            try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
            		PreparedStatement query = conn.prepareStatement(INSERT_VELDEN)) {
                   
            for (int i = 0; i < velden.length; i++) {
                for (int j = 0; j < velden[i].length; j++) {
                	boolean isDoel = false;
                	boolean isMuur = false;
                	boolean isMan = false;
                	boolean isKist = false;
                    if (velden[i][j] == 'X') {
                        isMuur = true;
                    } else if (velden[i][j] == '?') {
                        isDoel = true;
                    } else if (velden[i][j] == 'M') {
                        isMan = true;
                    } else if (velden[i][j] == 'K') {
                        isKist = true;
                    }  
                    query.setString(1, spelnaam);
                    query.setInt(2, volgnummer);
                    query.setInt(3, i);
                    query.setInt(4, j);
                    query.setBoolean(5, isDoel);
                    query.setBoolean(6, isMuur);
                    query.setBoolean(7, isMan);
                    query.setBoolean(8, isKist);
                    query.executeUpdate(); 
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}





