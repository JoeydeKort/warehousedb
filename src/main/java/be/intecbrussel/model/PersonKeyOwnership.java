package be.intecbrussel.model;

import jakarta.persistence.*;


@Entity
public class PersonKeyOwnership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne( fetch = FetchType.EAGER)
    private Key key;

    @ManyToOne( fetch = FetchType.EAGER)
    private Person person;

}

