package org.example.modelos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "venta_cliente")

public class Venta_Cliente implements Serializable
{

    @Id
    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    @Id
    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Cliente cliente;

    public Venta_Cliente() {}


    public Venta_Cliente(Venta venta, Cliente cliente)
    {
        this.venta = venta;
        this.cliente = cliente;
    }

    @Override
    public String toString()
    {
        return venta.toString() + " " + cliente.toString();
    }
}
