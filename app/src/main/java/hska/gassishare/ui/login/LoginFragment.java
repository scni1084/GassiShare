package hska.gassishare.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;
import java.util.Observer;

import hska.gassishare.data.database.GassishareDatabase;
import hska.gassishare.data.entity.User;
import hska.gassishare.data.repositories.UserRepository;
import hska.gassishare.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LoginViewModel loginViewModel =
                new ViewModelProvider(this).get(LoginViewModel.class);

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        testDatabaseConnection();

        return root;

    }

    private void testDatabaseConnection() {
        System.out.println("Testing db connection");

        // TODO: test if data can be retrieved from DB

        User user = new User(69, "herrchen10",
                "Mustermann", "Max",
                "pw", "max.mustermann@m.de", "Ich bin Max",
                76131, "Musterstrasse", "Karlsruhe");


        UserRepository rep = new UserRepository(getActivity().getApplication());

        // TODO: Bind and do not do this!
        rep.getAllUsers().observe(getViewLifecycleOwner(),items -> {
            for (User u : items ) {
                System.out.println(u);
                binding.UsernameInput.setText(u.getUsername());
                binding.PasswordInput.setText(u.getPasswort());
            }
        });



    }
}

