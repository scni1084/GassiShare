package hska.gassishare.ui.person;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import hska.gassishare.data.entity.User;

public class PersonListAdapter extends ListAdapter<User, PersonViewHolder> {

    protected PersonListAdapter(@NonNull DiffUtil.ItemCallback<User> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return PersonViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        User current = getItem(position);
        holder.bind(current);
    }

    public static class PersonDiff extends DiffUtil.ItemCallback<User> {

        @Override
        public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.equals(newItem);
        }
    }
}
