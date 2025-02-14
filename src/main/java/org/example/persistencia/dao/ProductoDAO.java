package org.example.persistencia.dao;

import org.example.modelos.Producto;

import java.util.List;

public interface ProductoDAO
{
    // Métodos CRUD
    public void create(Producto producto);
    public Producto read(int id);
    public List<Producto> read();
    public boolean update(Producto producto);
    public boolean delete(Producto producto);
    public boolean delete();
}
