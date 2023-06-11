package hska.gassishare.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "dog_table")
public class Dog {

    @PrimaryKey
    @NonNull
    private UUID id;

    @NonNull
    private UUID user_id;

    @NonNull
    private String name;

    private Integer alter;

    private String geschlecht;

    private String rasse;

    private Boolean kastriert;

    private String beschreibung;

    private Integer groesse;


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

    public UUID getId() {
        return this.id;
    }

    public UUID getUser_id() {
        return this.user_id;
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    public Integer getAlter() {
        return this.alter;
    }

    public String getGeschlecht() {
        return this.geschlecht;
    }

    public String getRasse() {
        return this.rasse;
    }

    public Boolean getKastriert() {
        return this.kastriert;
    }

    public String getBeschreibung() {
        return this.beschreibung;
    }

    public Integer getGroesse() {
        return groesse;
    }
}

