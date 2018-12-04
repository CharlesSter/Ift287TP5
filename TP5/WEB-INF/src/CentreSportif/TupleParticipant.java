package CentreSportif;

public class TupleParticipant {

    private String prenom;
    private String nom;
    private String motdepasse;
    private String matricule;
    private int acces;
    
    public TupleParticipant()
    {
        
    }
    
    public TupleParticipant(String prenom,String nom,String motdepasse,String matricule, int acces)
    {
        this.setMatricule(matricule);
        this.setMotdepasse(motdepasse);
        this.setPrenom(prenom);
        this.setNom(nom);
        this.setAcces(acces);
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
    
    public int getAcces()
    {
        return acces;
    }

    public void setAcces(int acces)
    {
        this.acces = acces;
    }
    
    
}
