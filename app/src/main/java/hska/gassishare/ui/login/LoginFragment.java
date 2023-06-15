package hska.gassishare.ui.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.UUID;

import hska.gassishare.MainActivity;
import hska.gassishare.R;
import hska.gassishare.data.entity.User;
import hska.gassishare.databinding.FragmentLoginBinding;

/**
 * Fragment für den Login-Bereich.
 */
public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;     // Binding für das Fragment
    private LoginViewModel loginViewModel;    // ViewModel für den Login
    private EditText usernameInput;           // Eingabefeld für den Benutzernamen
    private EditText passwordInput;           // Eingabefeld für das Passwort
    private Button anmeldenButton;            // Button für die Anmeldung
    private Button registrierenButton;        // Button für die Registrierung
    private MainActivity mainActivity;       // Referenz auf die MainActivity


    /**
     * Konstruktor für das LoginFragment.
     */
    public LoginFragment() {
        super(R.layout.fragment_login);
    }

    /**
     * Wird aufgerufen, um die Benutzeroberfläche des Fragments zu erstellen und zurückzugeben.
     *
     * @param inflater           Der LayoutInflater, der verwendet werden kann, um XML in View-Objekte umzuwandeln.
     * @param container          Wenn nicht null, ist dies der übergeordnete View, an den das Fragment angehängt wird.
     * @param savedInstanceState Wenn nicht null, enthält das Fragment den zuvor gespeicherten Zustand.
     * @return Der View des Fragments.
     */
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    /**
     * Wird aufgerufen, sobald die View des Fragments erstellt wurde.
     *
     * @param view               Der erstellte View des Fragments.
     * @param savedInstanceState Wenn nicht null, enthält das Fragment den zuvor gespeicherten Zustand.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainActivity = ((MainActivity) getActivity());
        usernameInput = getView().findViewById(R.id.UsernameInput);
        passwordInput = getView().findViewById(R.id.PasswordInput);
        anmeldenButton = getView().findViewById(R.id.AnmeldenButton);
        registrierenButton = getView().findViewById(R.id.RegistrierenButton);

        final Observer<List<User>> usersObserver = new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable final List<User> userListe) {

                if (userListe.size() == 0)
                    return;

                String alleUser = "";
                User user1 = userListe.get(0);

                usernameInput.setText(user1.getUsername());
                passwordInput.setText(user1.getPasswort());

                for (User u : userListe) {
                    alleUser = alleUser + u.toString();
                }
            }
        };

        loginViewModel.getAlleUser().observe(getViewLifecycleOwner(), usersObserver);

        anmeldenButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Wird aufgerufen, wenn der Anmelde-Button geklickt wird.
             *
             * @param v Die geklickte View.
             */
            public void onClick(View v) {
                String username = String.valueOf(usernameInput.getText());
                String passwort = String.valueOf(passwordInput.getText());

                if (!loginViewModel.userExists(username, passwort)) {
                    // Dialogfenster als Hinweis, dass der Benutzer nicht existiert
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Benutzer nicht gefunden");
                    builder.setMessage("Bitte überprüfen Sie, ob Sie den Benutzernamen und das Passwort korrekt eingegeben haben. Wenn Sie neu sind, registrieren Sie sich bitte.");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.setCancelable(false);
                    dialog.show();
                    return;
                }

                mainActivity.setAktuellerUser(loginViewModel.getUser(username));
                UUID userId = mainActivity.getAktuellerUser().getId();
                mainActivity.setAktuelleDoggosListe(loginViewModel.getDogsForUser(userId));

                BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view);
                navBar.setVisibility(View.VISIBLE);

                Navigation.findNavController(getView()).navigate(R.id.navigation_profile);
            }
        });

        registrierenButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Wird aufgerufen, wenn der Registrieren-Button geklickt wird.
             *
             * @param v Die geklickte View.
             */
            public void onClick(View v) {
                if (usernameInput.getText().toString().equals("") || passwordInput.getText().toString().equals("")) {
                    // Dialogfenster als Hinweis, dass Benutzername oder Passwort vergessen wurden
                    mainActivity.dialogNotification("Registrierung fehlgeschlagen", "Bitte überprüfen Sie, ob Sie Benutzername und Passwort eingegeben haben.");
                } else {
                    User u = new User(
                            UUID.randomUUID(),
                            usernameInput.getText().toString(),
                            "",
                            "",
                            passwordInput.getText().toString(),
                            "",
                            "",
                            0,
                            "",
                            ""
                    );

                    mainActivity.dialogNotification("Profil erstellt", "Bitte füllen Sie Ihre Daten unter 'Profil > Meine Angaben' weiter aus.");

                    BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view);
                    navBar.setVisibility(View.VISIBLE);

                    loginViewModel.createUser(u);
                    mainActivity.setAktuellerUser(u);

                    Navigation.findNavController(getView()).navigate(R.id.navigation_profile);
                }
            }
        });

        view.post(new Runnable() {
            /**
             * Wird ausgeführt, nachdem die View des Fragments erstellt wurde.
             */
            @Override
            public void run() {
                BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view);
                while (navBar == null) {
                    navBar = getActivity().findViewById(R.id.nav_view);
                }
                navBar.setVisibility(View.GONE);
            }
        });
    }
}
