package hska.gassishare.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String username;
    //..other fields, getters, setters

    @NonNull
    private String nachname;

    @NonNull
    private String vorname;

    @NonNull
    private String passwort;

    @NonNull
    private String email;

    private String beschreibung;

    @NonNull
    private Integer plz;

    @NonNull
    private String strasse;

    @NonNull
    private String ort;


    public User(int id, @NonNull String username, @NonNull String nachname, @NonNull String vorname, @NonNull String passwort, @NonNull String email, String beschreibung, @NonNull Integer plz, @NonNull String strasse, @NonNull String ort) {
        this.id = id;
        this.username = username;
        this.nachname = nachname;
        this.vorname = vorname;
        this.passwort = passwort;
        this.email = email;
        this.beschreibung = beschreibung;
        this.plz = plz;
        this.strasse = strasse;
        this.ort = ort;
    }

    public int getId() {
        return this.id;
    }

    @NonNull
    public String getUsername() {
        return this.username;
    }

    @NonNull
    public String getNachname() {
        return this.nachname;
    }

    @NonNull
    public String getVorname() {
        return this.vorname;
    }

    @NonNull
    public String getPasswort() {
        return this.passwort;
    }

    @NonNull
    public String getEmail() {
        return this.email;
    }

    public String getBeschreibung() {
        return this.beschreibung;
    }

    @NonNull
    public Integer getPlz() {
        return this.plz;
    }

    @NonNull
    public String getStrasse() {
        return this.strasse;
    }

    @NonNull
    public String getOrt() {
        return this.ort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && username.equals(user.username) && nachname.equals(user.nachname) && vorname.equals(user.vorname) && passwort.equals(user.passwort) && email.equals(user.email) && Objects.equals(beschreibung, user.beschreibung) && plz.equals(user.plz) && strasse.equals(user.strasse) && ort.equals(user.ort);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nachname='" + nachname + '\'' +
                ", vorname='" + vorname + '\'' +
                ", passwort='" + passwort + '\'' +
                ", email='" + email + '\'' +
                ", beschreibung='" + beschreibung + '\'' +
                ", plz=" + plz +
                ", strasse='" + strasse + '\'' +
                ", ort='" + ort + '\'' +
                '}';
    }
}
