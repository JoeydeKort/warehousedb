package be.intecbrussel.repository;

import be.intecbrussel.config.RepoConfig;
import be.intecbrussel.exceptions.ProductException;
import be.intecbrussel.exceptions.StorageException;
import be.intecbrussel.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

public class ProductRepository {

    public void createProduct(Product product) {

        try (EntityManager em = RepoConfig.getEM()) {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
        } catch (PersistenceException ex) {
            throw new ProductException("Error creating product", ex);
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred while creating a product", ex);
        }

    }

    public Product readProduct(long id) {

        try (EntityManager em = RepoConfig.getEM()) {
            Product product = em.find(Product.class, id);
            if (product == null) {
                throw new StorageException("Product with ID " + id + " not found.");
            }
            return product;
        } catch (PersistenceException ex) {
            throw new ProductException("Error reading product", ex);
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred while reading a product", ex);
        }

    }

    public void updateProduct(Product product) {
        try (EntityManager em = RepoConfig.getEM()) {
            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
        } catch (PersistenceException ex) {
            throw new ProductException("Error updating product", ex);
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred while updating a product", ex);
        }
    }

    public void deleteProduct(long id) {
        try (EntityManager em = RepoConfig.getEM()) {
            em.getTransaction().begin();
            Product dbProduct = em.find(Product.class, id);
            em.remove(dbProduct);
            em.getTransaction().commit();
        } catch (PersistenceException ex) {
            throw new ProductException("Error deleting product", ex);
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred while deleting a product", ex);
        }
    }

}
