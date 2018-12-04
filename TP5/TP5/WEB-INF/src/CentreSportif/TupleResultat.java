package CentreSportif;

public class TupleResultat {

	private int id;
	private String equipeA;
	private String equipeB;
	private int resultatA;
	private int resultatB;
	
	public TupleResultat()
	{
		
	}	
	
	public TupleResultat(int id, String equipeA, String equipeB, int resultatA, int resultatB) 
	{

		this.id = id;
		this.equipeA = equipeA;
		this.equipeB = equipeB;
		this.resultatA = resultatA;
		this.resultatB = resultatB;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEquipeA() {
		return equipeA;
	}

	public void setEquipeA(String equipeA) {
		this.equipeA = equipeA;
	}

	public String getEquipeB() {
		return equipeB;
	}

	public void setEquipeB(String equipeB) {
		this.equipeB = equipeB;
	}

	public int getResultatA() {
		return resultatA;
	}

	public void setResultatA(int resultatA) {
		this.resultatA = resultatA;
	}

	public int getResultatB() {
		return resultatB;
	}

	public void setResultatB(int resultatB) {
		this.resultatB = resultatB;
	}
	
	
}
