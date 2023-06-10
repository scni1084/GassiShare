package hska.gassishare.ui.login;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import hska.gassishare.data.entity.User;
import hska.gassishare.data.repositories.UserRepository;

public class LoginViewModel extends AndroidViewModel {

    private UserRepository mRepository;

    private final MutableLiveData<String> mText;

    //private final LiveData<List<User>> mAllUsers;

    public LoginViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("This is login fragment");
        mRepository = new UserRepository(application);
        //mAllUsers = mRepository.getAllUsers();
    }

   // LiveData<List<User>> getAllUsers() {
    //    return mAllUsers;
    //}

    public LiveData<String> getText() {
        return mText;
    }
}