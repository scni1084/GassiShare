package hska.gassishare.ui.animals;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import hska.gassishare.R;
import hska.gassishare.data.entity.Dog;
public class AnimalsListAdapter extends ListAdapter<Dog, AnimalsViewHolder> {

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public OnItemClickListener getmListener() {
        return mListener;
    }

    public AnimalsListAdapter(@NonNull DiffUtil.ItemCallback<Dog> diffCallback) {
        super(diffCallback);
    }

    @Override
    public AnimalsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return AnimalsViewHolder.create(parent, mListener);
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
