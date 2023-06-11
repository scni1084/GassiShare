package hska.gassishare.ui.animals;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import hska.gassishare.data.entity.Dog;

public class AnimalsListAdapter extends ListAdapter<Dog, AnimalsViewHolder> {

    public AnimalsListAdapter(@NonNull DiffUtil.ItemCallback<Dog> diffCallback) {
        super(diffCallback);
    }

    @Override
    public AnimalsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return AnimalsViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(AnimalsViewHolder holder, int position) {
        Dog current = getItem(position);
        holder.bind(current.getName());
    }

    static class WordDiff extends DiffUtil.ItemCallback<Dog> {

        @Override
        public boolean areItemsTheSame(@NonNull Dog oldItem, @NonNull Dog newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Dog oldItem, @NonNull Dog newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }


}

