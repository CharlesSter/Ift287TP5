// Travail fait par :
//   NomEquipier1 - Matricule
//   NomEquipier2 - Matricule

package CentreSportif;

import java.io.*;
import java.util.StringTokenizer;
import java.sql.*;

/**
 * Fichier de base pour le TP2 du cours IFT287
 *
 * <pre>
 * 
 * Vincent Ducharme
 * Universite de Sherbrooke
 * Version 1.0 - 16 août 2018
 * IFT287 - Exploitation de BD relationnelles et OO
 * 
 * Ce programme permet d'appeler des transactions d'un systeme
 * de gestion utilisant une base de donnees.
 *
 * Paramètres du programme
 * 0- site du serveur SQL ("local" ou "dinf")
 * 1- nom de la BD
 * 2- user id pour etablir une connexion avec le serveur SQL
 * 3- mot de passe pour le user id
 * 4- fichier de transaction [optionnel]
 *           si non spécifié, les transactions sont lues au
 *           clavier (System.in)
 *
 * Pré-condition
 *   - La base de donnees doit exister
 *
 * Post-condition
 *   - Le programme effectue les mises à jour associees à chaque
 *     transaction
 * </pre>
 */
public class CentreSportif
{
    private static GestionCentreSportif gestionCentreSportif;
    private boolean echo;

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        if (args.length < 4)
        {
            System.out.println("Usage: java CentreSportif.CentreSportif <serveur> <bd> <user> <password> [<fichier-transactions>]");
            return;
        }
        
        CentreSportif centreSportifInstance = null;
        
        try
        {
        	
        	boolean lectureClavier = true;
        	InputStream sourceTransaction = System.in;
        	if(args.length > 4)
        	{
        		sourceTransaction = new FileInputStream(args[4]);
        		lectureClavier = false;
        	}
        	
            //cx = new Connexion(args[0], args[1], args[2], args[3]);
            BufferedReader reader = ouvrirFichier(args);
            
            centreSportifInstance = new CentreSportif(args[0], args[1], args[2], args[3]);
            centreSportifInstance.setFaireEcho(!lectureClavier);
                        
            String transaction = lireTransaction(reader);
            while (!finTransaction(transaction))
            {
                executerTransaction(transaction);
                transaction = lireTransaction(reader);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace(System.out);
        }
        finally
        {
            if(centreSportifInstance != null)
            	centreSportifInstance.fermer();
        }
       
    }
    
    /*
     * 
     * 
     * 
     */
    
    public CentreSportif(String serveur, String bd, String user, String password) throws Exception
    {
    	gestionCentreSportif = new GestionCentreSportif(serveur, bd, user, password);
    }
    
    public void setFaireEcho(boolean echo)
    {
    	this.echo = echo;
    }
    
