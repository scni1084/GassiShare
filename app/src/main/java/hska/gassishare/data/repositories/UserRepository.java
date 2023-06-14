package hska.gassishare.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import hska.gassishare.data.dao.UserDao;
import hska.gassishare.data.database.GassishareDatabase;
import hska.gassishare.data.entity.User;


/**
 * Repository für den Zugriff auf die User-Datenbanktabelle unter Benutzung der DAO.
 */
public class UserRepository {

    private UserDao mUserDao;
    private LiveData<List<User>> mAllUsers;

    public UserRepository(Application application) {
        GassishareDatabase db = GassishareDatabase.getDatabase(application);
        mUserDao = db.userDao();
        mAllUsers = mUserDao.getAlphabetizedUsers();
    }

    /**
     * Ruft alle Benutzer als LiveData-Objekt ab.
     *
     * @return Eine LiveData-Liste aller Benutzer.
     */
    public LiveData<List<User>> getAllUsers() {
        return mAllUsers;
    }

    /**
     * Überprüft, ob ein Benutzer mit dem angegebenen Benutzernamen und Passwort existiert.
     *
     * @param username Der Benutzername.
     * @param password Das Passwort.
     * @return true, wenn der Benutzer existiert, ansonsten false.
     */
    public boolean userExists(String username, String password) {
        return mUserDao.userExisting(username, password);
    }

    /**
     * Fügt einen neuen Benutzer in die Datenbank ein.
     *
     * @param user Der einzufügende Benutzer.
     */
    public void insert(User user) {
        GassishareDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.insert(user);
        });
    }

    /**
     * Aktualisiert die Informationen eines Benutzers in der Datenbank.
     *
     * @param user Der zu aktualisierende Benutzer.
     */
    public void update(User user) {
        GassishareDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.update(
                    user.getId(),
                    user.getUsername(),
                    user.getNachname(),
                    user.getVorname(),
                    user.getPasswort(),
                    user.getEmail(),
                    user.getBeschreibung(),
                    user.getPlz(),
                    user.getStrasse(),
                    user.getOrt()
            );
        });
    }

    /**
     * Ruft einen Benutzer mit dem angegebenen Benutzernamen ab.
     *
     * @param username Der Benutzername.
     * @return Der Benutzer mit dem angegebenen Benutzernamen oder null, wenn kein Benutzer gefunden wurde.
     */
    public User getUser(String username) {
        return mUserDao.getUser(username);
    }
}
