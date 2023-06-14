package hska.gassishare.ui.dog;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import hska.gassishare.data.entity.Dog;
import hska.gassishare.data.repositories.DogRepository;

/**
 * ViewModel für das DogFragment.
 */
public class DogViewModel extends AndroidViewModel {

    private DogRepository mDogRepository;

    /**
     * Konstruiert ein neues DogViewModel.
     *
     * @param application Der Anwendungskontext.
     */
    public DogViewModel(Application application) {
        super(application);
        mDogRepository = new DogRepository(application);
    }

    /**
     * Fügt einen Hund in die Datenbank ein.
     *
     * @param dog Der einzufügende Hund.
     */
    public void doggoAnlegen(Dog dog) {
        mDogRepository.insert(dog);
    }

    /**
     * Aktualisiert einen Hund in der Datenbank.
     *
     * @param dog Der zu aktualisierende Hund.
     */
    public void doggoAendern(Dog dog) {
        mDogRepository.updateDog(dog);
    }
}
