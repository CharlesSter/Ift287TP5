package CentreSportif;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionInterrogation {
	
	private Connexion cx;
    private PreparedStatement stmtAfficheToutEquipe;
    private PreparedStatement stmtAfficheEquipeEtResultat; 
    private PreparedStatement stmtAfficheLigue; 
    
    public GestionInterrogation(Connexion cx) throws SQLException
    {
    	this.cx = cx;
    	stmtAfficheToutEquipe = cx.getConnection().prepareStatement( 
				"select nomLigue, nomEquipe from Equipe order by nomLigue");
    
        stmtAfficheEquipeEtResultat= cx.getConnection().prepareStatement(
    			"select eq.nomLigue as ligue, eq.nomEquipe equipe, eq.matriculecapitaine as capitaine, re.equipea as equipea, re.resultata as resultata, re.equipeb as equipeb, re.resultatb as resultatb"
    			+ " from equipe eq, resultat re "
    			+ " where eq.nomEquipe = ? and (re.equipea = ? or re.equipeb = ?)"); 
       
        stmtAfficheLigue= cx.getConnection().prepareStatement(
    			"select 	eq.nomEquipe as nomEquipe,\r\n" + 
    			"sum( \r\n" + 
    			"Case\r\n" + 
    			"when res.equipea = eq.nomEquipe and resultata > resultatb then 1\r\n" + 
    			"when res.equipeb = eq.nomEquipe and resultata < resultatb then 1\r\n" + 
    			"else 0\r\n" + 
    			"end\r\n" + 
    			") as victoire,\r\n" + 
    			"sum(\r\n" + 
    			"Case\r\n" + 
    			"when res.equipea = eq.nomEquipe and resultata < resultatb then 1\r\n" + 
    			"when res.equipeb = eq.nomEquipe and resultata > resultatb then 1\r\n" + 
    			"else 0\r\n" + 
    			"end\r\n" + 
    			") as defaite,\r\n" + 
    			"sum(\r\n" + 
    			"Case\r\n" + 
    			"when res.equipea = eq.nomEquipe and resultata = resultatb then 1\r\n" + 
    			"when res.equipeb = eq.nomEquipe and resultata = resultatb then 1\r\n" + 
    			"else 0\r\n" + 
    			"end\r\n" + 
    			") as nulle	\r\n" + 
    			"from equipe eq, resultat res   where eq.nomLigue = ?   group by eq.nomEquipe"); 
    }
    
    public void afficherEquipes() throws SQLException
	{
		ResultSet rset = stmtAfficheToutEquipe.executeQuery();
		
		System.out.println("NomLigue  NomEquipe");
		
		while(rset.next())
		{
			System.out.println(rset.getString("nomLigue") + " " + rset.getString("nomEquipe"));	
		}
		
		cx.commit();
	}
    
    public void afficherEquipe(String nomEquipe) throws SQLException 
    {
		
    	stmtAfficheEquipeEtResultat.setString(1, nomEquipe);
    	stmtAfficheEquipeEtResultat.setString(2, nomEquipe);
    	stmtAfficheEquipeEtResultat.setString(3, nomEquipe);
    	
    	ResultSet rset = stmtAfficheEquipeEtResultat.executeQuery();
		
    	if(!rset.next())
    		return;
    	
    	System.out.println("Ligue: " + rset.getString("ligue"));
    	System.out.println("Equipe: " + nomEquipe);
    	System.out.println("Capitaine: " + rset.getString("capitaine"));
    	
    	System.out.println("Resultat: ");
    	System.out.println("Equipe A   Resultat A    Equipe B   Resultat B");
    	
    	while(rset.next())
    	{
	    	System.out.print(rset.getString("equipea") + "  ");
	    	System.out.print(rset.getString("resultata") + "    ");
	    	System.out.print(rset.getString("equipeb") + "   ");
	    	System.out.print(rset.getString("resultatb"));
	    	System.out.println();
    	}
    	
    	System.out.println();
    	
    	cx.commit();
	}

	public void afficherLigue(String nomLigue) throws SQLException {
			
		stmtAfficheLigue.setString(1, nomLigue);
		
		ResultSet rset =  stmtAfficheLigue.executeQuery();
		
		System.out.println("Ligue: " + nomLigue);
		System.out.println();
		System.out.println("Equipe   Victoire   Defaite   Nulle");
		
		while(rset.next())
		{
			System.out.print(rset.getString("nomEquipe") + "   ");
			System.out.print(rset.getString("victoire") + "   ");
			System.out.print(rset.getString("defaite") + "   ");
			System.out.print(rset.getString("nulle") + "   ");
			System.out.println();
		}
		
		System.out.println();
		
		cx.commit();
		
	}
    
    

}
