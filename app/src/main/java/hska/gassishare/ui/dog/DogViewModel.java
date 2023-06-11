package hska.gassishare.ui.dog;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DogViewModel extends AndroidViewModel {

    private final MutableLiveData<String> mText;

    public DogViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("This is dog fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}