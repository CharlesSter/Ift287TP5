package CentreSportif;

import java.util.ArrayList;

public class TupleLigue {
	
	private String nomLigue;
	private int nbJoueursMaxEquipe;
	
	public TupleLigue()
	{
		
	}
	
	public TupleLigue(String nl, int njme)
	{
		this.setNomLigue(nl);
		this.setNbJouersMaxEquipe(njme);		
	}
	
	public void setNomLigue(String nl)
	{
		this.nomLigue = nl;
	}

	public void setNbJouersMaxEquipe(int njme)
	{
		this.nbJoueursMaxEquipe = njme;
	}
	
	public String getNomLigue()
	{
		return nomLigue;
	}
	
	public int getNbJouersMaxEquipe()
	{
		return nbJoueursMaxEquipe;
	}

}
