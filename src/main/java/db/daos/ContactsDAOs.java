/**
 * 
 */
package db.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author dumes
 *
 */
public class ContactsDAOs {

	
	public void add(String lastname, String firstname, String nickname, String phone_number, String address, String email_address, Date birth_date) {
		try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
	        String sqlQuery = "INSERT INTO genre (name) "+"VALUES(?)";
	        try (PreparedStatement statement = connection.prepareStatement(
	                        sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
	            statement.setString(1, lastname);
	            statement.executeUpdate();
	        }
	    }catch (SQLException e) {
	        // Manage Exception
	        e.printStackTrace();
	    }
	}
	
}
