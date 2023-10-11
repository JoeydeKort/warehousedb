package be.intecbrussel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;
import java.util.Objects;


@Entity
public class Person {

    @Id
    private String name;

    @ManyToMany
    private List<Key> keys;

    protected Person() {}

    public Person(String name, List<Key> keys) {
        this.name = name;
        this.keys = keys;
    }

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
