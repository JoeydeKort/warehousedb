package be.intecbrussel.repository;

import be.intecbrussel.config.RepoConfig;
import be.intecbrussel.exceptions.StorageException;
import be.intecbrussel.model.Key;
import be.intecbrussel.model.Product;
import be.intecbrussel.model.Storage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class StorageRepository {

    public void createStorage(Storage storage) {
        try (EntityManager em = RepoConfig.getEM()) {
            em.getTransaction().begin();
            em.persist(storage);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new StorageException("Error creating storage", ex);
        }
    }

    public Storage readStorage(long id) {
        try (EntityManager em = RepoConfig.getEM()) {
            Storage storage = em.find(Storage.class, id);
            return storage;
        } catch (Exception ex) {
            throw new StorageException("Error reading storage", ex);
        }
    }

    public Storage readStorage(Product product) {
        try (EntityManager em = RepoConfig.getEM()) {
            Query query = em.createQuery("SELECT s FROM Storage s JOIN s.products p WHERE p.id = ?1");
            query.setParameter(1, product.getId());
            return (Storage) query.getSingleResult();
        } catch (Exception ex) {
            throw new StorageException("Error reading storage for product", ex);
        }
    }

    public Storage readStorage(Key key) {
        try (EntityManager em = RepoConfig.getEM()) {
            Query query = em.createQuery("SELECT k.storage FROM Key k WHERE k.id = :keyId");
            query.setParameter("keyId", key.getId());
            return (Storage) query.getSingleResult();
        } catch (Exception ex) {
            throw new StorageException("Error reading storage for key", ex);
        }
    }

    public void updateStorage(Storage storage) {
        try (EntityManager em = RepoConfig.getEM()) {
            em.getTransaction().begin();
            em.merge(storage);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new StorageException("Error updating storage", ex);
        }
    }

    public void deleteStorage(long id) {
        try (EntityManager em = RepoConfig.getEM()){
            em.getTransaction().begin();
            Storage dbStorage = em.find(Storage.class, id);
            if (dbStorage != null) {
                em.remove(dbStorage);
                em.getTransaction().commit();
            } else {
                throw new StorageException("Storage with ID " + id + " not found for deletion.");
            }
        } catch (Exception ex) {
            throw new StorageException("Error deleting storage", ex);
        }
    }
}
