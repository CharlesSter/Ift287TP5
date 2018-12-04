package CentreSportif;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableParticipant {

    private PreparedStatement stmtExiste;
    private PreparedStatement stmtInsert;
    private PreparedStatement stmtDelete;
    
    private Connexion cx;
    
    public TableParticipant(Connexion cx)throws SQLException
    {
        this.cx = cx;
        stmtExiste = cx.getConnection().prepareStatement(
                "select prenom, nom, motdepasse, matricule from participant where matricule = ?");
        stmtInsert = cx.getConnection().prepareStatement(
                "insert into participant (prenom, nom, motdepasse, matricule) values(?, ?, ?, ?)");
        stmtDelete = cx.getConnection().prepareStatement(
                "delete from participant where matricule = ?");
    }
    
    public Connexion getConnexion()
    {
        return cx;
    }
    
    /*
     * Verifie si le participant existe
     */
    public boolean existe(String matricule)throws SQLException
    {
        stmtExiste.setString(1, matricule);
        ResultSet rset = stmtExiste.executeQuery();
        boolean participantExiste = rset.next();
        rset.close();
        return participantExiste;        
    }
    
    /*
     * Lecture d'un participant
     */
    public TupleParticipant getParticipant(String matricule)throws SQLException
    {
        stmtExiste.setString(1, matricule);
        ResultSet rset = stmtExiste.executeQuery();
        
        if(rset.next())
        {
            TupleParticipant tupleParticipant = new TupleParticipant();
            
            tupleParticipant.setMatricule(matricule);
            tupleParticipant.setPrenom(rset.getString(1));
            tupleParticipant.setNom(rset.getString(2));
            tupleParticipant.setMotdepasse(rset.getString(3));
            tupleParticipant.setAcces(rset.getInt(4));
            
            rset.close();
            
            return tupleParticipant;
        }
        else
            return null;
               
    }
    
    /*
     * Inscription d'un participant
     */
    public void inscrireParticipant(String prenom, String nom, String motdepasse, String matricule, int acces) throws SQLException
    {
        stmtInsert.setString(1, prenom);
        stmtInsert.setString(2, nom);
        stmtInsert.setString(3, motdepasse);
        stmtInsert.setString(4, matricule);
        stmtInsert.setInt(5, acces);
        stmtInsert.executeUpdate();        
    }
    
    /*
     * Suppression d'un participant
     */
    public int supprimerParticipant(String matricule)throws SQLException
    {
        stmtDelete.setString(1, matricule);
        return stmtDelete.executeUpdate();
    }
}
