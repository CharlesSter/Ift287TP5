package CentreSportif;

import java.sql.SQLException;

public class GestionJoueur {
	
	private Connexion cx;

	private TableJoueur joueur;
	private TableEquipe equipe;
	private TableParticipant participant;
	
	/*
	 * Créaation d'une instance
	 */
	
	public GestionJoueur(TableJoueur joueur, TableEquipe equipe, TableParticipant participant) throws IFT287Exception
	{
		this.cx = joueur.getConnexion();
		
		if(joueur.getConnexion() != equipe.getConnexion())
			throw new IFT287Exception("Les instance de joueur et equipe n'utilise pas la meme connexion au serveur.");
		if(joueur.getConnexion() != participant.getConnexion())
			throw new IFT287Exception("Les instance de joueur et participant n'utilise pas la meme connexion au serveur.");
		
		this.joueur = joueur;
		this.equipe = equipe;
		this.participant = participant;
		
	}

	public void accepterJoueur(String nomEquipe, String matricule) 
			throws SQLException, IFT287Exception, Exception
	{
		try {
			//Verification
			if(!equipe.existe(nomEquipe))
				throw new IFT287Exception("Equipe " + nomEquipe + " non-existante.");
			if(!participant.existe(matricule))
				throw new IFT287Exception("Participant " + matricule + " non-existant.");
			if(!joueur.existe(matricule))
				throw new IFT287Exception("Joueur " + matricule + " non-existant.");
				
			//Modification du Joueur
	    	int nb = joueur.modifierJoueur(matricule, false);
	    	if(nb == 0)
	    		throw new IFT287Exception("Joueur  " + matricule + " non-existant.");
			
			//Commit
			cx.commit();			
			
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
		
	}

	public void refuserJoueur(String nomEquipe, String matricule)  
			throws SQLException, IFT287Exception, Exception
	{
		try {
			//Verification
			if(!equipe.existe(nomEquipe))
				throw new IFT287Exception("Equipe " + nomEquipe + " non-existante.");
			if(!participant.existe(matricule))
				throw new IFT287Exception("Participant " + matricule + " non-existant.");
			if(!joueur.existe(matricule))
				throw new IFT287Exception("Joueur " + matricule + " non-existant.");
				
			//Suppression du joueur	    	
			supprimerJoueur(nomEquipe,matricule);		
			
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
		
		
	}

	public void supprimerJoueur(String nomEquipe, String matricule)  
			throws SQLException, IFT287Exception, Exception
	{
		try {
			//Verification
			if(!equipe.existe(nomEquipe))
				throw new IFT287Exception("Equipe " + nomEquipe + " non-existante.");
			if(!participant.existe(matricule))
				throw new IFT287Exception("Participant " + matricule + " non-existant.");
			if(!joueur.existe(matricule))
				throw new IFT287Exception("Joueur " + matricule + " non-existant.");
				
			//Suppression du Joueur
	    	int nb = joueur.supprimerJoueur(matricule);
	    	if(nb == 0)
	    		throw new IFT287Exception("Joueur  " + matricule + " non-existant.");
			
			//Commit
			cx.commit();			
			
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
		
	}

	public void ajouterJoueur(String nomEquipe, String matricule) 
			throws SQLException, IFT287Exception, Exception
	{
		try {
			//Verification
			if(!equipe.existe(nomEquipe))
				throw new IFT287Exception("Equipe " + nomEquipe + " non-existante.");
			if(!participant.existe(matricule))
				throw new IFT287Exception("Participant " + matricule + " non-existant.");
			if(joueur.existe(matricule))
				throw new IFT287Exception("Participant " + matricule + " deja joueur.");
				
			//Ajout
			joueur.ajouterJoueur(nomEquipe, matricule, true);
			
			//Commit
			cx.commit();			
			
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
		
		
	}

}
