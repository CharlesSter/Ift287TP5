package CentreSportif;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableResultat {
	
	private PreparedStatement stmtExiste;
    private PreparedStatement stmtInsert;
    private PreparedStatement stmtDelete;
    private PreparedStatement stmtMaxId;
    
    private Connexion cx;
    
    public TableResultat(Connexion cx)throws SQLException
    {
        this.cx = cx;
        stmtExiste = cx.getConnection().prepareStatement(
                "select id, equipea, resultata, equipeb, resultatb from Resultat where equipeA = ? or equipeB = ?");
        stmtInsert = cx.getConnection().prepareStatement(
                "insert into Resultat (id, equipea, resultata, equipeb, resultatb) values(?, ?, ?, ?, ?)");
        stmtDelete = cx.getConnection().prepareStatement(
                "delete from Resultat where equipea = ? or equipeb = ?");
        stmtMaxId = cx.getConnection().prepareStatement( 
        		"select max(id) from Resultat");
    }
    
    /*
     * Retourne la connexion associer.
     */
    public Connexion getConnexion()
    {
        return cx;
    }
    
    /*
     * Verifie si le Resultat existe
     */
    
    public boolean existe (String nomEquipe) throws SQLException
    {
    	stmtExiste.setString(1, nomEquipe);
    	stmtExiste.setString(2, nomEquipe);
    	ResultSet rset = stmtExiste.executeQuery();
    	boolean ResultatExiste = rset.next();
    	rset.close();
    	return ResultatExiste;
    }
    
    /*
     * Lecture d'une equipe
     */
    public ArrayList<TupleResultat> getResultat(String nomEquipe) throws SQLException
    {
    	stmtExiste.setString(1, nomEquipe);
    	stmtExiste.setString(2, nomEquipe);
    	ResultSet rset = stmtExiste.executeQuery();
    	
    	if(rset.next())
    	{    		
    		ArrayList<TupleResultat> tupleResultat = new ArrayList<TupleResultat>();
    		TupleResultat tuple = new TupleResultat();
    		
    		while(rset.next())
    		{
    			tuple.setId(rset.getInt(1));
        		tuple.setEquipeA(rset.getString(2));
        		tuple.setResultatA(rset.getInt(3));
        		tuple.setEquipeB(rset.getString(4));
        		tuple.setResultatB(rset.getInt(5));
        		
        		tupleResultat.add(tuple);
    		}   		
    		
    		rset.close();
    		
    		return tupleResultat;
    	}
    	else
    		return null;
    }
    
    /*
     * Ajout d'un nouveau Resultat dans la base de donner
     */
    public void ajouterResultat(int id, String equipeA, int resultatA,String equipeB, int resultatB) throws SQLException
    {
    	/*Ajout du Resultat*/
    	stmtInsert.setInt(1, id+1);
    	stmtInsert.setString(2, equipeA);
    	stmtInsert.setInt(3, resultatA);
    	stmtInsert.setString(4, equipeB);
    	stmtInsert.setInt(5, resultatB);
  
    	stmtInsert.executeUpdate();
    }
    
    /*
     * Suppression d'un Resultat
     */
    public int supprimerResultat(String nomEquipe) throws SQLException
    {
    	stmtDelete.setString(1, nomEquipe);
    	stmtDelete.setString(2, nomEquipe);
    	
    	return stmtDelete.executeUpdate();
    }
    
    public int getMaxId() throws SQLException
    {
    	
    	ResultSet rset = stmtMaxId.executeQuery();
    	if(!rset.next())
    		return 0;
    	
    	int maxID = rset.getInt(1);
    	
    	return maxID;
    	
    }
}
