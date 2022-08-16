package save;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Update {
    private static SessionFactory sessionFactory = Save.sessionFactory;
    public static void update(Object newObject){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(newObject);
        session.getTransaction().commit();
        session.close();
    }
}
