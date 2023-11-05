package com.DAO.TiendaVirtualSB;

import com.DTO.TiendaVirtualSB.Producto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    public void registrarProducto(Producto producto) {
        Conexion conex = new Conexion();
        try {
            Statement estatuto = conex.getConnection().createStatement();
            estatuto.executeUpdate("INSERT INTO productos (nombre,descripcion,precio,imagen) VALUES ('" + producto.getNombre() + "', '"
                    + producto.getDescripcion() + "', '" + producto.getPrecio() + "', '" + producto.getImagen() + "')");
            estatuto.close();
            conex.desconectar();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Producto> consultarProductos() {
        Conexion conex = new Conexion();
        List<Producto> productos = new ArrayList<>();
        try {
            Statement estatuto = conex.getConnection().createStatement();
            ResultSet rs = estatuto.executeQuery("SELECT * FROM productos");
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setCodigoProducto(rs.getInt("codigo_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getInt("precio"));
                producto.setImagen(rs.getString("imagen"));
                productos.add(producto);
            }
            estatuto.close();
            conex.desconectar();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return productos;
    }

    public Producto buscarProductoPorCodigo(int codigoProducto) {
        Conexion conex = new Conexion();
        Producto producto = null;
        try {
            Statement estatuto = conex.getConnection().createStatement();
            ResultSet rs = estatuto.executeQuery("SELECT * FROM productos WHERE codigo_producto = " + codigoProducto);
            if (rs.next()) {
                producto = new Producto();
                producto.setCodigoProducto(rs.getInt("codigo_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getInt("precio"));
                producto.setImagen(rs.getString("imagen"));
            }
            estatuto.close();
            conex.desconectar();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return producto;
    }

    public void editarProducto(Producto producto) {
        Conexion conex = new Conexion();
        try {
            Statement estatuto = conex.getConnection().createStatement();
            String query = "UPDATE productos SET " +
                    "nombre = '" + producto.getNombre() + "', " +
                    "descripcion = '" + producto.getDescripcion() + "', " +
                    "precio = " + producto.getPrecio() + ", " +
                    "imagen = '" + producto.getImagen() + "' " +
                    "WHERE codigo_producto = " + producto.getCodigoProducto();
            estatuto.executeUpdate(query);
            estatuto.close();
            conex.desconectar();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarProducto(int codigoProducto) {
        Conexion conex = new Conexion();
        try {
            Statement estatuto = conex.getConnection().createStatement();
            estatuto.executeUpdate("DELETE FROM productos WHERE codigo_producto = " + codigoProducto);
            estatuto.close();
            conex.desconectar();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
