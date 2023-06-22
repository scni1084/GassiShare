package hska.gassishare.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
import java.util.UUID;

import hska.gassishare.data.entity.Dog;

/**
 * Interface für den Zugriff auf die Hunde-Datenbanktabelle.
 */
@Dao
public interface DogDao {

    /**
     * Gibt eine LiveData-Liste aller alphabetisch sortierten Hunde aus der Dog-Tabelle zurück.
     *
     * @return LiveData-Liste der alphabetisch sortierten Hunde.
     */
    @Query("SELECT * FROM dog_table ORDER BY id ASC")
    LiveData<List<Dog>> getAlphabetizedDogs();

    /**
     * Fügt einen neuen Hund in die Datenbank ein.
     *
     * @param dog Der einzufügende Hund.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Dog dog);

    /**
     * Löscht alle Hunde aus der Dog-Tabelle.
     */
    @Query("DELETE FROM dog_table")
    void deleteAll();

    /**
     * Gibt eine Liste aller Hunde für einen bestimmten Benutzer anhand der Benutzer-ID zurück.
     *
     * @param user_id Die ID des Benutzers.
     * @return Liste der Hunde für den Benutzer.
     */
    @Query("SELECT * FROM dog_table WHERE user_id = :user_id")
    List<Dog> getDogsForUser(UUID user_id);

    /**
     * Aktualisiert die Informationen eines Hunds in der Dog-Tabelle.
     *
     * @param id           Die ID des Hunds.
     * @param user_id      Die ID des Benutzers.
     * @param name         Der Name des Hunds.
     * @param alter        Das Alter des Hunds.
     * @param geschlecht   Das Geschlecht des Hunds.
     * @param rasse        Die Rasse des Hunds.
     * @param groesse      Die Größe des Hunds.
     * @param kastriert    Gibt an, ob der Hund kastriert ist oder nicht.
     * @param beschreibung Die Beschreibung des Hunds.
     */
    @Query("UPDATE dog_table SET name= :name, `alter`= :alter, geschlecht= :geschlecht, rasse= :rasse, groesse= :groesse, kastriert= :kastriert, beschreibung= :beschreibung WHERE id =:id AND user_id= :user_id")
    void updateDog(UUID id, UUID user_id, String name, Integer alter, String geschlecht, String rasse, Integer groesse, Boolean kastriert, String beschreibung);
}
