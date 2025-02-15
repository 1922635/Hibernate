package org.example;

import org.example.almacenamiento.mysql.hib.*;
import org.example.modelos.Cliente;
import org.example.modelos.Producto;
import org.example.modelos.Venta;
import org.example.modelos.Venta_Cliente;
import org.example.persistencia.dao.ProductoDAO;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main
{
    private static Scanner consola = new Scanner(System.in);
    private static ProductoDAOHibernate productoDAOHibernate = ProductoDAOHibernate.getSINGLETON();
    private static ClienteDAOHibernate clienteDAOHibernate = ClienteDAOHibernate.getSINGLETON();
    private static VentaDAOHibernate ventaDAOHibernate = VentaDAOHibernate.getSINGLETON();
    private static Venta_ClienteDAOHibernate ventaClienteDAOHibernate = Venta_ClienteDAOHibernate.getSINGLETON();

    public static void main(String[] args)
    {
        menuPrincipal();
    }

    private static List<Producto> getProductos() { return productoDAOHibernate.read(); }
    private static List<Cliente> getClientes() { return clienteDAOHibernate.read(); }

    public static void menuPrincipal() {
        int opcion;
        boolean continuar = true;
        int numOpciones = 9;

        while (continuar) {
            do {
                System.out.println("0. Salir");
                System.out.println("1. Añadir producto");
                System.out.println("2. Eliminar producto");
                System.out.println("3. Modificar producto");
                System.out.println("4. Listar productos");
                System.out.println("5. Añadir cliente");
                System.out.println("6. Eliminar cliente");
                System.out.println("7. Listar clientes");
                System.out.println("8. Realizar una venta");
                System.out.println("9. Realizar una venta a un cliente.");
                opcion = leerEntero(0, numOpciones);
            } while (opcion < 0 || opcion > numOpciones);

            switch (opcion) {
                case 0 -> continuar = false;
                case 1 -> addProducto();
                case 2 -> eliminarProducto();
                case 3 -> modificarProducto();
                case 4 -> listarProductos();
                case 5 -> addCliente();
                case 6 -> eliminarCliente();
                case 7 -> listarClientes();
                case 8 -> realizarVenta();
                case 9 -> realizarVenta_Cliente();
                default -> throw new RuntimeException("No se puede hacer una opción");
            }
        }
        cerrar();
    }

    public static void addProducto()
    {
        System.out.println("Introduzca el nombre del producto que desea añadir:");
        String nombre = "";
        while(nombre.isBlank()){
            nombre = consola.nextLine();
        }

        System.out.println("Introduzca el precio de compra del producto que desea añadir");
        float precio_compra = Float.valueOf(consola.nextLine().trim());

        System.out.println("Introduzca el precio de venta del producto que desea añadir");
        float precio_venta = Float.valueOf(consola.nextLine().trim());

        System.out.println("Introduzca el stock del producto que desea añadir");
        int stock = Integer.valueOf(consola.nextLine().trim());

        Producto producto = new Producto(nombre, precio_compra, precio_venta, stock);
        productoDAOHibernate.create(producto);
        getProductos().add(producto);
        System.out.println("Producto creado: " + producto);

    }

    public static void eliminarProducto()
    {
        System.out.println("Introduzca el id del producto que desea eliminar");
        int id = leerEntero();
        Producto producto = productoDAOHibernate.read(id);
        System.out.println(productoDAOHibernate.delete(producto)?"Producto borrado":"No se ha podido borrar el producto");
    }

    public static void modificarProducto()
    {
        System.out.println("Introduzca el id del producto que desea modificar:");
        int id = leerEntero();
        Producto producto = productoDAOHibernate.read(id);

        if (producto == null) System.out.println("No se encontró el producto");
        else
        {
            System.out.println("Introduzca el nuevo nombre de " + producto.getNombre());
            String nombre;
            while ((nombre = consola.nextLine().trim()).isBlank()) {}
            producto.setNombre(nombre);

            System.out.println("Introduzca el nuevo precio de compra de " + nombre);
            producto.setPrecio_compra(Float.valueOf(consola.nextLine().trim()));

            System.out.println("Introduzca el nuevo precio de venta de " + nombre);
            producto.setPrecio_venta(Float.valueOf(consola.nextLine().trim()));

            System.out.println("Introduzca la nueva cantidad de stock de " + nombre);
            producto.setStock(Integer.valueOf(consola.nextLine().trim()));

            System.out.println(producto);
            productoDAOHibernate.update(producto);
        }
    }

    public static void listarProductos()
    {
        System.out.println("Listado de Productos");
        for (Producto producto : getProductos())
        {
            System.out.println(producto);
        }
    }

    public static void addCliente()
    {
        System.out.println("Introduzca el nombre del cliente que desea añadir:");
        String nombre = "";
        while(nombre.isBlank()){
            nombre = consola.nextLine();
        }

        System.out.println("Introduzca el email del cliente que desea añadir");
        String email = consola.nextLine().trim();

        System.out.println("Introduzca el telefono del cliente que desea añadir");
        String telefono = consola.nextLine().trim();

        System.out.println("Introduzca la dirección del cliente que desea añadir");
        String direccion = consola.nextLine().trim();

        Cliente cliente = new Cliente(nombre, email, telefono, direccion);
        clienteDAOHibernate.create(cliente);
        getClientes().add(cliente);
        System.out.println("Cliente creado: " + cliente);
    }

    public static void eliminarCliente()
    {
        System.out.println("Opción no implementada");

        System.out.println("Introduzca el id del cliente que desea eliminar");
        int id = leerEntero();

        List<Venta_Cliente> ventas = Venta_ClienteDAOHibernate.getSINGLETON().findByClienteId(id);
        if (ventas != null && !ventas.isEmpty())
        {
            System.out.println("El cliente " + id + " tiene ventas asociadas, estas se eliminarán también.");
            for (Venta_Cliente venta : ventas)
            {
                System.out.println(ventaClienteDAOHibernate.delete(venta)?"Venta borrada":"No se ha podido borrar la venta");
            }
        }
        Cliente cliente = clienteDAOHibernate.read(id);
        System.out.println(clienteDAOHibernate.delete(cliente)?"Cliente borrado":"No se ha podido borrar el cliente");
    }

    public static void listarClientes()
    {
        System.out.println("Listado de Clientes");
        for (Cliente cliente : getClientes())
        {
            System.out.println(cliente);
        }
    }

    public static void realizarVenta()
    {
        System.out.println("Introduzca la fecha de la venta que desea realizar (formato YYYY-MM-DD) :");
        Date fecha = Date.valueOf(consola.nextLine().trim());

        System.out.println("Introduzca el total de la venta que desea realizar");
        float total = Float.valueOf(consola.nextLine().trim());

        Venta venta = new Venta(fecha, total);
        ventaDAOHibernate.create(venta);
        System.out.println("Venta creada: " + venta);
    }

    public static void realizarVenta_Cliente()
    {
        System.out.println("Opción no implementada");

        // Tendría que pedir ambos id
    }

    public static void cerrar() {
        consola.close();
        HibernateUtil.shutdown();
    }

    public static int leerEntero() {
        return leerEntero(0, Integer.MAX_VALUE);
    }

    public static int leerEntero(int desde, int hasta)
    {
        int entero = Integer.MAX_VALUE;
        if (desde > hasta)
        {
            int aux = desde;
            hasta = desde;
            desde = aux;
        }

        do
        {
            try
            {
                if (hasta == Integer.MAX_VALUE)
                {
                    System.out.println("Introduzca un número (mínimo = " + desde + ")");
                }
                else
                {
                    System.out.println("Introduzca un entero entre " + desde + " y " + hasta);
                }
                entero = Integer.valueOf(consola.nextLine().trim());
            } catch (Exception e)
            {
                System.out.println("El valor introducido no es un número");
            }
        } while(entero < desde || entero > hasta);
        return entero;
    }

}