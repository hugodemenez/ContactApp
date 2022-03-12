import isen.contactApp.daos.ContactsDAOs;
import isen.contactApp.daos.DataSourceFactory;
import isen.contactApp.entities.Contact;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

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
                        "    gender VARCHAR(150) NULL,\n" +
                        "    filter VARCHAR(150) NULL);");

        stmt.executeUpdate("DELETE FROM person");

        stmt.executeUpdate("INSERT INTO person(idperson,lastname,firstname,nickname,phone_number,address,email_address,birth_date,gender,filter) "
                + "VALUES (0,'Demenez','Hugo','hdemenez','069910196727','rue jean jaures','hugo.demenez@student.junia.com','2000-03-18 00:00:00.000','Man','Favorites')");

        stmt.executeUpdate("INSERT INTO person(idperson,lastname,firstname,nickname,phone_number,address,email_address,birth_date,gender,filter) "
                + "VALUES (1,'Duhamel','Alban','aduhamel','06111111111','avenue du soleil','alban.duhamel@student.junia.com','2000-09-14 00:00:00.000','Man','Family')");

        stmt.executeUpdate("INSERT INTO person(idperson,lastname,firstname,nickname,phone_number,address,email_address,birth_date,gender,filter) "
                + "VALUES (2,'Dumesge','Quentin','qdumesge','03222222228','rue de la lune','quentin.dumesge@student.junia.com','2000-10-14 00:00:00.000','Man','Friends')");

        stmt.close();
        connection.close();
    }



    @Test
    public void shouldAddContact() throws Exception {
        // WHEN
        Contact contact = new Contact(
                "DUVAL",
                "Philippe",
                "P.DUVAL",
                "0700000000",
                "5 rue de l'avenue",
                "philippe.duval@externe.junia.com",
                Date.valueOf("2022-03-09"),
                "Man",
                "Work");

        ContactsDAOs.addContactToDb(contact);
        // THEN
        Connection connection = DataSourceFactory.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM person WHERE lastname='DUVAL'");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getInt("idperson")).isEqualTo(contact.getIdperson());
        assertThat(resultSet.getString("lastname")).isEqualTo("DUVAL");
        assertThat(resultSet.getString("firstname")).isEqualTo("Philippe");
        assertThat(resultSet.getString("nickname")).isEqualTo("P.DUVAL");
        assertThat(resultSet.getString("phone_number")).isEqualTo("0700000000");
        assertThat(resultSet.getString("address")).isEqualTo("5 rue de l'avenue");
        assertThat(resultSet.getString("email_address")).isEqualTo("philippe.duval@externe.junia.com");
        assertThat(resultSet.getDate("birth_date")).isEqualTo(Date.valueOf("2022-03-09"));
        assertThat(resultSet.getString("gender")).isEqualTo("Man");
        assertThat(resultSet.getString("filter")).isEqualTo("Work");
        assertThat(resultSet.next()).isFalse();
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void shouldGetContactsFromDb() throws Exception {
        // WHEN
        List<Contact> contacts = ContactsDAOs.getContactsFromDb();
        // THEN
        assertThat(contacts).hasSize(3);
        assertThat(contacts).extracting("idperson","lastname","firstname","nickname","phone_number","address","email_address","birth_date","gender","filter")
                .containsOnly(tuple(0,"Demenez","Hugo","hdemenez","069910196727","rue jean jaures","hugo.demenez@student.junia.com",Date.valueOf("2000-03-18"),"Man","Favorites"),
                        tuple(1,"Duhamel","Alban","aduhamel","06111111111","avenue du soleil","alban.duhamel@student.junia.com",Date.valueOf("2000-09-14"),"Man","Family"),
                        tuple(2,"Dumesge","Quentin","qdumesge","03222222228","rue de la lune","quentin.dumesge@student.junia.com",Date.valueOf("2000-10-14"),"Man","Friends"));
    }




    @Test
    public void shouldRemoveContactFromDb() throws Exception {
        // WHEN
        Contact contact = new Contact(
                "FOR",
                "Remove",
                "delete",
                "6666666666",
                "rue du neant",
                "erase@isen.bin",
                Date.valueOf("2000-10-14"),
                "Woman",
                "Unknown");
        ContactsDAOs.addContactToDb(contact);
        ContactsDAOs.removeContactFromDb(contact);
        // THEN
        Connection connection = DataSourceFactory.getDataSource().getConnection();
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM person WHERE lastname='DUVAL' AND firstname='Philippe'");
        assertThat(resultSet.next()).isEqualTo(false);
    }


}
