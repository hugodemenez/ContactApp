import isen.db.daos.ContactsDAOs;
import isen.db.daos.DataSourceFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

public class ContactsDAOTestCase {

        private ContactsDAOs contactDao = new ContactsDAOs();

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
                            "    birth_date DATE NULL);");
            stmt.close();
            connection.close();
        }

        @Test
        public void shouldListFilms() {
            // WHEN
            List<Film> films = filmDao.listFilms();
            // THEN
            assertThat(films).hasSize(3);
        }

        @Test
        public void shouldListFilmsByGenre() {
            // WHEN
            List<Film> films = filmDao.listFilmsByGenre("Drama");
            // THEN
            assertThat(films).hasSize(1);
            assertThat(films.get(0).getTitle()).isEqualTo("Title 1");
        }

        @Test
        public void shouldAddFilm() throws Exception {
            // WHEN
            Film film = new Film(4,"Test", LocalDate.now(),new Genre(1,"Horreur"),40,"Hugo","Film d'horreur");

            filmDao.addFilm(film);
            // THEN
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM film WHERE title='Test'");
            assertThat(resultSet.next()).isTrue();
            assertThat(resultSet.getInt("idfilm")).isNotNull();
            assertThat(resultSet.getString("Title")).isEqualTo("Test");
            assertThat(resultSet.next()).isFalse();
            resultSet.close();
            statement.close();
            connection.close();
        }


}
