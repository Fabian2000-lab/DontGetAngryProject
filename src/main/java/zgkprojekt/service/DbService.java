package zgkprojekt.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import zgkprojekt.database.HibernateUtil;
import zgkprojekt.entity.User;

public class DbService {

    public void createUser(String name) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            User user = new User(name);
            session.persist(user);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }

    public boolean isNameAvailable(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Long count = session.createNativeQuery(
                    "SELECT count(*) FROM users WHERE name = :name", Long.class)
                    .setParameter("name", name)
                    .uniqueResult();

            return count == null || count == 0;
        }
    }

    public User getUserByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createNativeQuery(
                    "SELECT * FROM users WHERE name = :name", User.class)
                    .setParameter("name", name)
                    .uniqueResult();
        }
    }

    public Double getPointsByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createNativeQuery(
                    "SELECT points FROM users WHERE name = :name", Double.class)
                    .setParameter("name", name)
                    .uniqueResult();
        }
    }

    public void updatePoints(String name, Double points) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            User user = session.createNativeQuery(
                    "SELECT * FROM users WHERE name = :name", User.class)
                    .setParameter("name", name)
                    .uniqueResult();

            if (user != null) {
                user.setPoints(points);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }

    public String[] getAllUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createNativeQuery("SELECT name FROM users", String.class)
                    .list()
                    .toArray(new String[0]);
        }
    }

}
