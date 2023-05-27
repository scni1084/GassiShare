package hska.gassishare.ui.dog;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DogViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DogViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dog fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}