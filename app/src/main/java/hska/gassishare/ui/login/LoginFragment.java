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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.Observer;

import java.util.List;

import hska.gassishare.R;
import hska.gassishare.data.database.GassishareDatabase;
import hska.gassishare.data.entity.User;
import hska.gassishare.data.repositories.UserRepository;
import hska.gassishare.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    private LoginViewModel loginViewModel;

    // UI-Elemente
    private TextView textView3;

    private EditText usernameInput;

    private EditText passwordInput;

    private Button button5;

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
    }
}

