package hska.gassishare.ui.dog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import hska.gassishare.MainActivity;
import hska.gassishare.R;
import hska.gassishare.data.entity.Dog;
import hska.gassishare.databinding.FragmentDogBinding;
import hska.gassishare.ui.animals.AnimalsFragment;

/**
 * Fragment für die Einzelansicht eines Hundes
 */
public class DogFragment extends Fragment {

    private FragmentDogBinding binding;         // Binding für das Fragment
    private Dog aktuellerDoggo;                 // Aktuell ausgewählter Hund
    private MainActivity mainActivity;          // Referenz auf die MainActivity
    private EditText editName;                   // Eingabefeld für den Namen des Hundes
    private EditText editRasse;                  // Eingabefeld für die Rasse des Hundes
    private EditText editGroesse;                // Eingabefeld für die Größe des Hundes
    private EditText editBeschreibung;           // Eingabefeld für die Beschreibung des Hundes
    private EditText editAlter;                  // Eingabefeld für das Alter des Hundes
    private RadioGroup groupGeschlecht;          // RadioGroup für das Geschlecht des Hundes
    private RadioGroup groupKastriert;           // RadioGroup für die Kastration des Hundes
    private Button dogSpeichern;                 // Button zum Speichern der Hundedaten
    private DogViewModel dogViewModel;           // ViewModel für die Hunde
    private List<Dog> doggoList;                 // Liste der Hunde


