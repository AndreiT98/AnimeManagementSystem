/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


/**
 *
 * @author Andrei
 */

public class LoginController {
    
    public boolean checkAccount(String username, String psw){
        try(Connection con = DbConnector.getConnection()){
            String query = "SELECT Username, Password from users WHERE Username = ? and Password = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, username);
            st.setString(2, psw);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                System.out.println("account found");
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("Account not found)");
        return false;
    }
    
  
    
}



