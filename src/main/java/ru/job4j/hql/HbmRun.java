package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Candidate john = Candidate.of("John", 3, 2000);
            Candidate sally = Candidate.of("Sally", 4, 3000);
            Candidate tom = Candidate.of("Tom", 1, 1000);

            session.save(john);
            session.save(sally);
            session.save(tom);

            Query<Candidate> query = session.createQuery("select c from Candidate as c", Candidate.class);
            for (Candidate can : query.list()) {
                System.out.println(can.getName());
            }

            Candidate can = session.createQuery("select c from Candidate as c where c.id = :id", Candidate.class)
                    .setParameter("id", 1)
                    .uniqueResult();
            System.out.println("Candidate with id = 1: " + can.getName());

            Candidate can2 = session.createQuery("select c from Candidate as c where c.name = :name", Candidate.class)
                    .setParameter("name", "John")
                    .uniqueResult();
            System.out.println("Candidate with name John: " + can2.getId());

            session.createQuery("update Candidate set salary = 2500 where id = :id")
                    .setParameter("id", 1)
                    .executeUpdate();

            session.createQuery("delete Candidate where id = :id")
                    .setParameter("id", 2)
                    .executeUpdate();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
