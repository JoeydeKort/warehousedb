package be.intecbrussel.service;

import be.intecbrussel.model.Key;
import be.intecbrussel.model.Person;
import be.intecbrussel.repository.PersonRepository;

import java.util.List;


public class PersonService {

    private final PersonRepository personRepository = new PersonRepository();

    public void addPerson(Person person) {
        if (person.getKeys() != null) {
            for (Key key : person.getKeys()) {
                if (key.getId() == 0) {
                    new KeyService().addKey(key);
                } else {
                    new KeyService().updateKey(key);
                }
            }
        }
        personRepository.createPerson(person);
    }

    public Person getPerson(String name) {
        return personRepository.readPerson(name);
    }

    public void updatePerson(Person person) {
        personRepository.updatePerson(person);
    }

    public void deletePerson(String name) {
        personRepository.deletePerson(name);
    }

    public void removeKeyFromPerson(Key key) {
        List<Person> persons = personRepository.readPerson(key);
        for (Person person : persons) {
            person.getKeys().remove(key);
            personRepository.updatePerson(person);
        }
    }

}
