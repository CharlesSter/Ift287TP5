package CentreSportif;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableEquipe {
    
    private PreparedStatement stmtExiste;
    private PreparedStatement stmtInsert;
    private PreparedStatement stmtDelete;
    
    private Connexion cx;
    
    public TableEquipe(Connexion cx) throws SQLException
    {
        this.cx = cx;
        
        stmtExiste = cx.getConnection().prepareStatement(
                "Select nomEquipe, matriculecapitaine, nomligue from equipe where nomequipe = ?");
        stmtInsert = cx.getConnection().prepareStatement(
                "Insert into equipe (nomEquipe, matriculeCapitaine, nomLigue) values (?, ?, ?)");
        stmtDelete = cx.getConnection().prepareStatement(
                "delete from equipe where nomEquipe = ?");
    }
    
    /*
     * Retourne la connexion associer.
     */    
    public Connexion getConnexion()
    {
        return cx;
    }
    
    /*
     * Verifie si l'equipe existe
     */
    public boolean existe(String nomEquipe)throws SQLException
    {
        stmtExiste.setString(1, nomEquipe);
        ResultSet rset = stmtExiste.executeQuery();
        boolean equipeExiste = rset.next();
        rset.close();
        return equipeExiste;
    }
    
    /*
     * Lecutre d'une equipe
     */
    public TupleEquipe getEquipe(String nomEquipe)throws SQLException
    {
        stmtExiste.setString(1, nomEquipe);
        ResultSet rset = stmtExiste.executeQuery();
        
        if(rset.next())
        {
            TupleEquipe tupleEquipe = new TupleEquipe();
            
            tupleEquipe.setNomEquipe(nomEquipe);
            tupleEquipe.setMatriculeCapitaine(rset.getString(2));
            tupleEquipe.setNomLigue(rset.getString(3));
            
            rset.close();
            
            return tupleEquipe;
        }
        else
            return null;
    }
    
    /*
     * Ajout d'une nouvelle equipe dans la base de donnee
     */
    public void ajouterEquipe(String nomEquipe, String matriculeCapitaine, String nomLigue) throws SQLException
    {
        /*Ajout de l'equipe*/
        stmtInsert.setString(1, nomEquipe);
        stmtInsert.setString(2, matriculeCapitaine);
        stmtInsert.setString(3, nomLigue);
        stmtInsert.executeUpdate();
        
    }
    
    /*
     * Suppression d'un equipe
     */
    public int supprimerEquipe(String nomEquipe) throws SQLException
    {
        stmtDelete.setString(1, nomEquipe);
        return stmtDelete.executeUpdate();
    }
    
    

}
