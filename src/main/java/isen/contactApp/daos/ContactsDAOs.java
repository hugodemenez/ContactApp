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
 * @author Quentin DUMESGE, Hugo DEMENEZ, Alban DUHAMEL
 *
 */
public class ContactsDAOs {

	
	public static Contact addContactToDb(Contact contact) {
		try (Connection connection = getDataSource().getConnection()) {
	        String sqlQuery = "INSERT INTO person(lastname,firstname,nickname,phone_number,address,email_address,birth_date,gender) VALUES(?,?,?,?,?,?,?,?)";
	        try (PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
	            statement.setString(1, contact.getLastname());
	            statement.setString(2, contact.getFirstname());
	            statement.setString(3, contact.getNickname());
	            statement.setString(4, contact.getPhone_number());
	            statement.setString(5, contact.getAddress());
	            statement.setString(6, contact.getEmail_address());
	            statement.setDate(7, contact.getBirth_date());
				statement.setString(8, contact.getGender());
	            statement.executeUpdate();
	            contact.setIdPerson(statement.getGeneratedKeys().getInt(1));
	        }
	    } 
		catch (SQLException e) {
	        e.printStackTrace();
	    }

		return contact;
	}

	public static List<Contact> getContactsFromDb() {
		List<Contact> listOfContacts = new ArrayList<>();

		try(Connection connection = getDataSource().getConnection()){
			try(Statement statement = connection.createStatement()){
				try(ResultSet results = statement.executeQuery("SELECT * FROM person")){
					while(results.next()){
						listOfContacts.add( new Contact(
								results.getInt("idperson"),
								results.getString("lastname"),
								results.getString("firstname"),
								results.getString("nickname"),
								results.getString("phone_number"),
								results.getString("address"),
								results.getString("email_address"),
								results.getDate("birth_date"),
								results.getString("gender")));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listOfContacts;

	}

	public static int getContactIdFromDb(Contact contact) {


		try(Connection connection = getDataSource().getConnection()){
			try(Statement statement = connection.createStatement()){
				try(ResultSet results = statement.executeQuery("SELECT * FROM person")){
					while(results.next()){
						if (results.getString("lastname").equals(contact.getLastname()) && results.getString("firstname").equals(contact.getFirstname())){
							return results.getInt("idperson");
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		throw new RuntimeException("Unable to find id of the selected contact");

	}

	public static void removeContactFromDb(Contact contact){
		try {
			Connection connection = DataSourceFactory.getDataSource().getConnection();
			Statement stmt = connection.createStatement();


			stmt.executeUpdate("DELETE FROM person WHERE idperson = "+contact.getIdperson()+";");
			stmt.close();
			connection.close();
			System.out.println(contact.getLastname()+contact.getFirstname()+" sucessfully removed from db");
		}
		catch(Exception exception){
			System.out.println(exception.getMessage());
		}




	}
	
}
