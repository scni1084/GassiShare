package hska.gassishare.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import hska.gassishare.data.dao.DogDao;
import hska.gassishare.data.database.GassishareDatabase;
import hska.gassishare.data.entity.Dog;

public class DogRepository {

    private DogDao mDogDAO;
    private LiveData<List<Dog>> mAllDogs;

    // Note that in order to unit test the DogRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public DogRepository(Application application) {
        GassishareDatabase db = GassishareDatabase.getDatabase(application);
        mDogDAO = db.dogDao();
        mAllDogs = mDogDAO.getAlphabetizedDogs();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Dog>> getAllDogs() {
        return mAllDogs;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Dog dog) {
        GassishareDatabase.databaseWriteExecutor.execute(() -> {
            mDogDAO.insert(dog);
        });
    }

    public List<Dog> getDogsForUser(int id) {
        return mDogDAO.getDogsForUser(id);
    }
}