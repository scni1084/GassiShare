package hska.gassishare.ui.dog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import hska.gassishare.databinding.FragmentDogBinding;

public class DogFragment extends Fragment {

    private FragmentDogBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        hska.gassishare.ui.dog.DogViewModel dogViewModel =
                new ViewModelProvider(this).get(DogViewModel.class);

        binding = FragmentDogBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    //TODO: Einzelansicht fuer den Hund wie bei Person

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}