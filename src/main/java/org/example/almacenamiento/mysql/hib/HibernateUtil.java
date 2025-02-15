package org.example.almacenamiento.mysql.hib;

import org.example.modelos.Cliente;
import org.example.modelos.Producto;
import org.example.modelos.Venta;
import org.example.modelos.Venta_Cliente;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            configuration.addAnnotatedClass(Producto.class);
            configuration.addAnnotatedClass(Cliente.class);
            configuration.addAnnotatedClass(Venta.class);
            configuration.addAnnotatedClass(Venta_Cliente.class);

            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Error al inicializar la SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}