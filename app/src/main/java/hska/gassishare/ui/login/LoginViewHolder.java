package hska.gassishare.ui.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import hska.gassishare.R;

public class LoginViewHolder {
    private final TextView userItemView;

    private LoginViewHolder(View itemView) {
        //super(itemView);
        userItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text) {
        userItemView.setText(text);
    }

    static LoginViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_login, parent, false);
        return new LoginViewHolder(view);
    }
}
