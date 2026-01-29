package zgkprojekt.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import zgkprojekt.database.HibernateUtil;
import zgkprojekt.entity.User;

public class DbService {
    public void createUser(String name, String email) {

        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            User user = new User(name, email);
            session.persist(user);

            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public User getUserById(Long id) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.get(User.class, id);
        }
    }

}
