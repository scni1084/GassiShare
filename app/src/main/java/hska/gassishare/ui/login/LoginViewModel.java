package hska.gassishare.ui.login;

import android.app.Application;

import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import hska.gassishare.data.entity.Dog;
import hska.gassishare.data.entity.User;
import hska.gassishare.data.repositories.DogRepository;
import hska.gassishare.data.repositories.UserRepository;

public class LoginViewModel extends AndroidViewModel {

    private UserRepository mUserRepository;

    private DogRepository mDogRepository;

    private LiveData<List<User>> alleUser;

    public LiveData<List<User>> getAlleUser() {
        return alleUser;
    }

    private LiveData<User> aktuellerUser;

    public LiveData<User> getAktuellerUser() {
        return aktuellerUser;
    }

    public void setAktuellerUser(LiveData<User> aktuellerUser) {
        this.aktuellerUser = aktuellerUser;
    }

    private final MutableLiveData<String> mText;

    public LoginViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();

        mUserRepository = new UserRepository(application);

        mDogRepository = new DogRepository(application);

        alleUser = mUserRepository.getAllUsers();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public boolean userExists(String username, String password) {return mUserRepository.userExists(username,password);}

    public User getUser(String username) {
        return mUserRepository.getUser(username);
    }

    public List<Dog> getDogsForUser(int id) {
        return mDogRepository.getDogsForUser(id);
    }
}