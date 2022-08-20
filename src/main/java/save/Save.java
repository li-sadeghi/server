package save;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class Save {
    public final static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        return sessionFactory;
    }

    public static void save(Object newObject){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(newObject);
        session.getTransaction().commit();
        session.close();
    }
    public static void saveAll(List<Object> objects){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (Object object : objects) {
            session.persist(object);
        }
        session.getTransaction().commit();
        session.close();
    }

}
