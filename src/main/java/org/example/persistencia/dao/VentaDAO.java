package org.example.persistencia.dao;

import org.example.modelos.Venta;

import java.util.List;

public interface VentaDAO
{
    // Métodos CRUD
    public void create(Venta venta);
    public Venta read(int id);
    public List<Venta> read();
    public boolean update(Venta venta);
    public boolean delete(Venta venta);
    public boolean delete();
}
