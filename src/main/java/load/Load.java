package load;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import save.Save;
import servermodels.users.User;

import java.io.Serializable;
import java.util.List;

public class Load {
    private static SessionFactory sessionFactory = Save.sessionFactory;

    public static <T> T fetch(Class<T> tClass, Serializable id) {
        Session session = sessionFactory.openSession();
        T t = session.get(tClass, id);
        session.close();
        return t;
    }

    public static <E> List<E> fetchAll(Class<E> entity) {
        Session session = sessionFactory.openSession();
        List<E> list = session.createQuery("from " + entity.getName(), entity).getResultList();
        session.close();
        return list;
    }
    public static <E> List<E> fetchWithCondition(Class<E> entity, String fieldName, Object value) {
        String sqlCode = "from " + entity.getName() + " where " + fieldName + "=" + "'" + value + "'";
        Session session = sessionFactory.openSession();
        List<E> list = session.createQuery(sqlCode, entity).getResultList();
        session.close();
        return list;
    }
    public static boolean isExistUser(String username){
        Session session = sessionFactory.openSession();
        User user = session.load(User.class, username);
        session.close();
        if (user == null)return false;
        else return true;
    }
    public static boolean isCorrectPass(String username, String password){
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, username);
        session.close();
        String correctPass = user.getPassword();
        if (password.equals(correctPass))return true;
        else return false;
    }
    public static <T> T delete(Class<T> tClass, Serializable id) {
        Session session = sessionFactory.openSession();
        T t = session.get(tClass, id);
        session.delete(t);
        session.close();
        return null;
    }
}
