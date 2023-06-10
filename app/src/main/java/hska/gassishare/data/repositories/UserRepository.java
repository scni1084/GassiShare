package hska.gassishare.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import hska.gassishare.data.dao.UserDao;
import hska.gassishare.data.database.GassishareDatabase;
import hska.gassishare.data.entity.User;

public class UserRepository {

    private UserDao mUserDao;
    private LiveData<List<User>> mAllUsers;

    private LiveData<User> mUser;

    private LiveData<User> mCurrentUserData;

    // Note that in order to unit test the UserRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public UserRepository(Application application) {
        GassishareDatabase db = GassishareDatabase.getDatabase(application);
        mUserDao = db.userDao();
        mAllUsers = mUserDao.getAlphabetizedUsers();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<User>> getAllUsers() {
        return mAllUsers;
    }

    public boolean userExists(String username, String password) {return mUserDao.userExisting(username,password);}

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(User user) {
        GassishareDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.insert(user);
        });
    }

    void update(User user) {
        GassishareDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.update(user);
        });
    }

    public LiveData<User> getCurrentUser(String username) {
        mCurrentUserData = mUserDao.getCurrentUser(username);
        return mCurrentUserData;
    }


}
