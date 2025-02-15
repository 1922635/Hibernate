package org.example.modelos;

import javax.persistence.*;

@Entity
@Table(name = "Clientes")


public class Cliente
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String nombre; // VARCHAR

    @Column(nullable = false, length = 320)
    private String email; // VARCHAR

    @Column(nullable = false, length = 10)
    private String telefono; // VARCHAR

    @Column(nullable = false, length = 400)
    private String direccion; // VARCHAR

    private static int ultimoId = -1;

    public Cliente() {}

    public Cliente(String nombre, String email, String telefono, String direccion)
    {
        this.id = ++ultimoId;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    @Override
    public String toString()
    {
        return "Cliente: " +
                "id: " + id +
                ", nombre: " + nombre +
                ", email: " + email +
                ", telefono: " + telefono +
                ", dirección: " + direccion;
    }
}
