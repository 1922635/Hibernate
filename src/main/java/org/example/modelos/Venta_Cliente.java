package org.example.modelos;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "venta_cliente")
@IdClass(Venta_Cliente.Venta_ClienteId.class)

public class Venta_Cliente implements Serializable
{

    @Id
    private int venta_id;

    @Id
    private int cliente_id;

    public Venta_Cliente() {}

    public Venta_Cliente(int venta_id, int cliente_id)
    {
        this.venta_id = venta_id;
        this.cliente_id = cliente_id;
    }

    public int getVenta_id() { return venta_id; }

    public void setVenta_id(int venta_id) { this.venta_id = venta_id; }

    public int getCliente_id() { return cliente_id; }

    public void setCliente_id(int cliente_id) { this.cliente_id = cliente_id; }

    @Override
    public String toString()
    {
        return "Venta_Cliente: " +
                "venta_id: " + venta_id +
                ", cliente_id: " + cliente_id;
    }

    public static class Venta_ClienteId implements Serializable
    {
        private int venta_id;
        private int cliente_id;

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Venta_ClienteId that = (Venta_ClienteId) o;
            return venta_id == that.venta_id && cliente_id == that.cliente_id;
        }

        @Override
        public int hashCode() { return 31 * venta_id + cliente_id; }
    }
}


