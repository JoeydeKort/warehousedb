package be.intecbrussel.repository;

import be.intecbrussel.config.RepoConfig;
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
        }
    }

    public Storage readStorage(long id) {
        try (EntityManager em = RepoConfig.getEM()) {
            return em.find(Storage.class, id);
        }
    }

    public Storage readStorage(Product product) {
        try (EntityManager em = RepoConfig.getEM()) {
            Query query = em.createQuery("SELECT s FROM Storage s JOIN s.products p WHERE p.id = ?1");
            query.setParameter(1, product.getId());
            return (Storage) query.getSingleResult();
        }
    }

    public void updateStorage(Storage storage) {
        try (EntityManager em = RepoConfig.getEM()) {
            em.getTransaction().begin();
            em.merge(storage);
            em.getTransaction().commit();
        }
    }

    public void deleteStorage(long id) {
        try (EntityManager em = RepoConfig.getEM()){
            em.getTransaction().begin();
            Storage dbStorage = em.find(Storage.class, id);
            em.remove(dbStorage);
            em.getTransaction().commit();
        }
    }

}
