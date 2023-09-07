package robson.jdbc.exemplos.java.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
                    Long id = rs.getLong(1);
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
        String sql = "UPDATE clientes SET nome=?, cpf=?, telefone=? WHERE id=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, cliente.getName());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getPhone());
            ps.setLong(4, cliente.getId());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    Long id = rs.getLong(1);
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
    public Integer deleteById(Long id) {
        Integer rowsAffected = null;
        String sql = "DELETE FROM clientes WHERE id=?";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);

            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
        return rowsAffected;
    }

    @Override
    public Cliente findById(Long id) {
        String sql = "SELECT * FROM clientes WHERE id=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cliente cliente = new Cliente();

        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                cliente.setId(rs.getLong(1));
                cliente.setName(rs.getString(2));
                cliente.setCpf(rs.getString(3));
                cliente.setPhone(rs.getString(4));
            }
        
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
        return cliente;
    }

    @Override
    public List<Cliente> findAll() {
       String sql = "SELECT * FROM clientes";
       PreparedStatement ps = null;
       ResultSet rs = null;
       List<Cliente> cliente = new ArrayList<>();

       try {
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            Cliente obj = new Cliente();

            obj.setId(rs.getLong(1));
            obj.setName(rs.getString(2));
            obj.setCpf(rs.getString(3));
            obj.setPhone(rs.getString(4));

            cliente.add(obj);
        }
       } catch (SQLException e) {
        System.out.println(e.getMessage());
       }
       return cliente;
    }
    
}
