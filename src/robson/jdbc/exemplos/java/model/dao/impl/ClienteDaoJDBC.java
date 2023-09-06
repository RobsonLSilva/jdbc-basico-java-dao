package robson.jdbc.exemplos.java.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import robson.jdbc.exemplos.java.db.DB;
import robson.jdbc.exemplos.java.model.dao.ClienteDao;
import robson.jdbc.exemplos.java.model.entities.Cliente;

public class ClienteDaoJDBC implements ClienteDao{

    private Connection conn;

    public ClienteDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, cpf, telefone) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, cliente.getName());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getPhone());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    cliente.setId(id);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void update(Cliente cliente) {
        
    }

    @Override
    public void deleteById(Integer id) {
        
    }

    @Override
    public Cliente findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<Cliente> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    
}
