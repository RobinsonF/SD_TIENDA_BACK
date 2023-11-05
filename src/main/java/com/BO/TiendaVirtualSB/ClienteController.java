package com.BO.TiendaVirtualSB;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.web.bind.annotation.*;
import com.DAO.TiendaVirtualSB.ClienteDAO;
import com.DAO.TiendaVirtualSB.ProveedorDAO;
import com.DAO.TiendaVirtualSB.VentasDAO;
import com.DTO.TiendaVirtualSB.ClienteVO;
import com.DTO.TiendaVirtualSB.ProveedorVO;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.MediaType;

@RestController
@CrossOrigin
@RequestMapping(value = "/cliente", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {

	@GetMapping("/consultarClientes/{id}")
	public ClienteVO consultarClientes(@PathVariable("id") String nit) {
		ClienteDAO Dao=new ClienteDAO(); 
	    return 	Dao.consultarClientes(nit).get(0);
	}

	@GetMapping("/consultarClientes")
	public ArrayList<ClienteVO> consultarClientes() {
		ClienteDAO Dao=new ClienteDAO();
		return 	Dao.consultarClientes();
	}

	@PostMapping("/registrarCliente")
	public void registrarCliente(@RequestBody ClienteVO cli)
	{
		ClienteDAO Dao=new ClienteDAO();
		Dao.registrarCliente(cli);
	}

	@PutMapping("/actualizarCliente")
	public void actualizarCliente(@RequestBody ClienteVO cli)
	{
		ClienteDAO Dao=new ClienteDAO();
		Dao.editarCliente(cli);
	}

	@DeleteMapping("/eliminarCliente/{cedula_cli}")
	public void eliminarCliente(@PathVariable(value = "cedula_cli") String cedula_cli)
	{
		ClienteDAO Dao=new ClienteDAO();
		Dao.eliminarCliente(cedula_cli);
	}
}