package hska.gassishare.ui.person;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import hska.gassishare.MainActivity;
import hska.gassishare.R;
import hska.gassishare.data.entity.User;
import hska.gassishare.databinding.FragmentPersonBinding;
import hska.gassishare.ui.profile.ProfileFragment;

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

        // Image setzen
        ImageView imageView = root.findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.avatar);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // In anderes Fragment weiterleiten
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main, ProfileFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("PersonToProfileFragmentTransaction")
                        .commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);




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


        mainActivity = ((MainActivity)getActivity());
        User aktuellerUser = mainActivity.getAktuellerUser();

        editVorname = getView().findViewById(R.id.editVorname);
        editNachname = getView().findViewById(R.id.editNachname);
        editEmail = getView().findViewById(R.id.editEmail);
        editBeschreibung = getView().findViewById(R.id.editHundeBeschreibung);
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

                String savedPW = aktuellerUser.getPasswort();

                if(String.valueOf(editNewPassword.getText()) == "" ||
                        String.valueOf(editOldPassword) != aktuellerUser.getPasswort()
                ) {
                }
                else {
                    savedPW = String.valueOf(editNewPassword.getText());
                }


                User geaenderterUser = new User(
                        aktuellerUser.getId(),
                        aktuellerUser.getUsername(),
                        String.valueOf(editNachname.getText()),
                        String.valueOf(editVorname.getText()),
                        savedPW,
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


                //Navigation wieder sichtbar machen
                BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view);
                navBar.setVisibility(View.VISIBLE);


                // In anderes Fragment weiterleiten
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main, ProfileFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("PersonToProfileFragmentTransaction")
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