    public void fermer() throws Exception
    {
    	gestionCentreSportif.fermer();
    }
    
    
    /*
     * 
     * 
     * 
     */
    
    
    /**
     * Decodage et traitement d'une transaction
     */
    private static void executerTransaction(String transaction) throws Exception, IFT287Exception
    {
        try
        {
            System.out.println(transaction);
            // Decoupage de la transaction en mots
            StringTokenizer tokenizer = new StringTokenizer(transaction, " ");
            if (tokenizer.hasMoreTokens())
            {
                String command = tokenizer.nextToken();
              
                // *************************
                // INSCRIRE PARTICIPANT
                // *************************
                if (command.equals("inscrireParticipant"))
                {
                	String prenom = readString(tokenizer);
                	String nom = readString(tokenizer);
                	String motDePasse = readString(tokenizer);
                	String matricule = readString(tokenizer);
                	
                	gestionCentreSportif.getGestionParticipant().inscrireParticipant(prenom, nom, motDePasse, matricule);
                }
                // *************************
                // SUPPRIMER PARTICIPANT
                // *************************
                else if (command.equals("supprimerParticipant"))
                {
                	String matricule = readString(tokenizer);
                	
                	gestionCentreSportif.getGestionParticipant().supprimerParticipant(matricule);
                }
            	// *************************
                // AJOUTER LIGUE
                // *************************
                else if (command.equals("ajouterLigue"))
                {
                	String nomLigue = readString(tokenizer);
                	int nbJoueursMaxEquipe = readInt(tokenizer);
                    gestionCentreSportif.getGestionLigue().ajouterLigue(nomLigue, nbJoueursMaxEquipe);
                    
                }
                // *************************
                // SUPPRIMER LIGUE
                // *************************
                else if (command.equals("supprimerLigue"))
                {
                	String nomLigue = readString(tokenizer);
                	
                    gestionCentreSportif.getGestionLigue().supprimerLigue(nomLigue);
                    
                }
                // *************************
                // AJOUTER EQUIPE
                // *************************
                else if (command.equals("ajouterEquipe"))
                {
                	String nomLigue = readString(tokenizer);
                	String nomEquipe = readString(tokenizer);
                	String capitaine = readString(tokenizer);
                    gestionCentreSportif.getGestionEquipe().ajouterEquipe(nomLigue, nomEquipe, capitaine);
                    
                }

                // *************************
                // AJOUTER JOUEUR
                // *************************
                else if (command.equals("ajouterJoueur"))
                {
                	
                	String nomEquipe = readString(tokenizer);
                	String matricule = readString(tokenizer);
                	
                    gestionCentreSportif.getGestionJoueur().ajouterJoueur(nomEquipe, matricule);
                    
                    
                }

                // *************************
                // ACCEPTER JOUEUR
                // *************************
                else if (command.equals("accepterJoueur"))
                {
                	String nomEquipe = readString(tokenizer);
                	String matricule = readString(tokenizer);
            	
                	gestionCentreSportif.getGestionJoueur().accepterJoueur(nomEquipe, matricule);
                    
                }
                // *************************
                // REFUSER JOUEUR 
                // *************************
                else if (command.equals("refuserJoueur"))
                {
                	String nomEquipe = readString(tokenizer);
                	String matricule = readString(tokenizer);
                	
                	gestionCentreSportif.getGestionJoueur().refuserJoueur(nomEquipe, matricule);
                    
                }
                // *************************
                // SUPPRIMER JOUEUR
                // *************************
                else if (command.equals("supprimerJoueur"))
                {
                	String nomEquipe = readString(tokenizer);
                	String matricule = readString(tokenizer);
                	
                	gestionCentreSportif.getGestionJoueur().supprimerJoueur(nomEquipe, matricule);
             
                }
                // *************************
                // AFFICHER EQUIPES
                // *************************
                else if (command.equals("afficherEquipes"))
                {
                    gestionCentreSportif.getGestionInterrogation().afficherEquipes();
                    
                }
                // *************************
                // AFFICHER EQUIPE
                // *************************
                else if (command.equals("afficherEquipe"))
                {
                	String nomEquipe = readString(tokenizer);
                	
                	gestionCentreSportif.getGestionInterrogation().afficherEquipe(nomEquipe);
                    
                }
                // *************************
                // AFFICHER  LIGUE
                // *************************
                else if (command.equals("afficherLigue"))
                {
                	String nomLigue = readString(tokenizer);
                	
                    gestionCentreSportif.getGestionInterrogation().afficherLigue(nomLigue);
                                       
                }
                // *************************
                // AJOUTER RESULTAT
                // *************************
                else if (command.equals("ajouterResultat"))
                {
                	String nomEquipeA = readString(tokenizer);
                	int scoreEquipeA = readInt(tokenizer);
                 
                	String nomEquipeB = readString(tokenizer);
                	int scoreEquipeB = readInt(tokenizer);
                	
                	gestionCentreSportif.getGestionResultat().ajouterResultat(nomEquipeA, scoreEquipeA, nomEquipeB, scoreEquipeB);
                    
                }

                // *************************
                // COMMENTAIRE
                // *************************
                else if (command.equals("--"))
                {
                	//Rien, c'est un commentaire.
                }
                else if (command.equals("quitter"))
                {
                	System.out.println("A IMPLEMENTER");
                }
                else
                {
                    System.out.println(" : Transaction non reconnue");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(" " + e.toString());
        }
    }

    
    // ****************************************************************
    // *   Les methodes suivantes n'ont pas besoin d'etre modifiees   *
    // ****************************************************************

    /**
     * Ouvre le fichier de transaction, ou lit à partir de System.in
     */
    public static BufferedReader ouvrirFichier(String[] args) throws FileNotFoundException
    {
        if (args.length < 5)
            // Lecture au clavier
            return new BufferedReader(new InputStreamReader(System.in));
        else
            // Lecture dans le fichier passe en parametre
            return new BufferedReader(new InputStreamReader(new FileInputStream(args[4])));
    }

    /**
     * Lecture d'une transaction
     */
    static String lireTransaction(BufferedReader reader) throws IOException
    {
        return reader.readLine();
    }

    /**
     * Verifie si la fin du traitement des transactions est atteinte.
     */
    static boolean finTransaction(String transaction)
    {
        // fin de fichier atteinte
        return (transaction == null || transaction.equals("quitter"));
    }

    /** Lecture d'une chaine de caracteres de la transaction entree a l'ecran */
    static String readString(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
            return tokenizer.nextToken();
        else
            throw new Exception("Autre parametre attendu");
    }

    /**
     * Lecture d'un int java de la transaction entree a l'ecran
     */
    static int readInt(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            try
            {
                return Integer.valueOf(token).intValue();
            }
            catch (NumberFormatException e)
            {
                throw new Exception("Nombre attendu a la place de \"" + token + "\"");
            }
        }
        else
            throw new Exception("Autre parametre attendu");
    }

    static Date readDate(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            try
            {
                return Date.valueOf(token);
            }
            catch (IllegalArgumentException e)
            {
                throw new Exception("Date dans un format invalide - \"" + token + "\"");
            }
        }
        else
            throw new Exception("Autre parametre attendu");
    }

}
