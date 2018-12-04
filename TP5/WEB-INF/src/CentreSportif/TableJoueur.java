package CentreSportif;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableJoueur 
{

	private PreparedStatement stmtExiste;
	private PreparedStatement stmtInsert;
	private PreparedStatement stmtDelete;
	private PreparedStatement stmtUpdate;
	private Connexion cx;
	
	/*
	 * Cr�ation d'une instance. 
	 * Des �nonc� SQL pour chaque requete son pr�compiller.
	 */	
	public TableJoueur(Connexion cx) throws SQLException
	{
		this.cx = cx;
		stmtExiste = cx.getConnection().prepareStatement(
				"select nomEquipe, matricule, enattente from joueur where matricule = ?");
		stmtInsert = cx.getConnection().prepareStatement(
				"insert into joueur (nomequipe, matricule, enattente) values (?, ?, ?)");
		stmtDelete = cx.getConnection().prepareStatement(
				"delete from joueur where matricule = ?");
		stmtUpdate = cx.getConnection().prepareStatement( 
				"update joueur set enattente = ? where matricule = ?");
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
	public boolean existe(String matricule) throws SQLException
	{
		stmtExiste.setString(1,matricule);
		ResultSet rset = stmtExiste.executeQuery();
		boolean ligueExiste = rset.next();
		rset.close();
		return ligueExiste;
	}
	
	/*
	 * Lecture d'un joueur
	 */
	public TupleJoueur getJoueur(String matricule) throws SQLException
	{
		stmtExiste.setString(1, matricule);
		ResultSet rset = stmtExiste.executeQuery();
		
		if(rset.next())
		{
			TupleJoueur tupleJoueur = new TupleJoueur();
			
			tupleJoueur.setMatricule(matricule);
			tupleJoueur.setNomEquipe(rset.getString(1));
			tupleJoueur.setEnAttente(rset.getBoolean(3));
			
			rset.close();
			
			return tupleJoueur;
		}
		else
			return null;
	}
	
	/*
	 * Ajout d'un nouveau joueur dans la base de donn�e
	 */
	public void ajouterJoueur(String nomEquipe, String matricule, boolean enAttente) throws SQLException
	{
		/*Ajout de la ligue*/
		stmtInsert.setString(1,nomEquipe);
		stmtInsert.setString(2,matricule);
		stmtInsert.setBoolean(3,enAttente);
		stmtInsert.executeUpdate();
	}
	
	/*
	 * Suppression d'une joueur
	 */
	public int supprimerJoueur(String matricule) throws SQLException
	{
		stmtDelete.setString(1, matricule);
		return stmtDelete.executeUpdate();
	}
	
	/*
	 * Modifie un joueur
	 */
	
	public int modifierJoueur(String matricule, boolean enAttente) throws SQLException
	{
		stmtUpdate.setBoolean(1, enAttente);
		stmtUpdate.setString(2, matricule);
		return stmtUpdate.executeUpdate();
	}
	
	
		

}