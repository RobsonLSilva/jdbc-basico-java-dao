package robson.jdbc.exemplos.java.application;

import java.util.List;

import robson.jdbc.exemplos.java.db.DB;
import robson.jdbc.exemplos.java.model.dao.ClienteDao;
import robson.jdbc.exemplos.java.model.dao.impl.ClienteDaoJDBC;
import robson.jdbc.exemplos.java.model.entities.Cliente;

public class Main {
    public static void main(String[] args) throws Exception {
        ClienteDao crud = new ClienteDaoJDBC(DB.getConnection());

        System.out.println("\n=== TEST 1: cliente findAll =====");
		List<Cliente> list = crud.findAll();

		for (Cliente cliente : list) {
			System.out.println(cliente);
		}

        System.out.println("\n-------------------------------------------------------------------------");
     
        System.out.println("\n=== TEST 2: cliente Insert =====");
        Cliente cliente = new Cliente(null, "Cliente 4", "444.444.444-44", "(44) 4444-4444");
		crud.insert(cliente);
		System.out.println("Inserted! New id = " + cliente.getId());
       
        System.out.println("\n-------------------------------------------------------------------------");

        System.out.println("\n=== TEST 3: cliente Update =====");
        Cliente cliente1 = new Cliente(3L,"Cliente 9", "999.999.999-99", "(99) 9999-9999");
		crud.update(cliente1);       
		System.out.println("Update! id = " + cliente1.getId());

        System.out.println("\n-------------------------------------------------------------------------");

        System.out.println("\n=== TEST 4: cliente Delete =====");
        int rowsAffected = crud.deleteById(5L);        
		System.out.println(rowsAffected + " deleted rows");

        System.out.println("\n-------------------------------------------------------------------------");
        
        System.out.println("\n=== TEST 5: cliente findById =====");
		Cliente cliente2 = crud.findById(2L);
        System.out.println(cliente2);

        DB.closeConnection();

    }
}
