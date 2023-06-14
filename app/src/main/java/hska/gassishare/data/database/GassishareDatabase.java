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
public abstract class GassishareDatabase extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 1;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    /**
     * Überschreibt die onCreate-Methode, um die Datenbank zu initialisieren.
     * In diesem Beispiel leeren wir die Datenbank jedes Mal, wenn sie erstellt wird.
     */
    private static RoomDatabase.Callback sGassishareDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                // Die Datenbank im Hintergrund befüllen.
                // Wenn Sie mit mehreren Daten starten möchten, fügen Sie sie einfach hinzu.
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
    // Markierung der Instanz als "volatile", um den atomaren Zugriff auf die Variable sicherzustellen
    private static volatile GassishareDatabase INSTANCE;

    public static GassishareDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (GassishareDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    GassishareDatabase.class, "app_database")
                            .addCallback(sGassishareDatabaseCallback)
                            .allowMainThreadQueries()
                            .build();

                    INSTANCE.userDao().getAlphabetizedUsers();
                }
            }
        }
        return INSTANCE;
    }

    public abstract UserDao userDao();

    public abstract DogDao dogDao();
}
