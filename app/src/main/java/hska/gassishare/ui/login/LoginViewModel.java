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

    private UserRepository mUserRepository;

    private LiveData<List<User>> alleUser;

    public LiveData<List<User>> getAlleUser() {
        /* checke nicht warum das nicht geht
        if (alleUser == null) {
            alleUser = new LiveData<List<User>>();
        }*/

        return alleUser;
    }

    private final MutableLiveData<String> mText;

    //private final LiveData<List<User>> mAllUsers;

    public LoginViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("This is login fragment");

        // weiß nicht ob application hier sinn macht
        mUserRepository = new UserRepository(application);

        alleUser = mUserRepository.getAllUsers();


        //mAllUsers = mRepository.getAllUsers();
        //hier muss die Action passieren, dass der User gesucht wird
    }

   // LiveData<List<User>> getAllUsers() {
    //    return mAllUsers;
    //}

    public LiveData<String> getText() {
        return mText;
    }
}