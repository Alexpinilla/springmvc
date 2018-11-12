package com.example.alexpinilla.datajpa.models.dao;

import com.example.alexpinilla.datajpa.models.entity.Cliente;

import java.util.List;

public interface IClienteDao {
    public List<Cliente> findAll();

    public void save(Cliente cliente);

    public Cliente findOne(Long id);
}
