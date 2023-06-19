package hska.gassishare;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import hska.gassishare.data.entity.Dog;
import hska.gassishare.data.entity.User;
import hska.gassishare.databinding.ActivityMainBinding;


/**
 * MainActivity der GassiShare-App
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;             // Binding für die MainActivity
    private User aktuellerUser;                      // Aktuell angemeldeter Benutzer
    private List<Dog> aktuelleDoggosListe;            // Aktuelle Liste der Hunde
    private Dog aktuellerDog = null;                  // Aktuell ausgewählter Hund
    private AppBarConfiguration appBarConfiguration;  // Konfiguration der App-Leiste

    private List<User> allUsers;

    /**
     * Wird aufgerufen, wenn die Aktivität erstellt wird.
     *
     * @param savedInstanceState Ein Bundle mit dem zuletzt gespeicherten Zustand der Aktivität.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_map, R.id.navigation_profile, R.id.navigation_animals)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    /**
     * Wird aufgerufen, wenn der Up-Navigationsschalter geklickt wird.
     *
     * @return true, wenn die Aktion abgeschlossen wurde, andernfalls false.
     */
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /**
     * Wird aufgerufen, wenn die Zurück-Taste gedrückt wird.
     */
    @Override
    public void onBackPressed() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        if (!navController.navigateUp()) {
            super.onBackPressed();
        }
    }

    /**
     * Zeigt einen Dialog mit einer Benachrichtigung an.
     *
     * @param title   Der Titel des Dialogs.
     * @param message Die Nachricht des Dialogs.
     */
    public void dialogNotification(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    /**
     * Gibt den aktuellen angemeldeten Benutzer zurück.
     *
     * @return Der aktuelle Benutzer.
     */
    public User getAktuellerUser() {
        return aktuellerUser;
    }

    /**
     * Setzt den aktuellen angemeldeten Benutzer.
     *
     * @param aktuellerUser Der aktuelle Benutzer.
     */
    public void setAktuellerUser(User aktuellerUser) {
        this.aktuellerUser = aktuellerUser;
    }

    /**
     * Gibt die aktuelle Liste der Hunde zurück.
     *
     * @return Die aktuelle Liste der Hunde.
     */
    public List<Dog> getAktuelleDoggosListe() {
        if (aktuelleDoggosListe == null) {
            aktuelleDoggosListe = new ArrayList<Dog>();
        }

        return aktuelleDoggosListe;
    }

    /**
     * Setzt die aktuelle Liste der Hunde.
     *
     * @param aktuelleDoggosListe Die aktuelle Liste der Hunde.
     */
    public void setAktuelleDoggosListe(List<Dog> aktuelleDoggosListe) {
        this.aktuelleDoggosListe = aktuelleDoggosListe;
    }

    /**
     * Gibt den aktuell ausgewählten Hund zurück.
     *
     * @return Der aktuell ausgewählte Hund.
     */
    public Dog getAktuellerDog() {
        return aktuellerDog;
    }

    /**
     * Setzt den aktuell ausgewählten Hund.
     *
     * @param aktuellerDog Der aktuell ausgewählte Hund.
     */
    public void setAktuellerDog(Dog aktuellerDog) {
        this.aktuellerDog = aktuellerDog;
    }

    public List<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }
}