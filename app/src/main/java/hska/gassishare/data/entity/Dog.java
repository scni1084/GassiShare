package hska.gassishare.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "dog_table")
public class Dog {

    // Primärschlüsselfeld zur Identifizierung des Hundes
    @PrimaryKey
    @NonNull
    private UUID id;

    // Fremdschlüsselfeld, das auf den Benutzer verweist, dem der Hund gehört
    @NonNull
    private UUID user_id;

    // Name des Hundes
    @NonNull
    private String name;

    // Alter des Hundes
    private Integer alter;

    // Geschlecht des Hundes
    private String geschlecht;

    // Rasse des Hundes
    private String rasse;

    // Gibt an, ob der Hund kastriert ist
    private Boolean kastriert;

    // Beschreibung des Hundes
    private String beschreibung;

    // Größe des Hundes
    private Integer groesse;

    /**
     * Konstruktor zur Erstellung eines neuen Dog-Objekts.
     *
     * @param id           Die ID des Hundes.
     * @param user_id      Die ID des Besitzers, dem der Hund gehört.
     * @param name         Der Name des Hundes.
     * @param alter        Das Alter des Hundes.
     * @param geschlecht   Das Geschlecht des Hundes.
     * @param rasse        Die Rasse des Hundes.
     * @param groesse      Die Größe des Hundes.
     * @param kastriert    Gibt an, ob der Hund kastriert ist.
     * @param beschreibung Die Beschreibung des Hundes.
     */
    public Dog(UUID id, @NonNull UUID user_id, @NonNull String name, Integer alter, String geschlecht, String rasse, Integer groesse, Boolean kastriert, String beschreibung) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.alter = alter;
        this.geschlecht = geschlecht;
        this.rasse = rasse;
        this.groesse = groesse;
        this.kastriert = kastriert;
        this.beschreibung = beschreibung;
    }

    /**
     * Ruft die ID des Hundes ab.
     *
     * @return Die ID des Hundes.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Ruft die Benutzer-ID des Hundebesitzers ab.
     *
     * @return Die Benutzer-ID des Hundebesitzers.
     */
    public UUID getUser_id() {
        return this.user_id;
    }

    /**
     * Ruft den Namen des Hundes ab.
     *
     * @return Der Name des Hundes.
     */
    @NonNull
    public String getName() {
        return this.name;
    }

    /**
     * Ruft das Alter des Hundes ab.
     *
     * @return Das Alter des Hundes.
     */
    public Integer getAlter() {
        return this.alter;
    }

    /**
     * Ruft das Geschlecht des Hundes ab.
     *
     * @return Das Geschlecht des Hundes.
     */
    public String getGeschlecht() {
        return this.geschlecht;
    }

    /**
     * Ruft die Rasse des Hundes ab.
     *
     * @return Die Rasse des Hundes.
     */
    public String getRasse() {
        return this.rasse;
    }

    /**
     * Ruft den Kastrationsstatus des Hundes ab.
     *
     * @return Der Kastrationsstatus des Hundes.
     */
    public Boolean getKastriert() {
        return this.kastriert;
    }

    /**
     * Ruft die Beschreibung des Hundes ab.
     *
     * @return Die Beschreibung des Hundes.
     */
    public String getBeschreibung() {
        return this.beschreibung;
    }

    /**
     * Ruft die Größe des Hundes ab.
     *
     * @return Die Größe des Hundes.
     */
    public Integer getGroesse() {
        return groesse;
    }
}
