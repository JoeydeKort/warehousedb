package be.intecbrussel.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class RepoConfig {

    private static EntityManagerFactory emf;

    private RepoConfig() {}

    public static EntityManagerFactory getEMF() {

        if(emf == null) {
            emf = Persistence.createEntityManagerFactory("mysql");
        }
        return emf;
    }

    public static EntityManager getEM() {
        return getEMF().createEntityManager();
    }

}
