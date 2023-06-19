package hska.gassishare.ui.login;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.UUID;

import hska.gassishare.data.entity.Dog;
import hska.gassishare.data.entity.User;
import hska.gassishare.data.repositories.DogRepository;
import hska.gassishare.data.repositories.UserRepository;

/**
 * ViewModel für den Login-Bereich.
 */
public class LoginViewModel extends AndroidViewModel {

    private final MutableLiveData<String> mText;
    private UserRepository mUserRepository;
    private DogRepository mDogRepository;
    private LiveData<List<User>> alleUser;
    private LiveData<User> aktuellerUser;

    /**
     * Konstruktor für das LoginViewModel.
     *
     * @param application Die Anwendungsklasse.
     */
    public LoginViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mUserRepository = new UserRepository(application);
        mDogRepository = new DogRepository(application);
        alleUser = mUserRepository.getAllUsers();
    }

    /**
     * Gibt den Text zurück.
     *
     * @return Der LiveData-Text.
     */
    public LiveData<String> getText() {
        return mText;
    }

    /**
     * Überprüft, ob der Benutzer existiert.
     *
     * @param username Der Benutzername.
     * @param password Das Passwort.
     * @return true, wenn der Benutzer existiert, false sonst.
     */
    public boolean userExists(String username, String password) {
        return mUserRepository.userExists(username, password);
    }

    /**
     * Überprüft, ob der Benutzername existiert.
     *
     * @param username Der Benutzername.
     * @return true, wenn der Benutzer existiert, false sonst.
     */
    public boolean usernameExists(String username) {
        return mUserRepository.usernameExists(username);
    }

    /**
     * Gibt den Benutzer anhand des Benutzernamens zurück.
     *
     * @param username Der Benutzername.
     * @return Der Benutzer.
     */
    public User getUser(String username) {
        return mUserRepository.getUser(username);
    }

    /**
     * Gibt eine Liste von Hunden für einen bestimmten Benutzer zurück.
     *
     * @param id Die ID des Benutzers.
     * @return Eine Liste von Hunden.
     */
    public List<Dog> getDogsForUser(UUID id) {
        return mDogRepository.getDogsForUser(id);
    }

    /**
     * Erstellt einen neuen Benutzer.
     *
     * @param u Der Benutzer.
     */
    public void createUser(User u) {
        mUserRepository.insert(u);
    }

    /**
     * Gibt alle Benutzer zurück.
     *
     * @return LiveData-Liste von Benutzern.
     */
    public LiveData<List<User>> getAlleUser() {
        return alleUser;
    }

    /**
     * Gibt den aktuellen Benutzer zurück.
     *
     * @return LiveData des aktuellen Benutzers.
     */
    public LiveData<User> getAktuellerUser() {
        return aktuellerUser;
    }

    /**
     * Setzt den aktuellen Benutzer.
     *
     * @param aktuellerUser LiveData des aktuellen Benutzers.
     */
    public void setAktuellerUser(LiveData<User> aktuellerUser) {
        this.aktuellerUser = aktuellerUser;
    }
}
