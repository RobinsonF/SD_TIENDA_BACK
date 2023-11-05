package com.BO.TiendaVirtualSB;

import com.DAO.TiendaVirtualSB.VentasDAO;
import com.DTO.TiendaVirtualSB.Ventas;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/venta", produces = MediaType.APPLICATION_JSON_VALUE)
public class VentaController {

    @GetMapping("/consolidado/{tipo}")
    public ArrayList<String> consultarConsolidado(@PathVariable(value = "tipo") String tipo) {
        VentasDAO Dao=new VentasDAO();
        return Dao.consultarConsolidado(tipo);
    }

    @PostMapping("/registrarVenta")
    public void registrarVenta(@RequestBody Map<String, Object> params) {
        String codigo_producto = params.get("codigo_producto") == null ? "" : ((String) params.get("codigo_producto"));
        Integer cantidad = params.get("cantidad") == null ? 0 : ((Integer) params.get("cantidad"));
        String NIT_cliente = params.get("NIT_cliente") == null ? "" : ((String) params.get("NIT_cliente"));
        Ventas ventas = new Ventas(codigo_producto, cantidad, NIT_cliente);
        VentasDAO Dao=new VentasDAO();
        Dao.insertVentas(ventas);
    }

    @PostMapping("/registrarVentaMultiple")
    public void registrarVentaMultiple(@RequestBody Map<String, Object> params) {
        List<Integer> productosIds = (List<Integer>) params.get("productsIds");
        Integer cantidad = params.get("cantidad") == null ? 0 : ((Integer) params.get("cantidad"));
        String NIT_cliente = params.get("nitCliente") == null ? "" : ((String) params.get("nitCliente"));
        for (Integer productoId: productosIds) {
            Ventas ventas = new Ventas(productoId.toString(), cantidad, NIT_cliente);
            VentasDAO Dao=new VentasDAO();
            Dao.insertVentas(ventas);
        }
    }
}
