package io.codeforall.fanstatics;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Sandbox {

    public static void main(String[] args) {

        // Use the test persistence unit to configure a new
        // entity manager factory and start up JPA
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

        // Open a new database connection by getting a new
        // entity manager from the entity manager factory
        EntityManager em = emf.createEntityManager();

        Cadet cadet = new Cadet();
        cadet.setId(1);
        cadet.setName("Cristiano Penaldo");
        cadet.setAge(39);

        Mc mc = new Mc();
        mc.setName("Gustavino");
        mc.setAge(69);


        em.getTransaction().begin();
        em.persist(cadet);
        em.merge(mc);
        cadet.findById(1);
        em.getTransaction().commit();

        // Close the database connection
        em.close();

        // Shutdown JPA
        emf.close();
    }
}
