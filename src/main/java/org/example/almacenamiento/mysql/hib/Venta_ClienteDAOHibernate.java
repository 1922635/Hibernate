package org.example.almacenamiento.mysql.hib;

import org.example.modelos.Venta_Cliente;
import org.example.persistencia.dao.Venta_ClienteDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class Venta_ClienteDAOHibernate implements Venta_ClienteDAO
{
    public static Venta_ClienteDAOHibernate SINGLETON = new Venta_ClienteDAOHibernate();

    public static Venta_ClienteDAOHibernate getSINGLETON() { return SINGLETON; }

    private Venta_ClienteDAOHibernate() {}

    @Override
    public void create(Venta_Cliente venta_cliente)
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(venta_cliente);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Venta_Cliente read(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Venta_Cliente.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Venta_Cliente> read() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Venta_Cliente> query = session.createQuery("FROM Venta_Cliente", Venta_Cliente.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Venta_Cliente> findByClienteId(int cliente_id)
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            Query<Venta_Cliente> query = session.createQuery("FROM Venta_Cliente WHERE cliente_id = :cliente_id", Venta_Cliente.class);
            query.setParameter("cliente_id", cliente_id);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Venta_Cliente venta_cliente) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(venta_cliente);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Venta_Cliente venta_cliente) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(venta_cliente);
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

            Query query = session.createQuery("DELETE FROM Venta_Cliente");
            int rowsAffected = query.executeUpdate();
            session.createNativeQuery("ALTER TABLE venta_cliente AUTO_INCREMENT = 1").executeUpdate();

            transaction.commit();
            success = true;

            System.out.println("Se eliminaron " + rowsAffected + " filas.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }
}
