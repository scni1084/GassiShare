package hska.gassishare.ui.person;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hska.gassishare.data.entity.User;
import hska.gassishare.data.repositories.UserRepository;

public class PersonViewModel extends ViewModel {

    /* Button btnProfile1;


    public ProfileViewModel() {


    }*/

    private UserRepository mUserRepository;
    private LiveData<User> mCurrentUserData;

    private MutableLiveData<User> currentUserLiveData;


    public PersonViewModel(UserRepository userRepository, int userId) {
        mUserRepository = userRepository;
        mCurrentUserData = mUserRepository.getCurrentUser(userId);
    }

    public LiveData<User> getCurrentUserData() {
        return mCurrentUserData;
    }

    public PersonViewModel() {
        currentUserLiveData = new MutableLiveData<>();
    }
    public String getUsername() {
        if (mCurrentUserData.getValue() != null) {
            return mCurrentUserData.getValue().getUsername();
        }
        return "";
    }

    public String getVorname() {
        if (mCurrentUserData.getValue() != null) {
            return mCurrentUserData.getValue().getVorname();
        }
        return "";
    }

    public String getNachname() {
        if (mCurrentUserData.getValue() != null) {
            return mCurrentUserData.getValue().getNachname();
        }
        return "";
    }

    public LiveData<User> getCurrentUserLiveData() {
        return currentUserLiveData;
    }

    public void setCurrentUser(User user) {
        currentUserLiveData.setValue(user);
    }

}