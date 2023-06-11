package hska.gassishare;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import hska.gassishare.data.entity.Dog;
import hska.gassishare.data.entity.User;
import hska.gassishare.databinding.ActivityMainBinding;
import hska.gassishare.ui.login.LoginViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private User aktuellerUser;

    private List<Dog> aktuelleDoggosListe;

    private Dog aktuellerDog = null;

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_map, R.id.navigation_profile, R.id.navigation_animals)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        if (!navController.navigateUp()) {
            super.onBackPressed();
        }
    }


    public User getAktuellerUser() {
        return aktuellerUser;
    }

    public void setAktuellerUser(User aktuellerUser) {
        this.aktuellerUser = aktuellerUser;
    }

    public List<Dog> getAktuelleDoggosListe() {
        return aktuelleDoggosListe;
    }

    public void setAktuelleDoggosListe(List<Dog> aktuelleDoggosListe) {
        this.aktuelleDoggosListe = aktuelleDoggosListe;
    }

    public Dog getAktuellerDog() {
        return aktuellerDog;
    }

    public void setAktuellerDog(Dog aktuellerDog) {
        this.aktuellerDog = aktuellerDog;
    }
}