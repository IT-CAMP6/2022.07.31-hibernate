package pl.camp.it;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class Main {
    public static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactory = new Configuration().configure().buildSessionFactory();

        User user = new User();
        user.setName("Zbyszek");
        user.setSurname("Kowalski");
        user.setSex(User.Sex.MEN);

        user.getOrders().add(new Order(0, 34.01));
        user.getOrders().add(new Order(0, 34.02));
        user.getOrders().add(new Order(0, 34.03));
        user.getOrders().add(new Order(0, 34.04));
        user.getOrders().add(new Order(0, 34.05));
        user.getOrders().add(new Order(0, 34.06));
        user.getOrders().add(new Order(0, 34.07));

        persistUser(user);

        //updateUser(user);

        //deleteUser(user);

        /*Optional<User> userBox = getUserById(10);
        if(userBox.isPresent()) {
            System.out.println(userBox.get());
        } else {
            System.out.println("Takiego usera nie ma !!!");
        }*/

        //System.out.println(getAllUsers());
        System.out.println(getUserById(1));

    }

    public static void persistUser(User user) {
        Session session = Main.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(user);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static void updateUser(User user) {
        Session session = Main.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static void deleteUser(User user) {
        Session session = Main.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static Optional<User> getUserById(int id) {
        Session session = Main.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM pl.camp.it.User WHERE id = :id");
        query.setParameter("id", id);
        try {
            User user = query.getSingleResult();
            session.close();
            return Optional.of(user);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }
    }

    public static List<User> getAllUsers() {
        Session session = Main.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM pl.camp.it.User");
        List<User> result = query.getResultList();
        session.close();
        return result;
    }


}
