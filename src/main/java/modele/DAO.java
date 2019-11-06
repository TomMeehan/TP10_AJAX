/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author pedago
 */
public class DAO {
    
    protected final DataSource myDataSource;

    /**
     *
     * @param dataSource la source de données à utiliser
     */
    public DAO(DataSource dataSource) {
            this.myDataSource = dataSource;
    }
    
    
    public LinkedList<DiscountEntity> getDiscountCodes() throws SQLException, Exception {
        LinkedList<DiscountEntity> codes = new LinkedList<>();
        
        String sql = "SELECT * FROM discount_code";
        
        try (Connection connection = myDataSource.getConnection(); // Ouvrir une connexion
            Statement stmt = connection.createStatement(); // On crée un statement pour exécuter une requête
            ResultSet rs = stmt.executeQuery(sql) // Un ResultSet pour parcourir les enregistrements du résultat
	){
            while(rs.next()){
                codes.add(new DiscountEntity( rs.getString("DISCOUNT_CODE"), rs.getFloat("RATE"))); 
            }
        }catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
        
        return codes;
    }
    
    public int addDiscountCode( DiscountEntity dc ) throws SQLException, Exception {
        
        String sql = "INSERT INTO discount_codes (discount_code,rate) VALUES (?,?)";
        
        try (   Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
                stmt.setString(1, dc.getCode());
                stmt.setFloat(1, dc.getRate());

                return stmt.executeUpdate();

        }  catch (SQLException ex) {
                Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
                throw new Exception(ex.getMessage());
        }
    }
    
    public int deleteDiscountCode( String code ) throws SQLException, Exception {
        
        String sql = "DELETE FROM discount_codes WHERE discount_code = ?";
        
        try (   Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
                stmt.setString(1, code);

                return stmt.executeUpdate();

        }  catch (SQLException ex) {
                Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
                throw new Exception(ex.getMessage());
        }
    }
}
