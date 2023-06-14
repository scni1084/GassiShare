package hska.gassishare.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.UUID;

import hska.gassishare.data.dao.DogDao;
import hska.gassishare.data.database.GassishareDatabase;
import hska.gassishare.data.entity.Dog;

public class DogRepository {

    private DogDao mDogDAO;
    private LiveData<List<Dog>> mAllDogs;

    public DogRepository(Application application) {
        GassishareDatabase db = GassishareDatabase.getDatabase(application);
        mDogDAO = db.dogDao();
        mAllDogs = mDogDAO.getAlphabetizedDogs();
    }

    /**
     * Ruft alle Hunde als LiveData-Objekt ab.
     *
     * @return Eine LiveData-Liste aller Hunde.
     */
    public LiveData<List<Dog>> getAllDogs() {
        return mAllDogs;
    }

    /**
     * Fügt einen neuen Hund in die Datenbank ein.
     *
     * @param dog Der einzufügende Hund.
     */
    public void insert(Dog dog) {
        GassishareDatabase.databaseWriteExecutor.execute(() -> {
            mDogDAO.insert(dog);
        });
    }

    /**
     * Ruft eine Liste von Hunden für einen bestimmten Benutzer ab.
     *
     * @param id Die ID des Benutzers.
     * @return Eine Liste von Hunden für den Benutzer.
     */
    public List<Dog> getDogsForUser(UUID id) {
        return mDogDAO.getDogsForUser(id);
    }

    /**
     * Aktualisiert die Informationen eines Hunds in der Datenbank.
     *
     * @param dog Der zu aktualisierende Hund.
     */
    public void updateDog(Dog dog) {
        GassishareDatabase.databaseWriteExecutor.execute(() -> {
            mDogDAO.updateDog(
                    dog.getId(),
                    dog.getUser_id(),
                    dog.getName(),
                    dog.getAlter(),
                    dog.getGeschlecht(),
                    dog.getRasse(),
                    dog.getGroesse(),
                    dog.getKastriert(),
                    dog.getBeschreibung()
            );
        });
    }
}
