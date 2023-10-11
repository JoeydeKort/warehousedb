package be.intecbrussel.model;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "storageKey")
public class Key {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Storage storage;

    protected Key() {}

    public Key(Storage storage) {
        this.storage = storage;
    }

    public long getId() {
        return id;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return id == key.id && Objects.equals(storage, key.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, storage);
    }

    @Override
    public String toString() {
        return "Key{" +
                "id=" + id +
                ", storage=" + storage +
                '}';
    }

}
