package hska.gassishare.ui.dog;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import hska.gassishare.data.entity.Dog;
import hska.gassishare.data.repositories.DogRepository;

public class DogViewModel extends AndroidViewModel {

    private final MutableLiveData<String> mText;


    private DogRepository mDogRepository;

    public DogViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("This is dog fragment");


        mDogRepository = new DogRepository(application);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void doggoAnlegen(Dog dog) {
        mDogRepository.insert(dog);
    }

    public void doggoAendern(Dog dog) {mDogRepository.updateDog(dog);}
}