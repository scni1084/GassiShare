package hska.gassishare.data.database;


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
abstract class GassishareDatabase extends RoomDatabase {

    abstract UserDao userDao();

    abstract DogDao dogDao();


    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile GassishareDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static GassishareDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (GassishareDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    GassishareDatabase.class, "app_database")
                            .addCallback(sGassishareDatabaseCallback)
                            .build();
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

                Dog dog = new Dog(1, 100, "Wuffi", 3, "weiblich",
                        "Labrador", true, "Ein lieber Wuffi");
                User user = new User(100, "herrchen10",
                        "Mustermann", "Max",
                        "pw", "max.mustermann@m.de", "Ich bin Max",
                        76131, "Musterstrasse", "Karlsruhe");
                dogDao.insert(dog);
                userDao.insert(user);
            });
        }
    };
}
