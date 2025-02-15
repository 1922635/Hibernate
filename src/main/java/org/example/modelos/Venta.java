package org.example.modelos;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "Ventas")

public class Venta
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private float total; // DECIMAL (10, 2)

    private static int ultimoId = -1;

    public Venta() {}

    public Venta(Date fecha, float total)
    {
        this.id = ++ultimoId;
        this.fecha = fecha;
        this.total = total;
    }

    @Override
    public String toString()
    {
        return "Venta: " +
                "id: " + id +
                ", fecha: " + fecha +
                ", total: " + total;
    }
}
