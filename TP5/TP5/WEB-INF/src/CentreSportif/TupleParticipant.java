package CentreSportif;

public class TupleParticipant {

    private String prenom;
    private String nom;
    private String motdepasse;
    private String matricule;
    
    public TupleParticipant()
    {
        
    }
    
    public TupleParticipant(String prenom,String nom,String motdepasse,String matricule)
    {
        this.setMatricule(matricule);
        this.setMotdepasse(motdepasse);
        this.setPrenom(prenom);
        this.setNom(nom);
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getMotdepasse()
    {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse)
    {
        this.motdepasse = motdepasse;
    }

    public String getMatricule()
    {
        return matricule;
    }

    public void setMatricule(String matricule)
    {
        this.matricule = matricule;
    }
    
    
}
