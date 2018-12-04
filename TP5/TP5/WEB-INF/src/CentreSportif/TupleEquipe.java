package CentreSportif;

public class TupleEquipe {

	private String nomEquipe;
	private String matriculeCapitaine;
	private String nomLigue;
	
	
	
	public TupleEquipe()
	{
		
	}
	
	public TupleEquipe(String ne, String mc, String nl)
	{
		this.setNomEquipe(ne);
		this.setMatriculeCapitaine(mc);
		this.setNomLigue(nl);		
	}
	
	public String getNomEquipe() 
	{
		return nomEquipe;
	}

	public void setNomEquipe(String nomEquipe) 
	{
		this.nomEquipe = nomEquipe;
	}

	public String getMatriculeCapitaine() 
	{
		return matriculeCapitaine;
	}

	public void setMatriculeCapitaine(String matriculeCapitaine) 
	{
		this.matriculeCapitaine = matriculeCapitaine;
	}

	public String getNomLigue() 
	{
		return nomLigue;
	}

	public void setNomLigue(String nomLigue) 
	{
		this.nomLigue = nomLigue;
	}

}
