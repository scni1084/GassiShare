package hska.gassishare.data.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import hska.gassishare.data.entity.Dog;
import hska.gassishare.data.repositories.DogRepository;

public class DogViewModel extends AndroidViewModel {
    private DogRepository mRepository;

    private final LiveData<List<Dog>> mAllDogs;

    public DogViewModel (Application application) {
        super(application);
        mRepository = new DogRepository(application);
        mAllDogs = mRepository.getAllDogs();
    }

    LiveData<List<Dog>> getAllWords() { return mAllDogs; }

    public void insert(Dog dog) { mRepository.insert(dog); }

}
