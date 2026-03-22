package se.yrgo.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

// En klass som ansvarar för kopplingen till derby-databasen

public class JpaUtil {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("jpa-unit");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
