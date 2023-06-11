package hska.gassishare.ui.dog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dogViewModel = new ViewModelProvider(this).get(DogViewModel.class);

        binding = FragmentDogBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mainActivity = (MainActivity)getActivity();

        aktuellerDoggo = mainActivity.getAktuellerDog();

        return root;
    }

    //TODO: Einzelansicht fuer den Hund wie bei Person
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

        //TODO: Werte in TextViews setzen, falls Dog existiert



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


                // Dog in DB updaten oder erstellen
                if (mainActivity.getAktuellerDog() == null) {

                    Dog doggo = new Dog(
                            300,
                            mainActivity.getAktuellerUser().getId(),
                            String.valueOf(editName.getText()),
                            0,
                            //Integer.valueOf(String.valueOf(editAlter.getText())),
                            selectedGender,
                            String.valueOf(editRasse.getText()),
                            kastriert,
                            String.valueOf(editBeschreibung.getText())
                    );

                    dogViewModel.doggoAnlegen(doggo);


                    List<Dog> doggoList = mainActivity.getAktuelleDoggosListe();
                    doggoList.add(doggo);
                    mainActivity.setAktuelleDoggosListe(doggoList);

                }
                else {
                    //TODO: Dog updaten
                    Dog doggo = new Dog(
                            300,
                            mainActivity.getAktuellerUser().getId(),
                            String.valueOf(editName.getText()),
                            Integer.valueOf(String.valueOf(editAlter.getText())),
                            selectedGender,
                            String.valueOf(editRasse.getText()),
                            kastriert,
                            String.valueOf(editBeschreibung.getText())
                    );


                    dogViewModel.doggoAnlegen(doggo);

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