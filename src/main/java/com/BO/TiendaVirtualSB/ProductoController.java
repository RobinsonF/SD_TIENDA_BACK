package com.BO.TiendaVirtualSB;

import com.DAO.TiendaVirtualSB.ProductoDAO;
import com.DTO.TiendaVirtualSB.Producto;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/producto", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductoController {

    @GetMapping("/consultarProducto/{id}")
    public Producto consultarProducto(@PathVariable("id") Integer codigo) {
        ProductoDAO Dao = new ProductoDAO();
        return 	Dao.buscarProductoPorCodigo(codigo);
    }

    @GetMapping("/consultarProducto")
    public List<Producto> consultarProductos() {
        ProductoDAO Dao = new ProductoDAO();
        return 	Dao.consultarProductos();
    }

    @PostMapping("/registrarProducto")
    public void registrarProducto(
            @RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion,
            @RequestParam("precio") Integer precio, @RequestParam("file") MultipartFile imagen
    )
    {
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        if (!imagen.isEmpty()) {
            try {
                Path directorioImagen = Paths.get("src//main//resources//static//images");
                String rutaAbsoluta = directorioImagen.toFile().getAbsolutePath();
                String nombreUnico = generateUniqueFileName(imagen.getOriginalFilename(), rutaAbsoluta);
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + nombreUnico);
                Files.write(rutaCompleta, bytesImg);
                producto.setImagen(nombreUnico);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        ProductoDAO Dao = new ProductoDAO();
        Dao.registrarProducto(producto);
    }

    @PutMapping("/actualizarProducto")
    public void actualizarProducto(@RequestBody Producto producto)
    {
        ProductoDAO Dao = new ProductoDAO();
        Dao.editarProducto(producto);
    }

    @DeleteMapping("/eliminarProducto/{codigo_producto}")
    public void eliminarProducto(@PathVariable(value = "codigo_producto") Integer codigoProducto)
    {
        ProductoDAO Dao = new ProductoDAO();
        Dao.eliminarProducto(codigoProducto);
    }

    private String generateUniqueFileName(String originalFileName, String directoryPath) {
        String fileName = originalFileName;
        Path filePath = Paths.get(directoryPath, fileName);
        int fileNumber = 1;

        while (Files.exists(filePath)) {
            String baseName = FilenameUtils.getBaseName(originalFileName);
            String extension = FilenameUtils.getExtension(originalFileName);
            fileName = baseName + fileNumber + "." + extension;
            filePath = Paths.get(directoryPath, fileName);
            fileNumber++;
        }

        return fileName;
    }
}
