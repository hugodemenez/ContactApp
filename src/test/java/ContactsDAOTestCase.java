import isen.contactApp.daos.ContactsDAOs;
import isen.contactApp.daos.DataSourceFactory;
import isen.contactApp.entities.Contact;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;

public class ContactsDAOTestCase {

	ContactsDAOs contactDAO = new ContactsDAOs(); 

    @Before
    public void initDb() throws Exception {
        Connection connection = DataSourceFactory.getDataSource().getConnection();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS person (\n" +
                        "    idperson INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, \n" +
                        "    lastname VARCHAR(45) NOT NULL,  \n" +
                        "    firstname VARCHAR(45) NOT NULL,\n" +
                        "    nickname VARCHAR(45) NOT NULL,\n" +
                        "    phone_number VARCHAR(15) NULL,\n" +
                        "    address VARCHAR(200) NULL,\n" +
                        "    email_address VARCHAR(150) NULL,\n" +
                        "    birth_date DATE NULL,\n" +
                        "    gender VARCHAR(150) NULL);");
        stmt.executeUpdate("DELETE FROM person");
        
        stmt.executeUpdate("INSERT INTO genre(idperson,lastname,firstname,nickname,phone_number,address,email_address,birth_date,gender) "
        				 + "VALUES (0,'Demenez','Hugo','hdemenez','069910196727','rue jean jaures','hugo.demenez@student.junia.com','2000-03-18','Man')");
        
        stmt.executeUpdate("INSERT INTO genre(idperson,lastname,firstname,nickname,phone_number,address,email_address,birth_date,gender) "
        				 + "VALUES (1,'Duhamel','Alban','aduhamel',06111111111,'avenue du soleil','alban.duhamel@student.junia.com','2000-09-14','Man')");
        
        stmt.executeUpdate("INSERT INTO genre(idperson,lastname,firstname,nickname,phone_number,address,email_address,birth_date,gender) "
        				 + "VALUES (2,'Dumesge','Quentin','qdumesge',03222222228,'rue de la lune','quentin.dumesge@student.junia.com','2000-10-14','Man')");
        
        stmt.close();
        connection.close();
    }

    @Test
    public void shouldAddContact() throws Exception {
        // WHEN
        Contact contact = new Contact(
                3,
                "DUVAL",
                "Philippe",
                "P.DUVAL",
                "0700000000",
                "5 rue de l'avenue",
                "philippe.duval@externe.junia.com",
                Date.valueOf("2022-03-09"),
                "Man");

        contactDAO.addContactToDb(contact);
        // THEN
        Connection connection = DataSourceFactory.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM person WHERE lastname='DUVAL'");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getInt("idperson")).isEqualTo(3);
        assertThat(resultSet.getString("lastname")).isEqualTo("DUVAL");
        assertThat(resultSet.getString("firstname")).isEqualTo("Philippe");
        assertThat(resultSet.getString("nickname")).isEqualTo("P.DUVAL");
        assertThat(resultSet.getString("phone_number")).isEqualTo("0700000000");
        assertThat(resultSet.getString("address")).isEqualTo("5 rue de l'avenue");
        assertThat(resultSet.getString("email_address")).isEqualTo("philippe.duval@externe.junia.com");
        assertThat(resultSet.getDate("birth_date")).isEqualTo(Date.valueOf("2022-03-09"));
        assertThat(resultSet.getString("gender")).isEqualTo("Man");
        resultSet.close();
        statement.close();
        connection.close();
    }


}
