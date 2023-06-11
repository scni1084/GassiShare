package hska.gassishare.ui.animals;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import hska.gassishare.R;

public class AnimalsViewHolder extends RecyclerView.ViewHolder {
    private final TextView dogItemView;

    private AnimalsViewHolder(View itemView) {
        super(itemView);
        dogItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text) {
        dogItemView.setText(text);
    }

    static AnimalsViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new AnimalsViewHolder(view);
    }
}

