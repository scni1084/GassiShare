package hska.gassishare.data.dao;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import hska.gassishare.data.entity.User;

@Dao
public interface UserDao {
    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @Query("SELECT * FROM user_table ORDER BY username ASC")
    LiveData<List<User>> getAlphabetizedUsers();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);

    @Query("DELETE FROM user_table")
    void deleteAll();

    /**
     * Updating only title and description
     * By order id
     */
    @Query("UPDATE user_table SET username = :username, nachname= :nachname, vorname= :vorname, passwort= :passwort,  email= :email,beschreibung= :beschreibung,plz= :plz,strasse= :strasse, ort= :ort WHERE id =:id")
    void update(int id, String username, String nachname, String vorname, String passwort, String email, String beschreibung, Integer plz, String strasse, String ort);

    @Query("SELECT * FROM user_table WHERE username = :username")
    User getUser(String username);


    @Query("SELECT EXISTS (SELECT * FROM user_table WHERE username = :username AND passwort = :password)")
    boolean userExisting(String username, String password);



}
