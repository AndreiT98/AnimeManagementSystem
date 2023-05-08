/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Andrei
 */
public class RegisterController {

    public boolean registerUser(String user, String pass, String email) {
        try (Connection con = DbConnector.getConnection();){
            String query = "INSERT INTO users (Username, Password, EMail) VALUES (?,?,?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, user);
            st.setString(2, pass);
            st.setString(3, email);
            st.executeUpdate();
            
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean checkUserDuplicate(String user) {
        try (Connection con = DbConnector.getConnection();) {
            String query = "SELECT Username FROM users WHERE Username = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, user);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
