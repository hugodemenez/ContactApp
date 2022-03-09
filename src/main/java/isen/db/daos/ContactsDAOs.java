/**
 * 
 */
package isen.db.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import isen.db.entities.Contact;


/**
 * @author dumes
 *
 */
public class ContactsDAOs {

	
	public void add(Contact contact) {
		try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
	        String sqlQuery = "INSERT INTO contacts(lastname,firstname,nickname,phone_number,address,email_address,birth_date) VALUES(?,?,?,?,?,?,?)";
	        try (PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
	            statement.setString(1, contact.getLastname());
	            statement.setString(2, contact.getFirstname());
	            statement.setString(3, contact.getNickname());
	            statement.setString(4, contact.getPhone_number());
	            statement.setString(5, contact.getAddress());
	            statement.setString(6, contact.getEmail_address());
	            statement.setDate(7, contact.getBirth_date());
	            statement.executeUpdate();
	            statement.getGeneratedKeys();
	        }
	    } 
		catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
}
