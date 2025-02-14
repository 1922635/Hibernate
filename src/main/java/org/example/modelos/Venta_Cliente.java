package org.example.modelos;

import javax.persistence.*;

@Entity
@Table(name = "venta_cliente")

public class Venta_Cliente
{
    private int venta_id;
    private int cliente_id;
}
