package hska.gassishare.ui.person;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import hska.gassishare.data.entity.User;
import hska.gassishare.data.repositories.UserRepository;

public class PersonViewModel extends AndroidViewModel {

    private UserRepository mUserRepository;

    /**
     * Erstellt ein neues PersonViewModel-Objekt.
     *
     * @param application Die Anwendung, in der das ViewModel verwendet wird.
     */
    public PersonViewModel(Application application) {
        super(application);

        mUserRepository = new UserRepository(application);
    }

    /**
     * Aktualisiert den Benutzer in der Datenbank.
     *
     * @param u Der Benutzer, der aktualisiert werden soll.
     */
    public void UserUpdaten(User u) {
        mUserRepository.update(u);
    }
}
