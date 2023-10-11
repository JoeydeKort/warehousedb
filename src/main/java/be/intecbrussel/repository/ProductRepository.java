package be.intecbrussel.repository;

import be.intecbrussel.config.RepoConfig;
import be.intecbrussel.exceptions.CustomProductException;
import be.intecbrussel.exceptions.ProductNotFoundException;
import be.intecbrussel.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

public class ProductRepository {

    public void createProduct(Product product) {

        try (EntityManager em = RepoConfig.getEM()) {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();

        } catch (PersistenceException pEx) {
            throw new CustomProductException("Error creating product", pEx);
        }

    }

    public Product readProduct(long id) {

        try (EntityManager em = RepoConfig.getEM()) {
            Product product = em.find(Product.class, id);
            if (product == null) {
                throw new ProductNotFoundException("Product with id " + id + " not found!");
            }
            return product;
        }
    }

    public void updateProduct(Product product) {
        try (EntityManager em = RepoConfig.getEM()) {

            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
        }
    }

    public void deleteProduct(long id) {

        try (EntityManager em = RepoConfig.getEM()) {

            em.getTransaction().begin();
            Product product = em.find(Product.class, id);
            em.remove(product);
            em.getTransaction().commit();
        }
    }

}
