package hska.gassishare.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Das ist der Heimat-Bildschirm! Es ist sehr kuschelig hier!");
    }

    public LiveData<String> getText() {
        return mText;
    }
}