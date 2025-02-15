package org.example.almacenamiento.mysql.hib;

import org.example.modelos.Producto;
import org.example.persistencia.dao.ProductoDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProductoDAOHibernate implements ProductoDAO
{

    public static ProductoDAOHibernate SINGLETON = new ProductoDAOHibernate();

    public static ProductoDAOHibernate getSINGLETON() { return SINGLETON; }

    private ProductoDAOHibernate() {}

    @Override
    public void create(Producto producto)
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(producto);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Producto read(int id)
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Producto.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Producto> read()
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Producto> query = session.createQuery("FROM Producto", Producto.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Producto producto)
    {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(producto);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Producto producto) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(producto);
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

            Query query = session.createQuery("DELETE FROM Producto");
            int rowsAffected = query.executeUpdate();
            session.createNativeQuery("ALTER TABLE producto AUTO_INCREMENT = 1").executeUpdate();

            transaction.commit();
            success = true;

            System.out.println("Se eliminaron " + rowsAffected + " productos.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }
}
