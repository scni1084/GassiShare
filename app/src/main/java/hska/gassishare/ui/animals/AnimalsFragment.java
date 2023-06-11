package hska.gassishare.ui.animals;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import hska.gassishare.MainActivity;
import hska.gassishare.R;
import hska.gassishare.databinding.FragmentAnimalsBinding;
import hska.gassishare.databinding.FragmentLoginBinding;
import hska.gassishare.ui.dog.DogFragment;
import hska.gassishare.ui.login.LoginViewModel;
import hska.gassishare.ui.person.PersonFragment;

public class AnimalsFragment extends Fragment {

    private FragmentAnimalsBinding binding;


    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    private AnimalsViewModel animalsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = FragmentAnimalsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        animalsViewModel = new ViewModelProvider(this).get(AnimalsViewModel.class);

        RecyclerView recyclerView = binding.recyclerview;
        final AnimalsListAdapter adapter = new AnimalsListAdapter(new AnimalsListAdapter.WordDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        adapter.submitList(((MainActivity)getActivity()).getAktuelleDoggosListe());


        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(view -> {
            // In anderes Fragment weiterleiten
            FragmentManager fragmentManager = getParentFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_main, DogFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("AnimalsToDogTransaction")
                    .commit();
        });

        //TODO: OnClick fuer auf einen spezifischen Hund klicken


        return root;

    }


}
