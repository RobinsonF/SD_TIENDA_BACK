package com.BO.TiendaVirtualSB;

import com.DAO.TiendaVirtualSB.ProveedorDAO;
import com.DTO.TiendaVirtualSB.ProveedorVO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/proveedor", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProveedorController {

    @GetMapping("/consultarProveedor/{id}")
    public ArrayList<ProveedorVO> consultarProveedor(@PathVariable("id") String nit) {
        ProveedorDAO Dao=new ProveedorDAO();
        return 	Dao.consultarProveedores(nit);
    }

    @GetMapping("/consultarProveedores")
    public List<ProveedorVO> consultarProveedores() {
        ProveedorDAO Dao=new ProveedorDAO();
        return 	Dao.consultarProveedores();
    }

    @PostMapping("/registrarProveedor")
    public void registrarProveedor(@RequestBody ProveedorVO proveedorVO)
    {
        ProveedorDAO Dao=new ProveedorDAO();
        Dao.insertProveedor(proveedorVO);
    }

    @PutMapping("/actualizarProveedor")
    public void actualizarProveedor(@RequestBody ProveedorVO proveedorVO)
    {
        ProveedorDAO Dao=new ProveedorDAO();
        Dao.actualizarProveedor(proveedorVO);
    }

    @DeleteMapping("/eliminarProveedor/{id}")
    public void eliminarCliente(@PathVariable(value = "id") String nit)
    {
        ProveedorDAO Dao=new ProveedorDAO();
        Dao.eliminarProveedor(nit);
    }
}
