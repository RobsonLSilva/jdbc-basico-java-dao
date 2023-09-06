package robson.jdbc.exemplos.java.model.dao;

import java.util.List;

import robson.jdbc.exemplos.java.model.entities.Cliente;

public interface ClienteDao {

    void insert(Cliente cliente);
    void update(Cliente cliente);
    void deleteById(Integer id);
    Cliente findById(Integer id);
    List<Cliente> findAll();
} 