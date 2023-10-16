package be.intecbrussel.repository;

import be.intecbrussel.config.RepoConfig;
import be.intecbrussel.exceptions.KeyException;
import be.intecbrussel.model.Key;
import be.intecbrussel.model.Person;
import be.intecbrussel.model.Storage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class KeyRepository {

    public void createKey(Key key) {
        try (EntityManager em = RepoConfig.getEM()) {
            em.getTransaction().begin();
            em.persist(key);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new KeyException("Failed to create key.", ex);
        }
    }

    public Key readKey(long id) {
        try (EntityManager em = RepoConfig.getEM()) {
            Key key = em.find(Key.class, id);
            if (key == null) {
                throw new KeyException("Key with ID " + id + " not found.");
            }
            return key;
        } catch (Exception ex) {
            throw new KeyException("Failed to read key.", ex);
        }
    }

    public Key readKey(Person person) {
        try (EntityManager em = RepoConfig.getEM()) {
            Query query = em.createQuery("SELECT k FROM Key k JOIN k.persons p WHERE p.name = ?1");
            query.setParameter(1, person.getName());
            return (Key) query.getSingleResult();
        } catch (Exception ex) {
            throw new KeyException("Failed to read key for person.", ex);
        }
    }

    public Key readKey(Storage storage) {
        try (EntityManager em = RepoConfig.getEM()) {
            Query query = em.createQuery("SELECT k FROM Key k WHERE k.storage = ?1");
            query.setParameter(1, storage);
            return (Key) query.getSingleResult();
        } catch (Exception ex) {
            throw new KeyException("Failed to read key for storage.", ex);
        }
    }

    public void updateKey(Key key) {
        try (EntityManager em = RepoConfig.getEM()) {
            em.getTransaction().begin();
            em.merge(key);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new KeyException("Failed to update key.", ex);
        }
    }

    public void deleteKey(long id) {
        try (EntityManager em = RepoConfig.getEM()) {
            em.getTransaction().begin();
            Key key = em.find(Key.class, id);
            if (key != null) {
                em.remove(key);
                em.getTransaction().commit();
            } else {
                throw new KeyException("Key with ID " + id + " not found for deletion.");
            }
        } catch (Exception ex) {
            throw new KeyException("Failed to delete key.", ex);
        }
    }

    public void deletePersonKeyOwnershipRecords(long keyId) {
        try (EntityManager em = RepoConfig.getEM()) {
            em.getTransaction().begin();

            Query query = em.createQuery("DELETE FROM PersonKeyOwnership pko WHERE pko.key.id = :keyId");
            query.setParameter("keyId", keyId);
            query.executeUpdate();

            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new KeyException("Failed to delete person key ownership records.", ex);
        }
    }
}
