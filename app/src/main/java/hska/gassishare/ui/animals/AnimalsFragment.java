package hska.gassishare.ui.animals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import hska.gassishare.databinding.FragmentAnimalsBinding;

public class AnimalsFragment extends Fragment {

    private FragmentAnimalsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        hska.gassishare.ui.animals.AnimalsViewModel animalsViewModel =
                new ViewModelProvider(this).get(AnimalsViewModel.class);

        binding = FragmentAnimalsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;

    }

    //TODO: Liste erstellen von den Hunden, die dem User gehoeren

}
