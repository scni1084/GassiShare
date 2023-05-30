package hska.gassishare.ui.person;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hska.gassishare.R;
import hska.gassishare.data.entity.User;

public class PersonActivity extends AppCompatActivity {

    private EditText editVorname;
    private EditText editNachname;
    private EditText editEmail;
    private EditText editBenutzername;
    private EditText editPlz;
    private EditText editStrasse;
    private EditText editOrt;
    private EditText editPassOld;
    private EditText editPassNew;
    private Button saveButton;

    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_person);

        editVorname = findViewById(R.id.editVorname);
        editNachname = findViewById(R.id.editNachname);
        editEmail = findViewById(R.id.editEmail);
        editBenutzername = findViewById(R.id.editBenutzername);
        editPassOld = findViewById(R.id.editPassOld);
        editPassNew = findViewById(R.id.editPassNew);
        saveButton = findViewById(R.id.buttonSpeichern);

        // Get the current user data from intent or database
        // Example: Get user data from intent
        if (getIntent().hasExtra("currentUser")) {
            currentUser = getIntent().getParcelableExtra("currentUser");
        }

        // Populate the existing data in EditText fields
        if (currentUser != null) {
            editVorname.setText(currentUser.getVorname());
            editNachname.setText(currentUser.getNachname());
            editEmail.setText(currentUser.getEmail());
            editBenutzername.setText(currentUser.getUsername());
            editPlz.setText(currentUser.getPlz());
            editStrasse.setText(currentUser.getStrasse());
            editOrt.setText(currentUser.getOrt());
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void saveData() {
        String vorname = editVorname.getText().toString();
        String nachname = editNachname.getText().toString();
        String email = editEmail.getText().toString();
        String benutzername = editBenutzername.getText().toString();
        String passOld = editPassOld.getText().toString();
        String passNew = editPassNew.getText().toString();

        // Keep the existing plz and strasse values
        Integer plz = currentUser.getPlz();
        String strasse = currentUser.getStrasse();
        String ort = currentUser.getOrt();
        Integer id = currentUser.getId();
        String username = currentUser.getUsername();

        User user = new User(id, username, nachname, vorname, passNew, email, currentUser.getBeschreibung(), plz, strasse, ort);
        // Perform save operation or store the data as needed
        // For simplicity, a toast message is shown with the saved data
        String message = "Saved data:\n" +
                "Vorname: " + user.getVorname() + "\n" +
                "Nachname: " + user.getNachname() + "\n" +
                "Email: " + user.getEmail() + "\n" +
                "Benutzername: " + user.getUsername() + "\n" +
                "PLZ: " + user.getPlz() + "\n" +
                "Straße: " + user.getStrasse() + "\n" +
                "Ort: " + user.getOrt() + "\n" +
                "Passwort (new): " + user.getPasswort();

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