    /**
     * Erstellt die View des Fragments.
     *
     * @param inflater           Der LayoutInflater, der verwendet wird, um die XML-Layoutdatei in ein View-Objekt umzuwandeln.
     * @param container          Der Eltern-View, in den das Fragment eingefügt wird.
     * @param savedInstanceState Das gespeicherte Zustandsbundle.
     * @return Die erstellte View des Fragments.
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dogViewModel = new ViewModelProvider(this).get(DogViewModel.class);

        binding = FragmentDogBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mainActivity = (MainActivity) getActivity();

        aktuellerDoggo = mainActivity.getAktuellerDog();

        doggoList = mainActivity.getAktuelleDoggosListe();

        // Find the ImageView
        ImageView imageView = root.findViewById(R.id.imageView2);

        // Set the image resource
        imageView.setImageResource(R.drawable.dog1);

        return root;
    }

    /**
     * Wird aufgerufen, nachdem die View des Fragments erstellt wurde.
     *
     * @param view               Die erstellte View des Fragments.
     * @param savedInstanceState Das gespeicherte Zustandsbundle.
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

        editName = getView().findViewById(R.id.editName);
        editRasse = getView().findViewById(R.id.editRasse);
        editGroesse = getView().findViewById(R.id.editGroesse);
        editBeschreibung = getView().findViewById(R.id.editHundeBeschreibung);
        editAlter = getView().findViewById(R.id.editAlter);

        groupGeschlecht = getView().findViewById(R.id.groupGeschlecht);
        groupKastriert = getView().findViewById(R.id.groupKastriert);

        dogSpeichern = getView().findViewById(R.id.dogSpeichern);

        //Werte in TextViews setzen, falls Dog existiert
        if (mainActivity.getAktuellerDog() != null) {

            editName.setText(mainActivity.getAktuellerDog().getName());
            editRasse.setText(mainActivity.getAktuellerDog().getRasse());
            editGroesse.setText(mainActivity.getAktuellerDog().getGroesse().toString());
            editBeschreibung.setText(mainActivity.getAktuellerDog().getBeschreibung());
            editAlter.setText(mainActivity.getAktuellerDog().getAlter().toString());

            // Werte fuer groupGeschlecht setzen
            String geschlecht = mainActivity.getAktuellerDog().getGeschlecht(); // Replace with the actual method to get the value
            RadioButton radioButtonGeschlecht;

            if (geschlecht.equals("männlich")) {
                radioButtonGeschlecht = view.findViewById(R.id.radioGeschlecht1);
                radioButtonGeschlecht.setChecked(true);
            } else if (geschlecht.equals("weiblich")) {
                radioButtonGeschlecht = view.findViewById(R.id.radioGeschlecht2);
                radioButtonGeschlecht.setChecked(true);
            }

            // Werte fuer groupKastriert setzen
            boolean isKastriert = mainActivity.getAktuellerDog().getKastriert();
            RadioButton radioButtonKastriert;

            if (isKastriert == true) {
                radioButtonKastriert = view.findViewById(R.id.radioKastiert1);
                radioButtonKastriert.setChecked(true);
            } else if (isKastriert == false) {
                radioButtonKastriert = view.findViewById(R.id.radioKastiert2);
                radioButtonKastriert.setChecked(true);
            }

        }

        dogSpeichern.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Geschlecht setzen
                int selectedRadioButtonId = groupGeschlecht.getCheckedRadioButtonId();
                String selectedGender = "";

                if (selectedRadioButtonId == R.id.radioGeschlecht1) {
                    selectedGender = "männlich";
                } else if (selectedRadioButtonId == R.id.radioGeschlecht2) {
                    selectedGender = "weiblich";
                }

                // Kastriert setzen
                int selectedRadioButtonIdKastriert = groupGeschlecht.getCheckedRadioButtonId();
                boolean kastriert = false;

                if (selectedRadioButtonIdKastriert == R.id.radioGeschlecht1) {
                    kastriert = true;
                } else if (selectedRadioButtonIdKastriert == R.id.radioGeschlecht2) {
                    kastriert = false;
                }

                int groesse = 0;
                try {
                    groesse = Integer.parseInt(editGroesse.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                if (editName.getText().toString().equals("") ||
                        editRasse.getText().toString().equals("") ||
                        editGroesse.getText().toString().equals("") ||
                        editAlter.getText().toString().equals("") ||
                        selectedGender.equals("") ||
                        groesse == 0) {
                    //Dialogfenster als Hinweis, dass Felder vergessen wurden auszufuellen
                    mainActivity.dialogNotification("Na hör mal",
                            "Bitte kontrolliere, ob du alle Felder korrekt ausgefüllt hast.");
                    return;
                }

                // Dog in DB updaten oder erstellen
                if (mainActivity.getAktuellerDog() == null) {
                    Dog doggo = new Dog(
                            UUID.randomUUID(),
                            mainActivity.getAktuellerUser().getId(),
                            String.valueOf(editName.getText()),
                            Integer.valueOf(String.valueOf(editAlter.getText())),
                            selectedGender,
                            String.valueOf(editRasse.getText()),
                            Integer.valueOf(String.valueOf(editGroesse.getText())),
                            kastriert,
                            String.valueOf(editBeschreibung.getText())
                    );

                    dogViewModel.doggoAnlegen(doggo);

                    doggoList.add(doggo);
                    Collections.sort(doggoList, new Comparator<Dog>() {
                        @Override
                        public int compare(Dog o1, Dog o2) {
                            return o1.getName().compareTo(o2.getName());
                        }
                    });
                    mainActivity.setAktuelleDoggosListe(doggoList);

                } else {
                    Dog doggo = new Dog(
                            mainActivity.getAktuellerDog().getId(),
                            mainActivity.getAktuellerUser().getId(),
                            String.valueOf(editName.getText()),
                            Integer.valueOf(String.valueOf(editAlter.getText())),
                            selectedGender,
                            String.valueOf(editRasse.getText()),
                            Integer.valueOf(String.valueOf(editGroesse.getText())),
                            kastriert,
                            String.valueOf(editBeschreibung.getText())
                    );

                    dogViewModel.doggoAendern(doggo);
                    doggoList.remove(aktuellerDoggo);
                    doggoList.add(doggo);

                    Collections.sort(doggoList, new Comparator<Dog>() {
                        @Override
                        public int compare(Dog o1, Dog o2) {
                            return o1.getName().compareTo(o2.getName());
                        }
                    });

                    mainActivity.setAktuelleDoggosListe(doggoList);

                }

                BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view);
                navBar.setVisibility(View.VISIBLE);

                // In anderes Fragment weiterleiten
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main, AnimalsFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("DogToAnimalsFragmentTransaction")
                        .commit();

                // Dog in Activity updaten
                mainActivity.setAktuellerDog(null);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view);
        navBar.setVisibility(View.VISIBLE);
    }
}
