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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.Random;

import hska.gassishare.MainActivity;
import hska.gassishare.R;
import hska.gassishare.data.entity.Dog;
import hska.gassishare.databinding.FragmentDogBinding;
import hska.gassishare.ui.animals.AnimalsFragment;
import hska.gassishare.ui.profile.ProfileFragment;

public class DogFragment extends Fragment {

    private FragmentDogBinding binding;

    private Dog aktuellerDoggo;

    private MainActivity mainActivity;

    private EditText editName;

    private EditText editRasse;

    private EditText editGroesse;

    private EditText editBeschreibung;

    private EditText editAlter;

    private RadioGroup groupGeschlecht;

    private RadioGroup groupKastriert;

    private Button dogSpeichern;

    private DogViewModel dogViewModel;

    private List<Dog> doggoList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dogViewModel = new ViewModelProvider(this).get(DogViewModel.class);

        binding = FragmentDogBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mainActivity = (MainActivity)getActivity();

        aktuellerDoggo = mainActivity.getAktuellerDog();

        doggoList = mainActivity.getAktuelleDoggosListe();

        // Find the ImageView
        ImageView imageView = root.findViewById(R.id.imageView2);

        // Set the image resource
        imageView.setImageResource(R.drawable.dog1);

        return root;
    }

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
        if(mainActivity.getAktuellerDog() != null) {

            editName.setText(mainActivity.getAktuellerDog().getName());
            editRasse.setText(mainActivity.getAktuellerDog().getRasse());
            editGroesse.setText(mainActivity.getAktuellerDog().getGroesse().toString());
            editBeschreibung.setText(mainActivity.getAktuellerDog().getBeschreibung());
            editAlter.setText(mainActivity.getAktuellerDog().getAlter().toString());

            // Set the value for groupGeschlecht
            String geschlecht = mainActivity.getAktuellerDog().getGeschlecht(); // Replace with the actual method to get the value
            RadioButton radioButtonGeschlecht;

            if (geschlecht.equals("männlich")) {
                radioButtonGeschlecht = view.findViewById(R.id.radioGeschlecht1);
                radioButtonGeschlecht.setChecked(true);
            } else if (geschlecht.equals("weiblich")) {
                radioButtonGeschlecht = view.findViewById(R.id.radioGeschlecht2);
                radioButtonGeschlecht.setChecked(true);
            }


            // Set the value for groupKastriert
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

                //TODO: Validieren / Fehlermeldung anzeigen

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


                // Dog in DB updaten oder erstellen
                if (mainActivity.getAktuellerDog() == null) {

                    Dog doggo = new Dog(
                            //TODO: ID zu anpassen ?
                            new Random().nextInt(Integer.MAX_VALUE),
                            mainActivity.getAktuellerUser().getId(),
                            String.valueOf(editName.getText()),
                            0,
                            //Integer.valueOf(String.valueOf(editAlter.getText())),
                            selectedGender,
                            String.valueOf(editRasse.getText()),
                            //TODO: Fehlerbehandlung fuer falls Groesse String ist
                            Integer.valueOf(String.valueOf(editGroesse.getText())),
                            kastriert,
                            String.valueOf(editBeschreibung.getText())
                    );

                    dogViewModel.doggoAnlegen(doggo);


                    doggoList.add(doggo);
                    mainActivity.setAktuelleDoggosListe(doggoList);

                }
                else {
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

                }

                //Navigation wieder sichtbar machen
                BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view);
                navBar.setVisibility(View.VISIBLE);


                // In anderes Fragment weiterleiten
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main, AnimalsFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("PersonToProfileFragmentTransaction")
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
    }
}