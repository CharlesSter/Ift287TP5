package CentreSportif;

import java.sql.SQLException;
import java.util.ArrayList;

public class GestionResultat {
	
	private Connexion cx;
	private TableResultat resultat;
	private TableEquipe	equipe;
	
	/*
	 * Creation d'une instance
	 */
	
	public GestionResultat(TableResultat resultat, TableEquipe equipe) throws IFT287Exception
	{
		this.cx = resultat.getConnexion();
		if(resultat.getConnexion() != equipe.getConnexion())
			throw new IFT287Exception("Les instance de resultat et equipe n'utilie pas la meme connexion.");
		
		this.resultat = resultat;
		this.equipe = equipe;
	}
	
	public void ajouterResultat(String nomEquipeA, int scoreEquipeA, String nomEquipeB, int scoreEquipeB)
			throws SQLException, IFT287Exception, Exception
	{
		try {
			if(scoreEquipeA < 0)
				throw new IFT287Exception("Score equipe A " + nomEquipeA  + " negatif.");
			if(scoreEquipeB < 0)
				throw new IFT287Exception("Score equipe B " + nomEquipeB  + " negatif.");
			if(equipe.getEquipe(nomEquipeA) == null)
				throw new IFT287Exception("Equipe A " + nomEquipeA + " n'existe pas.");
			if(equipe.getEquipe(nomEquipeB) == null)
				throw new IFT287Exception("Equipe B " + nomEquipeB + " n'existe pas.");
						
			resultat.ajouterResultat(resultat.getMaxId(), nomEquipeA, scoreEquipeA, nomEquipeB, scoreEquipeB);
						
			//Commit
			cx.commit();
		}
		catch(Exception e) {
			cx.rollback();
			throw e;
		}
	}
	
	public void supprimerResultat(String nomEquipe)
		throws SQLException, IFT287Exception, Exception
	{
		try {
			//Validation
			ArrayList<TupleResultat> tupleResultat = resultat.getResultat(nomEquipe);
			
			if(tupleResultat == null)
				throw new IFT287Exception("Aucun resultat pour equipe: " + nomEquipe);
			
			//Suppression des resultat
			int nb = resultat.supprimerResultat(nomEquipe);
			if(nb == 0)
				throw new IFT287Exception("Aucun resultat pour equipe: " + nomEquipe);			
			
			//Commit
			cx.commit();
			
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

}
