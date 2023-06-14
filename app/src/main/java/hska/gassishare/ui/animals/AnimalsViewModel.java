package hska.gassishare.ui.animals;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import hska.gassishare.data.entity.Dog;
import hska.gassishare.data.repositories.DogRepository;
import hska.gassishare.ui.login.LoginViewModel;

public class AnimalsViewModel extends AndroidViewModel {
    private DogRepository mRepository;

    private final LiveData<List<Dog>> mAllDogs;

    /**
     * Konstruktor für das AnimalsViewModel.
     *
     * @param application Die Anwendungsklasse.
     */
    public AnimalsViewModel(Application application) {
        super(application);
        mRepository = new DogRepository(application);
        mAllDogs = mRepository.getAllDogs();
    }
}
