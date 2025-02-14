package org.example.persistencia.dao;

import org.example.modelos.Venta_Cliente;

import java.util.List;

public interface Venta_ClienteDAO
{
    // Métodos CRUD
    public void create(Venta_Cliente venta_cliente);
    public Venta_Cliente read(int id);
    public List<Venta_Cliente> read();
    public boolean update(Venta_Cliente venta_cliente);
    public boolean delete(Venta_Cliente venta_cliente);
    public boolean delete();
}
