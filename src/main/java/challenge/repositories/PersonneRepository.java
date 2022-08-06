package challenge.repositories;

import challenge.domain.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonneRepository {

    @Autowired
    private DataSource dataSource;

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<Personne> findAllPersonnesOrderByName() {
        List<Personne> personnes = new ArrayList<>();
        ResultSet fetchedArticles;

        Connection connexion = null;
        try {
            connexion = dataSource.getConnection();
            Statement statement = connexion.createStatement();
            fetchedArticles = statement.executeQuery("SELECT * FROM Personnes ORDER BY Nom");
            while(fetchedArticles.next()) {
                Personne personne = new Personne();
                personne.setId(fetchedArticles.getInt("id"));
                personne.setNom(fetchedArticles.getString("nom"));
                personne.setPrenom(fetchedArticles.getString("prenom"));
                personne.setDateNaissance(LocalDate.parse(fetchedArticles.getString("dateNaissance"), dateFormatter));
                personnes.add(personne);
            }
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connexion != null)
                    connexion.close();
            } catch(SQLException sqle) {
                sqle.printStackTrace();
            }
        }

        return personnes;
    }

    public Personne savePersonne(Personne personne) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO Personnes (id, nom, prenom, dateNaissance) VALUES (?, ?, ?, ?)");

            statement.setInt(1, personne.getId());
            statement.setString(2, personne.getNom());
            statement.setString(3, personne.getPrenom());
            statement.setString(4, personne.getDateNaissance().format(dateFormatter));
            statement.execute();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch(SQLException sqle) {
                sqle.printStackTrace();
            }
        }

        return personne;
    }
}
