package hska.gassishare.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.Observer;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import hska.gassishare.MainActivity;
import hska.gassishare.R;
import hska.gassishare.data.entity.Dog;
import hska.gassishare.data.entity.User;
import hska.gassishare.databinding.FragmentLoginBinding;
import hska.gassishare.ui.map.MapFragment;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    private LoginViewModel loginViewModel;

    // UI-Elemente
    private TextView textView3;

    private EditText usernameInput;

    private EditText passwordInput;

    private Button anmeldenButton;

    // neu
    public LoginFragment() {
        super(R.layout.fragment_login);


    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView3 = getView().findViewById(R.id.textView3);
        usernameInput = getView().findViewById(R.id.UsernameInput);
        passwordInput = getView().findViewById(R.id.PasswordInput);
        anmeldenButton = getView().findViewById(R.id.AnmeldenButton);

        // Create the observer which updates the UI.
        final Observer<List<User>> usersObserver = new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable final List<User> userListe) {

                if (userListe.size() == 0)
                    return;

                String alleUser = "";
                User user1 = userListe.get(0);

                usernameInput.setText(user1.getUsername());
                passwordInput.setText(user1.getPasswort());


                for (User u : userListe ) {
                    alleUser = alleUser + u.toString();
                    //usernameInput.setText(u.getUsername());
                    //passwordInput.setText(u.getPasswort());
                }
                textView3.setText(alleUser);
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        loginViewModel.getAlleUser().observe(getViewLifecycleOwner(), usersObserver);

        // OnClick fuer Anmelde-Button
        anmeldenButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String username = String.valueOf(usernameInput.getText());
                String passwort = String.valueOf(passwordInput.getText());

                if(!loginViewModel.userExists(username,passwort)) {
                    return;
                }

                System.out.println("Der nutzer existiert");

                MainActivity mainActivity = ((MainActivity)getActivity());

                mainActivity.setAktuellerUser(loginViewModel.getUser(username));

                int userId = mainActivity.getAktuellerUser().getId();

                ((MainActivity)getActivity()).setAktuelleDoggosListe(loginViewModel.getDogsForUser(userId));

                BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view);
                navBar.setVisibility(View.VISIBLE);

                // In anderes Fragment weiterleiten
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main, MapFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("LoginToMapTransaction")
                        .commit();
            }
        });

        // hide navbar on login
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
    }
}

