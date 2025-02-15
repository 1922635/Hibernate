package org.example.modelos;

import org.example.almacenamiento.mysql.hib.ProductoDAOHibernate;

import javax.persistence.*;

@Entity
@Table(name = "Productos")

public class Producto
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String nombre; // VARCHAR

    @Column(nullable = false)
    private float precio_compra; // DECIMAL(10, 2)

    @Column(nullable = false)
    private float precio_venta; // DECIMAL(10, 2)

    @Column(nullable = false)
    private int stock;

    private static int ultimoId = -1;

    public Producto() {}

    public Producto(String nombre, float precio_compra, float precio_venta, int stock)
    {
        this.id = ++ultimoId;
        this.nombre = nombre;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
        this.stock = stock;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public float getPrecio_compra() { return precio_compra; }

    public void setPrecio_compra(float precio_compra) { this.precio_compra = precio_compra; }

    public float getPrecio_venta() { return precio_venta; }

    public void setPrecio_venta(float precio_venta) { this.precio_venta = precio_venta; }

    public float getStock() { return stock; }

    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString()
    {
        return "Producto: " +
                "id: " + id +
                ", nombre: " + nombre +
                ", precio_compra: " + precio_compra +
                ", precio_venta: " + precio_venta +
                ", stock: " + stock;
    }
}
