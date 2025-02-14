package org.example.modelos;

import javax.persistence.*;

@Entity
@Table(name = "venta_cliente")

public class Venta_Cliente
{
    @Id
    private int venta_id;

    @Id
    private int cliente_id;
}
