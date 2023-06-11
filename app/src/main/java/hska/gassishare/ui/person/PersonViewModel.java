package hska.gassishare.ui.person;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import hska.gassishare.data.entity.Dog;
import hska.gassishare.data.entity.User;
import hska.gassishare.data.repositories.UserRepository;
import hska.gassishare.ui.login.LoginViewModel;

public class PersonViewModel extends AndroidViewModel {

    private final MutableLiveData<String> mText;




    private LoginViewModel loginViewModel;

    private UserRepository mUserRepository;

    public PersonViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("This is person fragment");

        mUserRepository = new UserRepository(application);
    }

    public LiveData<String> getText() {
        return mText;
    }


    public void UserUpdaten(User u) {
        mUserRepository.update(u);
        System.out.println("User erfolgreich aktualisiert");
    }
}