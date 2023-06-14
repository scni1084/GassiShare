package hska.gassishare.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import hska.gassishare.MainActivity;
import hska.gassishare.R;
import hska.gassishare.databinding.FragmentProfileBinding;
import hska.gassishare.ui.animals.AnimalsFragment;
import hska.gassishare.ui.login.LoginFragment;
import hska.gassishare.ui.person.PersonFragment;
import hska.gassishare.ui.person.PersonViewModel;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    private Button buttonPerson;           // Button für den Benutzerbereich
    private Button buttonDogs;             // Button für die Hundeübersicht
    private Button buttonAbmelden;         // Button für die Abmeldung
    private MainActivity mainActivity;    // Referenz auf die MainActivity

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
        PersonViewModel personViewModel =
                new ViewModelProvider(this).get(PersonViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mainActivity = (MainActivity) getActivity();

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

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),
                new OnBackPressedCallback(true /* enabled by default */) {
                    @Override
                    public void handleOnBackPressed() {
                        // Handle the back button event
                        // For example, navigate to another fragment
                        NavController navController = Navigation.findNavController(view);
                        navController.navigate(R.id.navigation_profile);
                    }
                });

        buttonPerson = getView().findViewById(R.id.buttonPerson);
        buttonAbmelden = getView().findViewById(R.id.AbmeldenButton);
        buttonDogs = getView().findViewById(R.id.buttonDogs);

        buttonPerson.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Weiterleitung zu einem anderen Fragment
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
                mainActivity.setAktuellerUser(null);

                // Weiterleitung zu einem anderen Fragment
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main, LoginFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("ProfileToLoginTranscation")
                        .commit();
            }
        });

        buttonDogs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Weiterleitung zu einem anderen Fragment
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main, AnimalsFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("ProfileToAnimalsTransaction")
                        .commit();
            }
        });

        view.post(new Runnable() {
            @Override
            public void run() {
                BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view);

                while (navBar == null) {
                    navBar = getActivity().findViewById(R.id.nav_view);
                }
                navBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
