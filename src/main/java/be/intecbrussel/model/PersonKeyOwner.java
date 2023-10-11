package be.intecbrussel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class PersonKeyOwner {

    @Id
    private long id;

    @ManyToOne
    private Key key;

    @ManyToOne
    private Person person;


}
