package be.intecbrussel.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Person {

    @Id
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "PersonKeyOwnership",
            joinColumns = @JoinColumn(name = "person_name", referencedColumnName = "name"),
            inverseJoinColumns = @JoinColumn(name = "key_id"))
    private List<Key> keys;


    public Person(String name) {
        this.name = name;
        this.keys = new ArrayList<>();
    }

    protected Person(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Key> getKeys() {
        return keys;
    }

    public void setKeys(List<Key> keys) {
        this.keys = keys;
        for (Key key : keys) {
            if (!key.getPersons().contains(this)) {
                key.addPerson(this);
            }
        }
    }

    public void addKey(Key key) {
        if (!keys.contains(key)) {
            keys.add(key);
            key.addPerson(this);
        }
    }

    public void addKey(Key... keys) {
        if (keys != null) {
            for (Key key : keys) {
                addKey(key);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(keys, person.keys);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, keys);
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", keys=" + keys +
                '}';
    }

}

