package hska.gassishare.ui.person;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import hska.gassishare.MainActivity;
import hska.gassishare.R;
import hska.gassishare.data.entity.User;
import hska.gassishare.databinding.FragmentPersonBinding;
import hska.gassishare.ui.profile.ProfileFragment;

public class PersonFragment extends Fragment {

    private FragmentPersonBinding binding;

    private EditText editVorname;           // Eingabefeld für den Vornamen
    private EditText editNachname;          // Eingabefeld für den Nachnamen
    private EditText editEmail;             // Eingabefeld für die E-Mail
    private EditText editBeschreibung;      // Eingabefeld für die Beschreibung
    private EditText editStrasse;           // Eingabefeld für die Straße
    private EditText editPLZ;               // Eingabefeld für die PLZ
    private EditText editOrt;               // Eingabefeld für den Ort
    private EditText editOldPassword;       // Eingabefeld für das alte Passwort
    private EditText editNewPassword;       // Eingabefeld für das neue Passwort
    private Button aenderungenSpeichernButton;  // Button zum Speichern der Änderungen
    private MainActivity mainActivity;      // Referenz auf die MainActivity
    private PersonViewModel personViewModel; // ViewModel für die Person

    /**
     * Erstellt und gibt die Benutzeroberfläche des Fragments zurück.
     *
     * @param inflater           Der LayoutInflater, der verwendet wird, um die View zu erstellen.
     * @param container          Die ViewGroup, zu der die View hinzugefügt wird.
     * @param savedInstanceState Ein Bundle mit dem zuletzt gespeicherten Zustand des Fragments.
     * @return Die erstellte View des Fragments.
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        personViewModel = new ViewModelProvider(this).get(PersonViewModel.class);

        binding = FragmentPersonBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Setze das Bild
        ImageView imageView = root.findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.avatar);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Weiterleitung zu einem anderen Fragment
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main, ProfileFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("PersonToProfileFragmentTransaction")
                        .commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        return root;
    }

    /**
     * Wird aufgerufen, nachdem die View des Fragments erstellt wurde.
     *
     * @param view               Die erstellte View des Fragments.
     * @param savedInstanceState Ein Bundle mit dem zuletzt gespeicherten Zustand des Fragments.
     */
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.post(new Runnable() {
            @Override
            public void run() {
                BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view);

                while (navBar == null) {
                    navBar = getActivity().findViewById(R.id.nav_view);
                }
                navBar.setVisibility(View.GONE);
            }
        });

        mainActivity = ((MainActivity) getActivity());
        User aktuellerUser = mainActivity.getAktuellerUser();

        editVorname = getView().findViewById(R.id.editVorname);
        editNachname = getView().findViewById(R.id.editNachname);
        editEmail = getView().findViewById(R.id.editEmail);
        editBeschreibung = getView().findViewById(R.id.editHundeBeschreibung);
        editStrasse = getView().findViewById(R.id.editStrasse);
        editPLZ = getView().findViewById(R.id.editPLZ);
        editOrt = getView().findViewById(R.id.editOrt);
        editOldPassword = getView().findViewById(R.id.editPassOld);
        editNewPassword = getView().findViewById(R.id.editPassNew);

        aenderungenSpeichernButton = getView().findViewById(R.id.buttonSpeichern);

        editVorname.setText(aktuellerUser.getVorname());
        editNachname.setText(aktuellerUser.getNachname());
        editEmail.setText(aktuellerUser.getEmail());
        editBeschreibung.setText(aktuellerUser.getBeschreibung());
        editStrasse.setText(aktuellerUser.getStrasse());
        editPLZ.setText(aktuellerUser.getPlz().toString());
        editOrt.setText(aktuellerUser.getOrt());

        aenderungenSpeichernButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Änderungen übernehmen
                String savedPW = aktuellerUser.getPasswort();

                if (String.valueOf(editNewPassword.getText()) == "" ||
                        String.valueOf(editOldPassword) != aktuellerUser.getPasswort()) {
                    // Kein neues Passwort eingegeben oder falsches altes Passwort
                } else {
                    savedPW = String.valueOf(editNewPassword.getText());
                }

                User geaenderterUser = new User(
                        aktuellerUser.getId(),
                        aktuellerUser.getUsername(),
                        String.valueOf(editNachname.getText()),
                        String.valueOf(editVorname.getText()),
                        savedPW,
                        String.valueOf(editEmail.getText()),
                        String.valueOf(editBeschreibung.getText()),
                        Integer.parseInt(editPLZ.getText().toString()),
                        String.valueOf(editStrasse.getText()),
                        String.valueOf(editOrt.getText())
                );

                // Aktualisiere den Benutzer in der Activity
                mainActivity.setAktuellerUser(geaenderterUser);

                // Aktualisiere den Benutzer in der Datenbank
                personViewModel.UserUpdaten(geaenderterUser);

                // Mache die Navigation wieder sichtbar
                BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view);
                navBar.setVisibility(View.VISIBLE);

                // Weiterleitung zu einem anderen Fragment
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main, ProfileFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("PersonToProfileFragmentTransaction")
                        .commit();
            }
        });
    }
}
