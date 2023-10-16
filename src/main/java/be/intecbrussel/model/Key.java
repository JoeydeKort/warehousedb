package be.intecbrussel.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "StorageKey")
public class Key {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Storage storage;

    @ManyToMany(mappedBy = "keys", fetch = FetchType.EAGER)
    private List<Person> persons;

    public Key(Storage storage) {
        this.storage = storage;
        this.persons = new ArrayList<>();
    }

    protected Key(){}

    public long getId() {
        return id;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
        for (Person person : persons) {
            if (!person.getKeys().contains(this)) {
                person.getKeys().add(this);
            }
        }
    }

    public void addPerson(Person person) {
        if (person == null) {return;}

        if (persons == null) {persons = new ArrayList<>();}

        if (!persons.contains(person)) {
            persons.add(person);
            person.addKey(this);
        }
    }

    public void addPerson(Person... people) {
        if (people != null) {
            for (Person person : people) {
                addPerson(person);
            }
        }
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


