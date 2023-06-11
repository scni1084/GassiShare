package hska.gassishare.ui.animals;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import hska.gassishare.R;

public class AnimalsViewHolder extends RecyclerView.ViewHolder {
    private final TextView dogItemView;

    private AnimalsViewHolder(View itemView, final AnimalsListAdapter.OnItemClickListener listener) {
        super(itemView);
        dogItemView = itemView.findViewById(R.id.textView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            }
        });
    }

    public void bind(String text) {
        dogItemView.setText(text);
    }

    static AnimalsViewHolder create(ViewGroup parent, AnimalsListAdapter.OnItemClickListener listener) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new AnimalsViewHolder(view, listener);
    }
}
