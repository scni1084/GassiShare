package hska.gassishare.ui.person;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import hska.gassishare.R;
import hska.gassishare.data.entity.User;

public class PersonViewHolder extends RecyclerView.ViewHolder {

    private EditText editVorname;
    private EditText editNachname;
    private EditText editEmail;
    private EditText editBenutzername;
    private EditText editPassOld;
    private EditText editPassNew;

    public PersonViewHolder(@NonNull View itemView) {
        super(itemView);
        editVorname = itemView.findViewById(R.id.editVorname);
        editNachname = itemView.findViewById(R.id.editNachname);
        editEmail = itemView.findViewById(R.id.editEmail);
        editBenutzername = itemView.findViewById(R.id.editBenutzername);
        editPassOld = itemView.findViewById(R.id.editPassOld);
        editPassNew = itemView.findViewById(R.id.editPassNew);
    }

    public void bind(User user) {
        editVorname.setText(user.getVorname());
        editNachname.setText(user.getNachname());
        // Set other values as needed
    }

    public static PersonViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_person, parent, false);
        return new PersonViewHolder(view);
    }
}