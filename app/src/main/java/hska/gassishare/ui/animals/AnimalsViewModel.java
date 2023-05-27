package hska.gassishare.ui.animals;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AnimalsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AnimalsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is animals fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}