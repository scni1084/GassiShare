package hska.gassishare.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import hska.gassishare.MainActivity;
import hska.gassishare.R;
import hska.gassishare.data.entity.User;
import hska.gassishare.databinding.FragmentProfileBinding;
import hska.gassishare.ui.animals.AnimalsFragment;
import hska.gassishare.ui.map.MapFragment;
import hska.gassishare.ui.person.PersonFragment;
import hska.gassishare.ui.person.PersonViewModel;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    private Button buttonPerson;

    private Button buttonDogs;

    private Button buttonAbmelden;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PersonViewModel personViewModel =
                new ViewModelProvider(this).get(PersonViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonPerson = getView().findViewById(R.id.buttonPerson);

        buttonAbmelden = getView().findViewById(R.id.AbmeldenButton);

        buttonDogs = getView().findViewById(R.id.buttonDogs);

        buttonPerson.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // In anderes Fragment weiterleiten
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main, PersonFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("ProfileToPersonTransaction")
                        .commit();

            }

        });

        buttonAbmelden.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //TODO: alte Daten löschen, **Login**ViewModel löschen und dann zum Startbildschirm zurueckkehren
                // gespeicherten User in der MainActivity auf null setzen!

            }
        });

        buttonDogs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // In anderes Fragment weiterleiten
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main, AnimalsFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("ProfileToAnimalsTransaction")
                        .commit();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}