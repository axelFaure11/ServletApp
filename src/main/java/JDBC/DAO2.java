/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import simplejdbc.DAO;
import simplejdbc.DAOException;

/**
 *
 * @author pedago
 */
public class DAO2 extends DAO{
    
    public DAO2(DataSource dataSource) {
        super(dataSource);
    }
    
    public List<String> existingStates() throws SQLException, DAOException {
        
        String sql = "SELECT DISTINCT STATE FROM CUSTOMER";
        List<String> result = new ArrayList<String>();
        try(Connection connection = myDataSource.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
                while(rs.next()){
                    result.add(rs.getString("STATE"));
                }
            }
            return result;            
        }
    }

