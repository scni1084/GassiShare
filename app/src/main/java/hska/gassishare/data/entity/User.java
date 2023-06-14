package hska.gassishare.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;
import java.util.UUID;

@Entity(tableName = "user_table")
public class User {

    // Primärschlüsselfeld zur Identifizierung des Benutzers
    @PrimaryKey
    @NonNull
    private UUID id;

    // Benutzername
    @NonNull
    private String username;

    // Nachname des Benutzers
    @NonNull
    private String nachname;

    // Vorname des Benutzers
    @NonNull
    private String vorname;

    // Passwort des Benutzers
    @NonNull
    private String passwort;

    // E-Mail-Adresse des Benutzers
    @NonNull
    private String email;

    // Beschreibung des Benutzers
    private String beschreibung;

    // Postleitzahl des Benutzers
    @NonNull
    private Integer plz;

    // Straße des Benutzers
    @NonNull
    private String strasse;

    // Ort des Benutzers
    @NonNull
    private String ort;

    /**
     * Konstruktor zur Erstellung eines neuen User-Objekts.
     *
     * @param id           Die ID des Benutzers.
     * @param username     Der Benutzername.
     * @param nachname     Der Nachname des Benutzers.
     * @param vorname      Der Vorname des Benutzers.
     * @param passwort     Das Passwort des Benutzers.
     * @param email        Die E-Mail-Adresse des Benutzers.
     * @param beschreibung Die Beschreibung des Benutzers.
     * @param plz          Die Postleitzahl des Benutzers.
     * @param strasse      Die Straße des Benutzers.
     * @param ort          Der Ort des Benutzers.
     */
    public User(UUID id, @NonNull String username, @NonNull String nachname, @NonNull String vorname, @NonNull String passwort, @NonNull String email, String beschreibung, @NonNull Integer plz, @NonNull String strasse, @NonNull String ort) {
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

    /**
     * Ruft die ID des Benutzers ab.
     *
     * @return Die ID des Benutzers.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Ruft den Benutzernamen ab.
     *
     * @return Der Benutzername.
     */
    @NonNull
    public String getUsername() {
        return this.username;
    }

    /**
     * Ruft den Nachnamen des Benutzers ab.
     *
     * @return Der Nachname des Benutzers.
     */
    @NonNull
    public String getNachname() {
        return this.nachname;
    }

    /**
     * Ruft den Vornamen des Benutzers ab.
     *
     * @return Der Vorname des Benutzers.
     */
    @NonNull
    public String getVorname() {
        return this.vorname;
    }

    /**
     * Ruft das Passwort des Benutzers ab.
     *
     * @return Das Passwort des Benutzers.
     */
    @NonNull
    public String getPasswort() {
        return this.passwort;
    }

    /**
     * Ruft die E-Mail-Adresse des Benutzers ab.
     *
     * @return Die E-Mail-Adresse des Benutzers.
     */
    @NonNull
    public String getEmail() {
        return this.email;
    }

    /**
     * Ruft die Beschreibung des Benutzers ab.
     *
     * @return Die Beschreibung des Benutzers.
     */
    public String getBeschreibung() {
        return this.beschreibung;
    }

    /**
     * Ruft die Postleitzahl des Benutzers ab.
     *
     * @return Die Postleitzahl des Benutzers.
     */
    @NonNull
    public Integer getPlz() {
        return this.plz;
    }

    /**
     * Ruft die Straße des Benutzers ab.
     *
     * @return Die Straße des Benutzers.
     */
    @NonNull
    public String getStrasse() {
        return this.strasse;
    }

    /**
     * Ruft den Ort des Benutzers ab.
     *
     * @return Der Ort des Benutzers.
     */
    @NonNull
    public String getOrt() {
        return this.ort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && username.equals(user.username) && nachname.equals(user.nachname) && vorname.equals(user.vorname) && passwort.equals(user.passwort) && email.equals(user.email) && Objects.equals(beschreibung, user.beschreibung) && plz.equals(user.plz) && strasse.equals(user.strasse) && ort.equals(user.ort);
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
