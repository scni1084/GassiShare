package hska.gassishare.ui.person;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import hska.gassishare.MainActivity;
import hska.gassishare.R;
import hska.gassishare.data.entity.User;
import hska.gassishare.databinding.FragmentMapBinding;
import hska.gassishare.databinding.FragmentPersonBinding;
import hska.gassishare.ui.login.LoginViewModel;
import hska.gassishare.ui.map.MapFragment;
import hska.gassishare.ui.person.PersonViewModel;

public class PersonFragment extends Fragment {

    private FragmentPersonBinding binding;

    private LoginViewModel loginViewModel;

    private EditText editVorname;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PersonViewModel personViewModel =
                new ViewModelProvider(this).get(PersonViewModel.class);

        binding = FragmentPersonBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // loginViewModel fuer Datenzugriff
        MainActivity mainActivity = (MainActivity) getActivity();
        loginViewModel = mainActivity.getLoginViewModel();



        return root;
    }
    //TODO: Daten in Behaelter kriegen, in LoginView steht leider nur LiveData
/*
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Observer<List<User>> usersObserver = new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable final List<User> userListe) {

                if (userListe.size() == 0)
                    return;

                String alleUser = "";
                User user1 = userListe.get(0);

                editVorname.setText(user1.getUsername());
            }
        };
    }
*/
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}