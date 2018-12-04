package CentreSportif;

import java.sql.SQLException;



public class GestionLigue 
{

	private Connexion cx;
	private TableLigue ligue;
	private TableEquipe equipe;
	private TableResultat resultat;
	
	
	
	/*
	 * Créaation d'une instance
	 */
	
	public GestionLigue(TableLigue ligue, TableEquipe equipe, TableResultat resultat) throws IFT287Exception
	{
		this.cx = ligue.getConnexion();
		if(ligue.getConnexion() != equipe.getConnexion())
			throw new IFT287Exception("Les instance de ligue et equipe n'utilise pas la meme connexion au serveur.");
		if(ligue.getConnexion() != resultat.getConnexion())
			throw new IFT287Exception("Les instance de ligue et resultat n'utilise pas la meme connexion au serveur.");
		
		this.ligue = ligue;
		this.equipe = equipe;
		this.resultat = resultat;
		
	}
	
	public void ajouterLigue(String nomLigue, int nbJoueursMaxEquipe) 
			throws SQLException, IFT287Exception, Exception
	{
		try {
			
			//Verifie si la ligue existe deja
			if(ligue.existe(nomLigue))
				throw new IFT287Exception("Ligue " + nomLigue + " existe deja.");
			
			//Ajout du ligue dans la table des ligues
			ligue.ajouterLigue(nomLigue, nbJoueursMaxEquipe);
			
			//Commit
			cx.commit();
			
			
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}
	
	public void supprimerLigue(String nomLigue)
			throws SQLException, IFT287Exception, Exception
	{
		try
		{
			//Validation
			TupleLigue tupleLigue = ligue.getLigue(nomLigue);		
			
			if(tupleLigue == null)
				throw new IFT287Exception("Ligue " + nomLigue + " n'existe pas.");
			
			//Suppression de la ligue
			int nb = ligue.supprimerLigue(nomLigue);
			if (nb == 0)
				throw new IFT287Exception("Ligue " + nomLigue + " n'existe pas.");
			
			//Commit
			cx.commit();
		
		}
		catch(Exception e)
		{
			cx.rollback();
			throw e;
		}		
	}

	public void afficherLigue(String nomLigue) {
		// TODO Auto-generated method stub
		
	}
	
}


