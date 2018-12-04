package CentreSportif;

import java.sql.SQLException;

public class GestionEquipe {
    
    private Connexion cx;
    private TableEquipe equipe;
    private TableParticipant participant;
    private TableJoueur joueur;
    private TableLigue ligue;
        
    /*
     * Creation d'une instance
     */    
    public GestionEquipe(TableEquipe equipe, TableParticipant participant, TableJoueur joueur, TableLigue ligue)
    		throws IFT287Exception, SQLException
    {
        this.cx = equipe.getConnexion();
        
    	if(equipe.getConnexion() != participant.getConnexion())
			throw new IFT287Exception("Les instance de equipe et participant n'utilise pas la meme connexion au serveur.");
		if(equipe.getConnexion() != joueur.getConnexion())
			throw new IFT287Exception("Les instance de equipe et joueur n'utilise pas la meme connexion au serveur.");
		if(equipe.getConnexion() != ligue.getConnexion())
			throw new IFT287Exception("Les instance de equipe et ligue n'utilise pas la meme connexion au serveur.");
		
		this.equipe = equipe;
		this.participant = participant;
		this.joueur = joueur;
		this.ligue = ligue;
		
    }
    
    public void ajouterEquipe(String nomLigue, String nomEquipe, String capitaine)
    		throws SQLException, IFT287Exception, Exception
    {
    	try {
    		//Verifier si l'equipe existe
    		if(equipe.existe(nomEquipe))
    			throw new IFT287Exception("Equipe " + nomEquipe + " existe deja.");
    		if(joueur.getJoueur(capitaine) != null)
    			throw new IFT287Exception("Joueur " + capitaine + "deja capitaine d'une equipe.");
    		if(participant.getParticipant(capitaine) == null)
    			throw new IFT287Exception("Participant " + capitaine + "non-existant.");
    		if(ligue.getLigue(nomLigue) == null)
    			throw new IFT287Exception("Ligue " + nomLigue + " non-existante.");
    		
    		
    		//Ajout de l'equipe dans la table des equipe
    		equipe.ajouterEquipe(nomEquipe, capitaine, nomLigue);
    		
    		//Commit
    		cx.commit();
    		
			
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
    	
    }

}
