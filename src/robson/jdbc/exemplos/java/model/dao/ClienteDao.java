package robson.jdbc.exemplos.java.model.dao;

import java.util.List;

import robson.jdbc.exemplos.java.model.entities.Cliente;

public interface ClienteDao {

    void insert(Cliente cliente);
    void update(Cliente cliente);
    Integer deleteById(Long id);
    Cliente findById(Long id);
    List<Cliente> findAll();
} 