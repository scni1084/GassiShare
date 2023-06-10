package hska.gassishare.ui.person;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hska.gassishare.ui.login.LoginViewModel;

public class PersonViewModel extends ViewModel {

    private final MutableLiveData<String> mText;


    private LoginViewModel loginViewModel;



    public PersonViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is person fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}