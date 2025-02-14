package org.example.almacenamiento.mysql.hib;

import org.example.modelos.Producto;
import org.example.persistencia.dao.ProductoDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductoDAOHibernate implements ProductoDAO
{

    public static ProductoDAOHibernate SINGLETON = new ProductoDAOHibernate();

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
    public Producto read(int id) {
        return null;
    }

    @Override
    public List<Producto> read() {
        return List.of();
    }

    @Override
    public boolean update(Producto producto) {
        return false;
    }

    @Override
    public boolean delete(Producto producto) {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }
}
