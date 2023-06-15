package hska.gassishare.ui.animals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import hska.gassishare.MainActivity;
import hska.gassishare.R;
import hska.gassishare.data.entity.Dog;
import hska.gassishare.databinding.FragmentAnimalsBinding;

/**
 * Fragment für die Listenansicht der Tiere
 */
public class AnimalsFragment extends Fragment {

    private FragmentAnimalsBinding binding;     // Binding für das Fragment
    private AnimalsViewModel animalsViewModel;  // ViewModel für die Tiere
    private MainActivity mainActivity;         // Referenz auf die MainActivity

    /**
     * Erstellt die View des Fragments.
     *
     * @param inflater           Der LayoutInflater, der verwendet wird, um die XML-Layoutdatei in ein View-Objekt umzuwandeln.
     * @param container          Der Eltern-View, in den das Fragment eingefügt wird.
     * @param savedInstanceState Das gespeicherte Zustandsbundle.
     * @return Die erstellte View des Fragments.
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = FragmentAnimalsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mainActivity = (MainActivity) getActivity();

        animalsViewModel = new ViewModelProvider(this).get(AnimalsViewModel.class);

        RecyclerView recyclerView = binding.recyclerview;
        final AnimalsListAdapter adapter = new AnimalsListAdapter(new AnimalsListAdapter.WordDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter.setOnItemClickListener(new AnimalsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Dog clickedDog = mainActivity.getAktuelleDoggosListe().get(position);
                mainActivity.setAktuellerDog(clickedDog);

                Navigation.findNavController(getView()).navigate(R.id.dogFragment);

            }
        });
        adapter.submitList(((MainActivity) getActivity()).getAktuelleDoggosListe());


        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(view -> {
            Navigation.findNavController(getView()).navigate(R.id.dogFragment);
        });


        return root;

    }
}
