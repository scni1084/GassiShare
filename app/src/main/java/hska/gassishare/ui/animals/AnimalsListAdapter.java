package hska.gassishare.ui.animals;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import hska.gassishare.data.entity.Dog;

public class AnimalsListAdapter extends ListAdapter<Dog, AnimalsViewHolder> {

    private OnItemClickListener mListener;

    /**
     * Konstruktor für den AnimalsListAdapter.
     *
     * @param diffCallback Das DiffUtil.ItemCallback-Objekt für die Vergleichslogik.
     */
    public AnimalsListAdapter(@NonNull DiffUtil.ItemCallback<Dog> diffCallback) {
        super(diffCallback);
    }

    /**
     * Setzt den Click-Listener für die Liste.
     *
     * @param listener Der OnItemClickListener.
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    /**
     * Gibt den aktuellen OnItemClickListener zurück.
     *
     * @return Der OnItemClickListener.
     */
    public OnItemClickListener getmListener() {
        return mListener;
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

    /**
     * Interface für den Click-Listener der Liste.
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    /**
     * DiffUtil.ItemCallback-Klasse für den Vergleich der Hunde.
     */
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
