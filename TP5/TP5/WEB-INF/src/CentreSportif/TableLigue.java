package CentreSportif;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableLigue 
{

	private PreparedStatement stmtExiste;
	private PreparedStatement stmtInsert;
	private PreparedStatement stmtDelete;
	private Connexion cx;
	
	/*
	 * Cr�ation d'une instance. 
	 * Des �nonc� SQL pour chaque requete son pr�compiller.
	 */	
	public TableLigue(Connexion cx) throws SQLException
	{
		this.cx = cx;
		stmtExiste = cx.getConnection().prepareStatement(
				"select nomligue, nbjoueursmaxequipe from ligue where nomligue = ?");
		stmtInsert = cx.getConnection().prepareStatement(
				"insert into ligue (nomligue, nbjoueursmaxequipe) values (?, ?)");
		stmtDelete = cx.getConnection().prepareStatement(
				"delete from ligue where nomLigue = ?");
	}
	
	/*
	 * Retourne la connexion associ�
	 */
	public Connexion getConnexion()
	{
		return cx;
	}
	
	/*
	 * Verifie si la ligue existe.
	 */
	public boolean existe(String nomLigue) throws SQLException
	{
		stmtExiste.setString(1,nomLigue);
		ResultSet rset = stmtExiste.executeQuery();
		boolean ligueExiste = rset.next();
		rset.close();
		return ligueExiste;
	}
	
	/*
	 * Lecture d'une ligue
	 */
	public TupleLigue getLigue(String nomLigue) throws SQLException
	{
		stmtExiste.setString(1, nomLigue);
		ResultSet rset = stmtExiste.executeQuery();
		
		if(rset.next())
		{
			TupleLigue tupleLigue = new TupleLigue();
			
			tupleLigue.setNomLigue(nomLigue);
			tupleLigue.setNbJouersMaxEquipe(rset.getInt(2));
			
			rset.close();
			
			return tupleLigue;
		}
		else
			return null;
	}
	
	/*
	 * Ajout d'une nouvelle ligue dans la base de donn�e
	 */
	public void ajouterLigue(String nomLigue, int nbJouersMaxEquipe) throws SQLException
	{
		/*Ajout de la ligue*/
		stmtInsert.setString(1,nomLigue);
		stmtInsert.setInt(2,nbJouersMaxEquipe);
		stmtInsert.executeUpdate();
	}
	
	/*
	 * Suppression d'une ligue
	 */
	public int supprimerLigue(String nomLigue) throws SQLException
	{
		stmtDelete.setString(1, nomLigue);
		return stmtDelete.executeUpdate();
	}
	
		
	
}
