package CentreSportif;

import java.sql.SQLException;

public class GestionCentreSportif {
	
	private Connexion cx;
	
	private TableEquipe equipe;
	private TableJoueur joueur;
	private TableLigue ligue;
	private TableParticipant participant;
	private TableResultat resultat;	

	private GestionEquipe gestionEquipe;
	private GestionJoueur gestionJoueur;
	private GestionLigue gestionLigue;
	private GestionParticipant gestionParticipant;
	private GestionResultat gestionResultat;
	private GestionInterrogation gestionInterrogation;
	
	/**
     * Ouvre une connexion avec la BD relationnelle et alloue les gestionnaires
     * de transactions et de tables.
     * 
     * @param serveur SQL
     * @param bd nom de la bade de données
     * @param user user id pour établir une connexion avec le serveur SQL
     * @param password mot de passe pour le user id
     */
	public GestionCentreSportif(String serveur, String bd, String user, String password)
		throws IFT287Exception, SQLException
	{
		//Allocation des objets pour le traitement des transactions
		cx = new Connexion(serveur, bd, user, password);
		
		equipe = new TableEquipe(cx);
		joueur = new TableJoueur(cx);
		ligue = new TableLigue(cx);
		participant = new TableParticipant(cx);
		resultat = new TableResultat(cx);
		
		setGestionEquipe(new GestionEquipe(equipe,participant,joueur,ligue));
		setGestionJoueur(new GestionJoueur(joueur, equipe, participant));
		setGestionLigue(new GestionLigue(ligue, equipe, resultat));
		setGestionParticipant(new GestionParticipant(participant, joueur));
		setGestionResultat(new GestionResultat(resultat, equipe));
		setGestionInterrogation(new GestionInterrogation(cx));
		
		
	}
	
	
	

	public Connexion getConnexion() {
		return cx;
	}


	public void fermer() throws SQLException
	{
		//Fermeture de la connexion
		cx.fermer();
	}	

	public GestionEquipe getGestionEquipe() {
		return gestionEquipe;
	}

	public void setGestionEquipe(GestionEquipe gestionEquipe) {
		this.gestionEquipe = gestionEquipe;
	}

	public GestionJoueur getGestionJoueur() {
		return gestionJoueur;
	}

	public void setGestionJoueur(GestionJoueur gestionJoueur) {
		this.gestionJoueur = gestionJoueur;
	}

	public GestionLigue getGestionLigue() {
		return gestionLigue;
	}

	public void setGestionLigue(GestionLigue gestionLigue) {
		this.gestionLigue = gestionLigue;
	}

	public GestionParticipant getGestionParticipant() {
		return gestionParticipant;
	}

	public void setGestionParticipant(GestionParticipant gestionParticipant) {
		this.gestionParticipant = gestionParticipant;
	}

	public GestionResultat getGestionResultat() {
		return gestionResultat;
	}

	public void setGestionResultat(GestionResultat gestionResultat) {
		this.gestionResultat = gestionResultat;
	}

	public GestionInterrogation getGestionInterrogation() {
		return gestionInterrogation;
	}
	
	public void setGestionInterrogation(GestionInterrogation gestionInterrogation) {
		this.gestionInterrogation = gestionInterrogation;
		
	}

}
