package CentreSportif;

import java.sql.SQLException;

import CentreSportif.IFT287Exception;
import CentreSportif.TupleParticipant;

public class GestionParticipant {

    private Connexion cx;
    private TableParticipant participant;
    private TableJoueur joueur;
    
    /*
     * Creation d'une instance
     */
    public GestionParticipant(TableParticipant participant, TableJoueur joueur)throws IFT287Exception
    {
        this.cx = participant.getConnexion();
        if(participant.getConnexion() != joueur.getConnexion())
        	throw new IFT287Exception("Les instance de participant et joueur n'utilise pas la meme connexion au serveur.");
        this.participant = participant;
        this.joueur = joueur;
    }
    
    /*
     * Inscription d'un participant
     */
    public void inscrireParticipant(String prenom, String nom, String motDePasse, String matricule)
    	throws SQLException,IFT287Exception, Exception
    {
    	try
    	{
    		//Verifie si le participant existe deja
    		if(participant.existe(matricule))
    			throw new IFT287Exception("Participant deja existant: " + prenom + " " + nom + " " + matricule);
    		
    		//Ajout du participant dans la table de participant
    		participant.inscrireParticipant(prenom, nom, motDePasse, matricule);
    		
    		//Commit
    		cx.commit();
    	}
    	catch(Exception e)
    	{
    		cx.rollback();
    		throw e;
    	}
    }
    
    public boolean informationsConnexionValide(String utilisateur, String motDePasse) 
            throws SQLException, IFT287Exception
    {
        try
        {
            // Vérifie si le membre existe déja
            if (!participant.existe(utilisateur))
                throw new IFT287Exception("Aucun utilisateur n'existe avec ce nom.");


            TupleParticipant user = participant.getParticipant(utilisateur);
            if(!user.getMotdepasse().equals(motDePasse))
                throw new IFT287Exception("Mauvais mot de passe.");
            
            // Commit
            cx.commit();
            
            return true;
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
    
    
    /*
     *	Suppression d'un participant, seulement s'il n'est pas dans une equipe. 
     */
    
    public void supprimerParticipant(String matricule)
    	throws SQLException, IFT287Exception, Exception
    {
    	try
    	{
	    	//Validation
	    	TupleParticipant tupleParticipant = participant.getParticipant(matricule); 
	    	
	    	if(tupleParticipant ==  null)
	    		throw new IFT287Exception("Participant matricule " + matricule + " non-existant.");
	    	if(joueur.existe(matricule))
	    		throw new IFT287Exception("Joueur matricule " + matricule + " fait partie d'une equipe.");
		    	
	    	//Suppression du participant
	    	int nb = participant.supprimerParticipant(matricule);
	    	if(nb == 0)
	    		throw new IFT287Exception("Participant matricule " + matricule + " non-existant.");
	    	
	    	//Commit
	    	cx.commit();
    	}
    	catch(Exception e)
    	{
    		cx.rollback();
    		throw e;
    	}
    }
}
