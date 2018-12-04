package CentreSportif;

public class TupleJoueur {
	
	private String nomEquipe;
	private String matricule;
	private boolean enAttente;
	
	public TupleJoueur ()
	{
		
	}
	
	public TupleJoueur(String nomEquipe, String matricule, boolean enAttente)
	{
		this.nomEquipe = nomEquipe;
		this.matricule = matricule;
		this.enAttente = enAttente;
		
	}

	public String getNomEquipe() {
		return nomEquipe;
	}

	public void setNomEquipe(String nomEquipe) {
		this.nomEquipe = nomEquipe;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public boolean isEnAttente() {
		return enAttente;
	}

	public void setEnAttente(boolean enAttente) {
		this.enAttente = enAttente;
	}

}
