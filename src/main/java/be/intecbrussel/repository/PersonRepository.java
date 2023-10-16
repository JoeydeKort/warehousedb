package be.intecbrussel.repository;

import be.intecbrussel.config.RepoConfig;
import be.intecbrussel.exceptions.PersonException;
import be.intecbrussel.model.Key;
import be.intecbrussel.model.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class PersonRepository {

    public void createPerson(Person person) {
        try (EntityManager em = RepoConfig.getEM()) {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new PersonException("Failed to create person.", ex);
        }
    }

    public Person readPerson(String name) {
        try (EntityManager em = RepoConfig.getEM()) {
            Person person = em.find(Person.class, name);
            if (person == null) {
                throw new PersonException("Person with name " + name + " not found.");
            }
            return person;
        } catch (Exception ex) {
            throw new PersonException("Failed to read person.", ex);
        }
    }

    public List<Person> readPerson(Key key) {
        try (EntityManager em = RepoConfig.getEM()) {
            Query query = em.createQuery("SELECT p FROM Person p JOIN p.keys k WHERE k.id = ?1");
            query.setParameter(1, key.getId());
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersonException("Failed to read persons with a specific key.", ex);
        }
    }

    public void updatePerson(Person person) {
        try (EntityManager em = RepoConfig.getEM()) {
            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new PersonException("Failed to update person.", ex);
        }
    }

    public void deletePerson(String name) {
        try (EntityManager em = RepoConfig.getEM()) {
            em.getTransaction().begin();
            Person dbPerson = em.find(Person.class, name);
            if (dbPerson != null) {
                em.remove(dbPerson);
                em.getTransaction().commit();
            } else {
                throw new PersonException("Person with name " + name + " not found for deletion.");
            }
        } catch (Exception ex) {
            throw new PersonException("Failed to delete person.", ex);
        }
    }
}
