package org.example.almacenamiento.mysql.hib;

import org.example.modelos.Venta;
import org.example.persistencia.dao.VentaDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class VentaDAOHibernate implements VentaDAO
{
    public static VentaDAOHibernate SINGLETON = new VentaDAOHibernate();

    private VentaDAOHibernate() {}

    @Override
    public void create(Venta venta)
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(venta);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Venta read(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Venta.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Venta> read() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Venta> query = session.createQuery("FROM Venta", Venta.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Venta venta) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(venta);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Venta venta) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(venta);
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

            Query query = session.createQuery("DELETE FROM Venta");
            int rowsAffected = query.executeUpdate();
            session.createNativeQuery("ALTER TABLE venta AUTO_INCREMENT = 1").executeUpdate();

            transaction.commit();
            success = true;

            System.out.println("Se eliminaron " + rowsAffected + " clientes.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }
}
