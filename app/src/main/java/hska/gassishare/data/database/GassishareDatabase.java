package hska.gassishare.data.database;


import static java.lang.System.in;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import hska.gassishare.data.dao.DogDao;
import hska.gassishare.data.dao.UserDao;
import hska.gassishare.data.entity.Dog;
import hska.gassishare.data.entity.User;

@Database(entities = {User.class, Dog.class}, version = 1, exportSchema = false)
public abstract class GassishareDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract DogDao dogDao();


    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile GassishareDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 1;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static GassishareDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (GassishareDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    GassishareDatabase.class, "app_database")
                            .addCallback(sGassishareDatabaseCallback)
                            .build();


                    INSTANCE.userDao().getAlphabetizedUsers();


/*
                    DogDao dogDao = INSTANCE.dogDao();
                    UserDao userDao = INSTANCE.userDao();
                    dogDao.deleteAll();
                    userDao.deleteAll();

                    MockData mockData = new MockData();

                    for (User usr : mockData.userList) {
                        userDao.insert(usr);
                    }

                    for (Dog doggo : mockData.doggoList) {
                        dogDao.insert(doggo);
                    } */
                }
            }
        }
        return INSTANCE;
    }


    /**
     * Override the onCreate method to populate the database.
     * For this sample, we clear the database every time it is created.
     */
    private static RoomDatabase.Callback sGassishareDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                DogDao dogDao = INSTANCE.dogDao();
                UserDao userDao = INSTANCE.userDao();
                dogDao.deleteAll();
                userDao.deleteAll();

                MockData mockData = new MockData();

                for (User usr : mockData.userList) {
                    userDao.insert(usr);
                }

                for (Dog doggo : mockData.doggoList) {
                    dogDao.insert(doggo);
                }
            });
        }
    };
}
