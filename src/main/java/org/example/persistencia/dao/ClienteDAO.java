package org.example.persistencia.dao;

import org.example.modelos.Cliente;

import java.util.List;

public interface ClienteDAO
{
    // Métodos CRUD
    public void create(Cliente cliente);
    public Cliente read(int id);
    public List<Cliente> read();
    public boolean update(Cliente cliente);
    public boolean delete(Cliente cliente);
    public boolean delete();
}
