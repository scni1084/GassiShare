package hska.gassishare.data.dao;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
import java.util.UUID;

import hska.gassishare.data.entity.Dog;

@Dao
public interface DogDao {
    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @Query("SELECT * FROM dog_table ORDER BY id ASC")
    LiveData<List<Dog>> getAlphabetizedDogs();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Dog dog);

    @Query("DELETE FROM dog_table")
    void deleteAll();

    @Query("SELECT * FROM dog_table WHERE user_id = :user_id")
    List<Dog> getDogsForUser(UUID user_id);

    @Query("UPDATE dog_table SET name= :name, `alter`= :alter, geschlecht= :geschlecht,  rasse= :rasse, groesse= :groesse, kastriert= :kastriert, beschreibung= :beschreibung WHERE id =:id AND user_id= :user_id")
    void updateDog(UUID id, UUID user_id, String name, Integer alter, String geschlecht, String rasse, Integer groesse, Boolean kastriert, String beschreibung);
}
