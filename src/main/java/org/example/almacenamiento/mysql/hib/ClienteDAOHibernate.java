package org.example.almacenamiento.mysql.hib;

import org.example.modelos.Cliente;
import org.example.persistencia.dao.ClienteDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ClienteDAOHibernate implements ClienteDAO
{
    public static ClienteDAOHibernate SINGLETON = new ClienteDAOHibernate();

    private ClienteDAOHibernate() {}

    @Override
    public void create(Cliente cliente)
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(cliente);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cliente read(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Cliente.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Cliente> read() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Cliente> query = session.createQuery("FROM Cliente", Cliente.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Cliente cliente) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(cliente);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Cliente cliente) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(cliente);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete() {
        boolean success = false;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("DELETE FROM Cliente");
            int rowsAffected = query.executeUpdate();
            session.createNativeQuery("ALTER TABLE cliente AUTO_INCREMENT = 1").executeUpdate();

            transaction.commit();
            success = true;

            System.out.println("Se eliminaron " + rowsAffected + " clientes.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }
}
