package hska.gassishare.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
import java.util.UUID;

import hska.gassishare.data.entity.User;

/**
 * Schnittstelle für den Zugriff auf die User-Datenbanktabelle.
 */
@Dao
public interface UserDao {

    /**
     * Gibt eine LiveData-Liste aller alphabetisch sortierten Benutzer aus der User-Tabelle zurück.
     *
     * @return LiveData-Liste der alphabetisch sortierten Benutzer.
     */
    @Query("SELECT * FROM user_table ORDER BY username ASC")
    LiveData<List<User>> getAlphabetizedUsers();

    /**
     * Fügt einen neuen Benutzer in die Datenbank ein.
     *
     * @param user Der einzufügende Benutzer.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);

    /**
     * Löscht alle Benutzer aus der User-Tabelle.
     */
    @Query("DELETE FROM user_table")
    void deleteAll();

    /**
     * Aktualisiert die Informationen eines Benutzers in der User-Tabelle.
     *
     * @param id           Die ID des Benutzers.
     * @param username     Der Benutzername.
     * @param nachname     Der Nachname des Benutzers.
     * @param vorname      Der Vorname des Benutzers.
     * @param passwort     Das Passwort des Benutzers.
     * @param email        Die E-Mail-Adresse des Benutzers.
     * @param beschreibung Die Beschreibung des Benutzers.
     * @param plz          Die Postleitzahl des Benutzers.
     * @param strasse      Die Straße des Benutzers.
     * @param ort          Der Wohnort des Benutzers.
     */
    @Query("UPDATE user_table SET username = :username, nachname= :nachname, vorname= :vorname, passwort= :passwort, email= :email, beschreibung= :beschreibung, plz= :plz, strasse= :strasse, ort= :ort WHERE id = :id")
    void update(UUID id, String username, String nachname, String vorname, String passwort, String email, String beschreibung, Integer plz, String strasse, String ort);

    /**
     * Gibt einen Benutzer anhand des Benutzernamens zurück.
     *
     * @param username Der Benutzername.
     * @return Der gefundene Benutzer oder null, wenn kein Benutzer mit dem angegebenen Benutzernamen gefunden wurde.
     */
    @Query("SELECT * FROM user_table WHERE username = :username")
    User getUser(String username);

    /**
     * Überprüft, ob ein Benutzer mit dem angegebenen Benutzernamen und Passwort vorhanden ist.
     *
     * @param username Der Benutzername.
     * @param password Das Passwort.
     * @return true, wenn der Benutzer existiert, andernfalls false.
     */
    @Query("SELECT EXISTS (SELECT * FROM user_table WHERE username = :username AND passwort = :password)")
    boolean userExisting(String username, String password);
}
