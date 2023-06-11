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

    private EditText editVorname;

    private EditText editNachname;

    private EditText editEmail;

    private EditText editBeschreibung;

    private EditText editStrasse;

    private EditText editPLZ;

    private EditText editOrt;

    private EditText editOldPassword;

    private EditText editNewPassword;

    private Button aenderungenSpeichernButton;

    private MainActivity mainActivity;

    private PersonViewModel personViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        personViewModel = new ViewModelProvider(this).get(PersonViewModel.class);

        binding = FragmentPersonBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mainActivity = ((MainActivity)getActivity());
        User aktuellerUser = mainActivity.getAktuellerUser();

        editVorname = getView().findViewById(R.id.editVorname);
        editNachname = getView().findViewById(R.id.editNachname);
        editEmail = getView().findViewById(R.id.editEmail);
        editBeschreibung = getView().findViewById(R.id.editBeschreibung);
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

                // Aenderungen nehmen
                User geaenderterUser = new User(
                        aktuellerUser.getId(),
                        aktuellerUser.getUsername(),
                        String.valueOf(editNachname.getText()),
                        String.valueOf(editVorname.getText()),
                        String.valueOf(editNewPassword.getText()),
                        String.valueOf(editEmail.getText()),
                        String.valueOf(editBeschreibung.getText()),
                        Integer.parseInt(editPLZ.getText().toString()),
                        String.valueOf(editStrasse.getText()),
                        String.valueOf(editOrt.getText())
                );

                // User in Activity updaten
                mainActivity.setAktuellerUser(geaenderterUser);

                // User in DB updaten
                personViewModel.UserUpdaten(geaenderterUser);


            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}