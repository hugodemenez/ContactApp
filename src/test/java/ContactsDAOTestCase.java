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
                            "    gender VARCHAR(150) NULL");
            stmt.close();
            connection.close();
        }

    @Test
    public void shouldAddContact() throws Exception {
        // WHEN
        Contact contact = new Contact(
                0,
                "DEMENEZ",
                "Hugo",
                "H.DEMENEZ",
                "0600000000",
                "5 rue de l'avenue",
                "hugo.demenez@student.junia.com",
                Date.valueOf("2022-03-09"),
                "Men");

        ContactsDAOs.addContactToDb(contact);
        // THEN
        Connection connection = DataSourceFactory.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM person");
        assertThat(resultSet.next()).isTrue();
        resultSet.close();
        statement.close();
        connection.close();
    }


}
