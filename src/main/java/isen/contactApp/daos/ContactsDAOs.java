/**
 * 
 */
package isen.contactApp.daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import isen.contactApp.entities.Contact;

import static isen.contactApp.daos.DataSourceFactory.getDataSource;


/**
 * @author dumes
 *
 */
public class ContactsDAOs {

	
	public void addContactToDb(Contact contact) {
		try (Connection connection = getDataSource().getConnection()) {
	        String sqlQuery = "INSERT INTO person(lastname,firstname,nickname,phone_number,address,email_address,birth_date) VALUES(?,?,?,?,?,?,?)";
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

	public List<Contact> getContactsFromDb() {
		List<Contact> listOfContacts = new ArrayList<>();

		try(Connection connection = getDataSource().getConnection()){
			try(Statement statement = connection.createStatement()){
				try(ResultSet results = statement.executeQuery("SELECT * FROM film JOIN genre ON film.genre_id = genre.idgenre")){
					while(results.next()){


						listOfContacts.add( new Contact(
								results.getInt("idperson"),
								results.getString("lastname"),
								results.getString("firstname"),
								results.getString("nickname"),
								results.getString("phone_number"),
								results.getString("address"),
								results.getString("email_address"),
								results.getDate("birthdate")));

					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listOfContacts;

	}
	
}
